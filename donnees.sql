-- Insertion de donnees de test

INSERT INTO Candidat (NomCandidat) VALUES
    ('Alice Martin'),
    ('Bob Dupont'),
    ('Clara Lefebvre'),
    ('David Moreau'),
    ('Emma Bernard');

INSERT INTO Correcteur (nomCorrecteur) VALUES
    ('Prof. Jean Rakoto'),
    ('Prof. Marie Rasoa'),
    ('Prof. Paul Andria');

INSERT INTO Matiere (nomMatiere) VALUES
    ('Mathematiques'),
    ('Physique'),
    ('Informatique'),
    ('Francais'),
    ('Anglais');

INSERT INTO Note (idCandidat, idCorrecteur, idMatiere, valeurNote) VALUES
    -- Candidat 1 - Mathematiques corrige par 3 correcteurs
    (1, 1, 1, 14.50),
    (1, 2, 1, 13.00),
    (1, 3, 1, 15.00),
    -- Candidat 1 - Physique corrige par 2 correcteurs
    (1, 1, 2, 12.00),
    (1, 2, 2, 11.50),
    -- Candidat 1 - Informatique corrige par 2 correcteurs
    (1, 2, 3, 16.75),
    (1, 3, 3, 18.00),

    -- Candidat 2 - Mathematiques corrige par 3 correcteurs
    (2, 1, 1, 10.00),
    (2, 2, 1,  9.00),
    (2, 3, 1, 11.00),
    -- Candidat 2 - Physique corrige par 2 correcteurs
    (2, 1, 2,  8.50),
    (2, 3, 2,  9.50),
    -- Candidat 2 - Francais corrige par 2 correcteurs
    (2, 2, 4, 13.25),
    (2, 3, 4, 12.00),

    -- Candidat 3 - Mathematiques corrige par 2 correcteurs
    (3, 1, 1, 18.00),
    (3, 2, 1, 17.50),
    -- Candidat 3 - Physique corrige par 3 correcteurs
    (3, 1, 2, 15.50),
    (3, 2, 2, 14.00),
    (3, 3, 2, 16.00),
    -- Candidat 3 - Informatique corrige par 2 correcteurs
    (3, 1, 3, 11.00),
    (3, 3, 3, 12.50),

    -- Candidat 4 - Francais corrige par 3 correcteurs
    (4, 1, 4,  9.75),
    (4, 2, 4, 11.00),
    (4, 3, 4, 10.50),
    -- Candidat 4 - Anglais corrige par 2 correcteurs
    (4, 1, 5, 17.00),
    (4, 2, 5, 16.00),

    -- Candidat 5 - Mathematiques corrige par 2 correcteurs
    (5, 1, 1, 13.00),
    (5, 2, 1, 14.00),
    -- Candidat 5 - Informatique corrige par 3 correcteurs
    (5, 1, 3, 14.00),
    (5, 2, 3, 15.50),
    (5, 3, 3, 13.75);

INSERT INTO Operateur (operateur) VALUES
    ('<'),
    ('>'),
    ('=');

INSERT INTO Resolution (nom) VALUES
    ('plus petit'),
    ('plus grand'),
    ('moyenne');

INSERT INTO Parametre (id_Matiere, difference, idOperateur, idResolution) VALUES
    -- Mathematiques (idMatiere=1)
    (1, 3.00, 2, 2),   -- si d > 3 => plus grand
    (1, 3.00, 1, 1),   -- si d < 3 => plus petit
    (1, 3.00, 2, 3),   -- si d > 3 => moyenne  (exemple alternatif)

    -- Physique (idMatiere=2)
    (2, 4.00, 2, 2),   -- si d > 4 => plus grand
    (2, 4.00, 1, 1),   -- si d < 4 => plus petit
    (2, 4.00, 2, 3),   -- si d > 4 => moyenne

    -- Informatique (idMatiere=3)
    (3, 2.00, 2, 2),   -- si d > 2 => plus grand
    (3, 2.00, 1, 3),   -- si d < 2 => moyenne
    (3, 2.00, 1, 1),   -- si d < 2 => plus petit

    -- Francais (idMatiere=4)
    (4, 3.50, 2, 2),   -- si d > 3.5 => plus grand
    (4, 3.50, 1, 1),   -- si d < 3.5 => plus petit
    (4, 3.50, 2, 3),   -- si d > 3.5 => moyenne

    -- Anglais (idMatiere=5)
    (5, 2.50, 2, 2),   -- si d > 2.5 => plus grand
    (5, 2.50, 1, 1),   -- si d < 2.5 => plus petit
    (5, 2.50, 1, 3);   -- si d < 2.5 => moyenne


