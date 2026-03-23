# Git Workflow — Projet Exams

## Structure des branches

```
main        ← production stable
 └── develop ← intégration des features
      ├── feature/<nom>  ← nouvelle fonctionnalité
      └── hotfix/<nom>   ← correction urgente (merge aussi dans main)
```

---

## Étape 1 — Initialisation

```bash
git init
git add .
git commit -m "initial commit"
git branch -m master main        # renommer master → main
git checkout -b develop          # créer la branche develop
```

---

## Étape 2 — Nouvelle fonctionnalité (feature)

```bash
# 1. Partir de develop
git checkout develop
git checkout -b feature/ma-fonctionnalite

# 2. Coder, puis commit
git add .
git commit -m "feat: description"

# 3. Fusionner dans develop
git checkout develop
git merge feature/ma-fonctionnalite
git branch -d feature/ma-fonctionnalite

# 4. Quand develop est stable → fusionner dans main
git checkout main
git merge develop
git tag v1.0.0   # optionnel : taguer la version
```

---

## Étape 3 — Correction urgente (hotfix)

```bash
# 1. Partir de main
git checkout main
git checkout -b hotfix/ma-correction

# 2. Corriger, puis commit
git add .
git commit -m "fix: description"

# 3. Fusionner dans main ET dans develop
git checkout main
git merge hotfix/ma-correction

git checkout develop
git merge hotfix/ma-correction

git branch -d hotfix/ma-correction
```

---

## Commandes utiles

```bash
git log --oneline --all --graph   # historique visuel
git branch -v                      # lister les branches
git status                         # état du dépôt
```
