--
-- PARITé
--
CREATE OR REPLACE VIEW v_pompiste_parity AS
SELECT 
    id_pompiste,
    CASE 
        WHEN MOD(COUNT(*), 2) = 0 THEN 'TRUE' 
        ELSE 'FALSE' 
    END AS isEven
FROM 
    Counter
WHERE 
    id_pompiste IS NOT NULL  -- Filter out NULLs
GROUP BY 
    id_pompiste;

--
-- VENTES 
--
CREATE OR REPLACE VIEW v_pompe_ventes AS
WITH Sessions AS (
    SELECT
        id_product,
        id_pompiste,
        id_pompe,
        amount,
        is_payed,
        date_session,
        LAG(amount) OVER (PARTITION BY id_pompiste, id_pompe ORDER BY date_session, amount) AS amount_previous,
        LAG(date_session) OVER (PARTITION BY id_pompiste, id_pompe ORDER BY date_session) AS date_previous,
        ROW_NUMBER() OVER (PARTITION BY id_pompiste, id_pompe ORDER BY date_session, amount) AS rn,
        CASE 
            WHEN MOD(ROW_NUMBER() OVER (PARTITION BY id_pompiste, id_pompe ORDER BY date_session), 2) = 0 THEN 'TRUE'
            ELSE 'FALSE'
        END AS isEven
    FROM
        Counter
    WHERE id_pompiste IS NOT NULL
)
SELECT 
    id_product,
    id_pompiste,
    id_pompe,
    date_previous AS date_beginning_session,
    date_session AS date_end_session,
    amount_previous AS amount_beginning,
    amount AS amount_end,
    is_payed
FROM 
    Sessions
WHERE 
    isEven = 'TRUE'; 

--
-- AMOUNT PER POMPES PER DATES
--
CREATE OR REPLACE VIEW v_ventes_per_pompe_and_date AS
SELECT
    id_product,
    id_pompe,
    TRUNC(date_end_session) AS date_vente,  
    SUM(amount_end - amount_beginning) AS somme_ventes,
    is_payed
FROM 
    v_pompe_ventes
GROUP BY
    id_product,
    id_pompe,
    TRUNC(date_end_session),
    is_payed
ORDER BY
    id_pompe,
    date_vente;

--
-- AMOUNT PER POMPES
--
CREATE OR REPLACE VIEW v_vente_totale_per_pompe AS
SELECT
    id_product,
    id_pompe,
    SUM(amount_end - amount_beginning) AS somme_totale
FROM 
    v_pompe_ventes
GROUP BY
    id_product,
    id_pompe
ORDER BY
    id_pompe;

--
-- AMOUNT TOTAL OF ALL POMPES
--
CREATE OR REPLACE VIEW v_vente_total_all_pompes AS
SELECT
    SUM(amount_end - amount_beginning) AS somme_totale
FROM 
    v_pompe_ventes;

--
-- RAVITAILLEMENT 
--
CREATE OR REPLACE VIEW v_ravitaillement AS 
SELECT 
    id_product,
    id_pompe AS pompe,
    date_session AS date_ravitaillement,
    SUM(amount) AS total_ravitaillement
FROM 
    Counter
WHERE 
    type_transaction = 'CASH_OUT'
GROUP BY 
    id_product, date_session, id_pompe;

--
-- TOTAL VENTES
--
CREATE OR REPLACE VIEW v_total_ventes AS 
SELECT
    id_product,
    id_pompe AS pompe,
    SUM(somme_ventes) AS total_ventes
FROM 
    v_ventes_per_pompe_and_date
GROUP BY 
    id_product,
    id_pompe;

--
-- STOCK LITERS 
--
CREATE OR REPLACE VIEW v_stock_restant AS
SELECT
    p.id AS id_product,
    p.name AS product_name,
    c.id_pompe AS pompe,
    r.date_ravitaillement AS date_ravitaillement,
    c.qte_initial AS qte_initiale,
    NVL(r.total_ravitaillement, 0) AS total_ravitaillee,
    NVL(vtv.total_ventes, 0) AS total_vendue,
    (c.qte_initial + NVL(r.total_ravitaillement, 0) - NVL(vtv.total_ventes, 0)) AS qte_stock_restant_unit,
    (c.qte_initial + NVL(r.total_ravitaillement, 0) - NVL(vtv.total_ventes, 0)) * p.PU_vente AS qte_stock_restant_amount
FROM
    Product p
JOIN
    Cuve c ON p.id = c.id_product
LEFT JOIN
    v_ravitaillement r ON p.id = r.id_product AND c.id_pompe = r.pompe  
LEFT JOIN
    v_total_ventes vtv ON p.id = vtv.id_product AND c.id_pompe = vtv.pompe  
GROUP BY
    p.id, p.name, c.qte_initial, r.total_ravitaillement, vtv.total_ventes, p.PU_vente, c.id_pompe, r.date_ravitaillement;

--
-- BENEFICES
--
CREATE OR REPLACE VIEW v_profit AS
SELECT
    p.id AS product_id,
    p.name AS product_name,
    c.id_pompe AS pump_id,
    r.date_ravitaillement AS date_profit,
    NVL(vtv.total_ventes, 0) AS qte_vendue,
    NVL(r.total_ravitaillement, 0) AS qte_achete,
    p.PU_vente,
    p.PU_achat,
    vsr.qte_stock_restant_amount AS amount_stock,
    ((NVL(vtv.total_ventes, 0) * p.PU_vente) - (NVL(r.total_ravitaillement, 0) * p.PU_achat) + vsr.qte_stock_restant_amount) AS profit
FROM
    Product p
JOIN
    Cuve c ON p.id = c.id_product
LEFT JOIN
    v_total_ventes vtv ON p.id = vtv.id_product AND c.id_pompe = vtv.pompe
LEFT JOIN
    v_ravitaillement r ON p.id = r.id_product AND c.id_pompe = r.pompe
LEFT JOIN
    V_stock_restant vsr ON p.id = vsr.id_product AND c.id_pompe = vsr.pompe;

--
-- SITUATION DE DETTE ET DE CREANCES
-- 
CREATE OR REPLACE VIEW v_get_dette AS 
SELECT 
    amount,
    is_payed,
    id_product,
    date_session,
    type_transaction
FROM 
    Counter
WHERE 
    is_payed = 'FALSE' 
AND
    type_transaction = 'CASH_OUT';

--
-- CREANCES 
--
CREATE OR REPLACE VIEW v_get_creances AS 
SELECT 
    c.is_payed,
    c.id_product,
    c.date_session,
    c.type_transaction,
    vppd.somme_ventes
FROM 
    Counter c
JOIN 
    v_ventes_per_pompe_and_date vppd
ON
    c.id_product = vppd.id_product AND c.is_payed = vppd.is_payed
WHERE 
    c.is_payed = 'FALSE' 
AND
    c.type_transaction = 'CASH_IN';