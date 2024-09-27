-- Insert queries for Magasin
INSERT INTO Magasin (id, name) VALUES (seq_magasin.NEXTVAL, 'SHELL');

-- Insert queries for Pompiste
INSERT INTO Pompiste (id, name) VALUES (seq_pompiste.NEXTVAL, 'John Doe');
INSERT INTO Pompiste (id, name) VALUES (seq_pompiste.NEXTVAL, 'Jane Smith');
INSERT INTO Pompiste (id, name) VALUES (seq_pompiste.NEXTVAL, 'Alice Johnson');

-- Insert queries for Pompe
INSERT INTO Pompe (id, name) VALUES (seq_pompe.NEXTVAL, 'Pump A');
INSERT INTO Pompe (id, name) VALUES (seq_pompe.NEXTVAL, 'Pump B');
INSERT INTO Pompe (id, name) VALUES (seq_pompe.NEXTVAL, 'Pump C');
INSERT INTO Pompe (id, name) VALUES (seq_pompe.NEXTVAL, 'Pump D');

-- Insert queries for Unit
INSERT INTO Unit (id, name) VALUES (seq_unit.NEXTVAL, 'Liter');

-- Insert queries for Product
INSERT INTO Product (id, id_unit, name, PU_achat, PU_vente) VALUES (seq_product.NEXTVAL, 2, 'Gasoline', 1.20, 1.50);

-- Insert queries for Cuve
INSERT INTO Cuve (id, id_pompe, name, qte_max, qte_initial, id_product) VALUES (seq_cuve.NEXTVAL, 2, 'Tank 1', 1000.00, 500.00, 3);
INSERT INTO Cuve (id, id_pompe, name, qte_max, qte_initial, id_product) VALUES (seq_cuve.NEXTVAL, 3, 'Tank 2', 2000.00, 1500.00, 3);
INSERT INTO Cuve (id, id_pompe, name, qte_max, qte_initial, id_product) VALUES (seq_cuve.NEXTVAL, 4, 'Tank 3', 1500.00, 1000.00, 3);
INSERT INTO Cuve (id, id_pompe, name, qte_max, qte_initial, id_product) VALUES (seq_cuve.NEXTVAL, 5, 'Tank 4', 1500.00, 1000.00, 3);


-- Fournisseur 
INSERT INTO Fournisseur (id, name) VALUES (seq_fournisseur.NEXTVAL, 'PETROL COMPANY');

-- INSERT COUNTER
INSERT INTO Counter (id, amount, is_payed, id_pompe, id_pompiste, id_fournisseur, id_product, date_session, type_transaction) 
VALUES 
(seq_counter.NEXTVAL, 10000, 'TRUE', 2, NULL, 2, 3, SYSDATE, 'CASH_OUT'),
(seq_counter.NEXTVAL, 12000, 'FALSE', 3, NULL, 2, 3, SYSDATE, 'CASH_OUT');