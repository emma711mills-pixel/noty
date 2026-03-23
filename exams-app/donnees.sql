INSERT INTO Operateur (operateur) VALUES
    ('<'),
    ('<='),
    ('>'),
    ('>=');

INSERT INTO Resolution (nom) VALUES
    ('plus petit'),
    ('plus grand'),
    ('moyenne');


INSERT INTO Candidat (NomCandidat) VALUES
    ('Candidat 3');


INSERT INTO Correcteur (nomCorrecteur) VALUES
    ('Correcteur 1'),
    ('Correcteur 2');

INSERT INTO Matiere (nomMatiere) VALUES
    ('PYTHON');



INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    (1, 4.00, 3, 1),
    (1, 1.00, 3, 3);



INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    (1, 1, 1, 14.50),
    (1, 2, 1, 13.00);