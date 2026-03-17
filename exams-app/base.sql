-- exams_db.sql
DROP DATABASE exams_db;
CREATE DATABASE exams_db;

\c exams_db;

-- Création des tables
CREATE TABLE Candidat (
    idCandidat SERIAL PRIMARY KEY,
    NomCandidat VARCHAR(100) NOT NULL
);

CREATE TABLE Correcteur (
    idCorrecteur SERIAL PRIMARY KEY,
    nomCorrecteur VARCHAR(100) NOT NULL
);

CREATE TABLE Matiere (
    idMatiere SERIAL PRIMARY KEY,
    nomMatiere VARCHAR(100) NOT NULL
);

CREATE TABLE Note (
    idNote SERIAL PRIMARY KEY,
    idCandidat INT NOT NULL REFERENCES Candidat(idCandidat),
    idCorrecteur INT NOT NULL REFERENCES Correcteur(idCorrecteur),
    idMatiere INT NOT NULL REFERENCES Matiere(idMatiere),
    valeurNote NUMERIC(5,2) NOT NULL
);

CREATE TABLE Operateur (
    idOperateur SERIAL PRIMARY KEY,
    operateur VARCHAR(2) NOT NULL CHECK (operateur IN ('<', '>', '<=', '>='))
);

CREATE TABLE Resolution (
    idResolution SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL CHECK (nom IN ('plus petit', 'plus grand', 'moyenne'))
);

CREATE TABLE Parametre (
    idParametre SERIAL PRIMARY KEY,
    id_Matiere INT NOT NULL REFERENCES Matiere(idMatiere),
    difference NUMERIC(5,2) NOT NULL,
    idOperateur INT NOT NULL REFERENCES Operateur(idOperateur),
    idResolution INT NOT NULL REFERENCES Resolution(idResolution)
);

-- Insertion des données
INSERT INTO Candidat (NomCandidat) VALUES
    ('Candidat 1'),
    ('Candidat 2');

INSERT INTO Correcteur (nomCorrecteur) VALUES
    ('Correcteur 1'),
    ('Correcteur 2'),
    ('Correcteur 3');

INSERT INTO Matiere (nomMatiere) VALUES
    ('JAVA'),
    ('PHP');

INSERT INTO Operateur (operateur) VALUES
    ('<'),
    ('<='),
    ('>'),
    ('>=');

INSERT INTO Resolution (nom) VALUES
    ('plus petit'),
    ('plus grand'),
    ('moyenne');

INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    (1, 7.00, 1, 2),
    (1, 7.00, 4, 3),
    (2, 2.00, 2, 1),
    (2, 2.00, 3, 2);

INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    (1, 1, 1, 15.00),
    (1, 2, 1, 10.00),
    (1, 3, 1, 12.00),
    (2, 1, 1, 9.00),
    (2, 2, 1, 8.00),
    (2, 3, 1, 11.00),
    (1, 1, 2, 10.00),
    (1, 2, 2, 10.00),
    (2, 1, 2, 13.00),
    (2, 2, 2, 11.00);



INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    (1, 3.00, 3, 1),
    (1, 8.00, 1, 2);

INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    (1, 1, 1, 10.00),
    (1, 2, 1, 15.00);



INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    (2, 3.00, 3, 1),
    (2, 8.00, 1, 2);


INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    (1, 1, 2, 0.00),
    (1, 2, 2, 5.50);



INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    (1, 3.00, 3, 1),
    (1, 6.00, 1, 2);


INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    (2, 1, 1, 0.00),
    (2, 2, 1, 5.50);