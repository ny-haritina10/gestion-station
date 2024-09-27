--
-- TABLE 
--

-- MAGASIN
CREATE TABLE Magasin (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- POMPISTE
CREATE TABLE Pompiste (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- POMPE 
CREATE TABLE Pompe (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- UNIT 
CREATE TABLE Unit (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- PRODUCT
CREATE TABLE Product (
    id INT PRIMARY KEY,
    id_unit INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PU_achat NUMERIC(10, 2) NOT NULL,
    PU_vente NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_unit FOREIGN KEY (id_unit) REFERENCES Unit(id) 
);

-- FOURNISSEUR 
CREATE TABLE Fournisseur (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- CUVE
CREATE TABLE Cuve (
    id INT PRIMARY KEY,
    id_pompe INT NOT NULL,
    id_product INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    qte_max NUMERIC(10, 2) NOT NULL,
    qte_initial NUMERIC(10, 2) DEFAULT 0,
    CONSTRAINT fk_id_pompe FOREIGN KEY (id_pompe) REFERENCES Pompe(id),
    CONSTRAINT fk_product_cuve FOREIGN KEY (id_product) REFERENCES Product(id)
);

-- COUNTER
CREATE TABLE Counter (
    id INT PRIMARY KEY,
    amount NUMERIC(10, 2) DEFAULT 0,
    is_payed VARCHAR(5) NOT NULL, 
    id_pompe INT NOT NULL,
    id_pompiste INT,     
    id_fournisseur INT,
    id_product INT NOT NULL,
    date_session DATE DEFAULT SYSDATE, 
    type_transaction VARCHAR(255) NOT NULL,  
    CONSTRAINT fk_pompe FOREIGN KEY (id_pompe) REFERENCES Pompe(id),
    CONSTRAINT fk_pompiste FOREIGN KEY (id_pompiste) REFERENCES Pompiste(id),
    CONSTRAINT fk_fournisseur FOREIGN KEY (id_fournisseur) REFERENCES Fournisseur(id),
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES Product(id)
);