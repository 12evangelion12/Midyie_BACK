### PARTIE BACK PROJET MIDYIE

#### Technologies utilisées

- Java (comme langage principal)
- Javalin (framework permettant de réaliser des API REST en JAVA)
- Maven (pour la gestion des dépendances)
- SQL (pour la gestion de la base de donnée)

Une base de donnée MariaDB à été installé sur un serveur distant, le back fait office d'api et
réalise des requêtes sur cette base.

#### Fonctionnalités implémentées

- Gestion des comptes pour la connexion à l'application
- Gestion des items (méthode get, post, put, delete);
- Gestion des propriétés des items (méthodes get, post, put, delete);
- Gestion de la base de donnée (avec des classes distinctes et des fichiers de configuration)
- Gestion des logs avec un Logback
- Méthode de chiffrement de donner pour les mots de passe