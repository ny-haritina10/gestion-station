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

-- Insert queries for Cuve
INSERT INTO Cuve (id, name, qte_max, qte_initial) VALUES (seq_cuve.NEXTVAL, 'Tank 1', 1000.00, 500.00);
INSERT INTO Cuve (id, name, qte_max, qte_initial) VALUES (seq_cuve.NEXTVAL, 'Tank 2', 2000.00, 1500.00);
INSERT INTO Cuve (id, name, qte_max, qte_initial) VALUES (seq_cuve.NEXTVAL, 'Tank 3', 1500.00, 1000.00);
INSERT INTO Cuve (id, name, qte_max, qte_initial) VALUES (seq_cuve.NEXTVAL, 'Tank 4', 1500.00, 1000.00);

-- Insert queries for Unit
INSERT INTO Unit (id, name) VALUES (seq_unit.NEXTVAL, 'Liter');

-- Insert queries for Product
INSERT INTO Product (id, id_unit, name, PU_achat, PU_vente) VALUES (seq_product.NEXTVAL, 2, 'Gasoline', 1.20, 1.50);

-- INSERT RAVITAILLEMENT
INSERT INTO Counter (id, amount, id_pompe, id_pompiste, date_session, type_transaction) 
VALUES (seq_counter.NEXTVAL, 55000, 2, NULL, TO_DATE('2024-09-14', 'YYYY-MM-DD'), 'CASH_OUT');