# Git Workflow — Projet Exams

## Étapes effectuées

### 1. Initialisation du dépôt Git
```bash
git init
```
Crée un dépôt Git local dans le répertoire du projet (`d:/fianarana/S6/MrNaina/exams`).

---

### 2. Création du fichier `.gitignore`
Fichier créé pour exclure les fichiers compilés et les logs :
```
exams-app/target/
*.class
*.log
```

---

### 3. Ajout des fichiers de base de données et premier commit (branche `main`)
```bash
git add base.sql donnees.sql .gitignore
git commit -m "initial commit: add database files (base.sql, donnees.sql)"
```
- `base.sql` : script de création de la structure de la base de données.
- `donnees.sql` : script d'insertion des données initiales.

La branche par défaut (`master`) a été renommée en `main` :
```bash
git branch -m master main
```

---

### 4. Création de la branche `develop`
```bash
git checkout -b develop
```
La branche `develop` sert à intégrer les nouvelles fonctionnalités avant de les fusionner dans `main`. C'est la branche de travail principale.

---

### 5. Création de la branche `feature/database-setup`
```bash
git checkout -b feature/database-setup
```
Les branches `feature/*` servent à développer une fonctionnalité spécifique de manière isolée. Une fois la fonctionnalité terminée et testée, elle est fusionnée dans `develop` (via `git merge` ou pull request), puis `develop` est fusionné dans `main` pour une mise en production.

---

## Structure des branches

```
main
 └── develop
      └── feature/database-setup   ← branche active pour modifications futures
```

## Workflow à suivre pour les modifications futures

1. Depuis `develop`, créer une nouvelle branche feature :
   ```bash
   git checkout develop
   git checkout -b feature/nom-de-la-fonctionnalite
   ```
2. Faire les modifications, puis commit :
   ```bash
   git add .
   git commit -m "description des changements"
   ```
3. Fusionner dans `develop` une fois terminé :
   ```bash
   git checkout develop
   git merge feature/nom-de-la-fonctionnalite
   ```
4. Quand `develop` est stable, fusionner dans `main` :
   ```bash
   git checkout main
   git merge develop
   ```
