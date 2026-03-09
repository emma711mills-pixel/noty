# Ajouter le code source dans Git

## Objectif

Versionner tout le code du projet (`exams-app/`) dans Git, en suivant le workflow défini dans [`GIT_WORKFLOW.md`](GIT_WORKFLOW.md).

---

## Étape 1 — Vérifier l'état actuel

```bash
git status
git branch
```

> S'assurer d'être sur la bonne branche avant de commencer.

---

## Étape 2 — Créer une branche feature depuis develop

```bash
git checkout develop
git checkout -b feature/spring-boot-app
```

---

## Étape 3 — Ajouter tout le code source

```bash
git add exams-app/
git status   # vérifier ce qui va être commité
```

> Le dossier `exams-app/target/` est ignoré grâce au `.gitignore`.

---

## Étape 4 — Commiter

```bash
git commit -m "feat: add Spring Boot application (entities, repositories, services, controller, templates)"
```

---

## Étape 5 — Fusionner dans develop

```bash
git checkout develop
git merge feature/spring-boot-app
git branch -d feature/spring-boot-app
```

---

## Étape 6 — Fusionner dans main (quand stable)

```bash
git checkout main
git merge develop
git tag v1.0.0   # optionnel
```

---

## Vérification finale

```bash
git log --oneline --all --graph
git ls-files   # lister tous les fichiers versionnés
```

---

## Fichiers qui seront versionnés

```
exams-app/pom.xml
exams-app/src/main/java/mg/s6/exams/ExamsAppApplication.java
exams-app/src/main/java/mg/s6/exams/entity/
exams-app/src/main/java/mg/s6/exams/repository/
exams-app/src/main/java/mg/s6/exams/service/
exams-app/src/main/java/mg/s6/exams/controller/
exams-app/src/main/resources/application.properties
exams-app/src/main/resources/templates/index.html
```

> `exams-app/target/` est **exclu** par `.gitignore`.



