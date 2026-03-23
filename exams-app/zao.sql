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
