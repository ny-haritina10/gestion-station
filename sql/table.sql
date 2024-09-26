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

-- CUVE
CREATE TABLE Cuve (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    qte_max NUMERIC(10, 2) NOT NULL,
    qte_initial NUMERIC(10, 2) DEFAULT 0
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

-- COUNTER
CREATE TABLE Counter (
    id INT PRIMARY KEY,
    amount NUMERIC(10, 2) DEFAULT 0,
    id_pompe INT NOT NULL,
    id_pompiste INT,    
    date_session DATE DEFAULT SYSDATE, 
    type_transaction VARCHAR(255) NOT NULL,  
    CONSTRAINT fk_pompe FOREIGN KEY (id_pompe) REFERENCES Pompe(id),
    CONSTRAINT fk_pompiste FOREIGN KEY (id_pompiste) REFERENCES Pompiste(id) 
);