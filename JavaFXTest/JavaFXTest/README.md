## Explication d'un projet JavaFX
Emir Sen
29/11/2024

- Ne jamais toucher le dossier .idea sauf si erreur qu'on arrive pas à régler donc supprimer
- Ne jamais toucher le dossier .mvn 
- pom.xml : fichier qui permet de 
  - gérer la compilation et l'éxécution du projet 
  - premret de gérer les dépendances (librairies nécessaires à ajouter)

- module-info.java : directrive des dépendances entre javafx xet les packages java utiles 
- dossier target, peux etre supprimer pour forcer la compilation, réapartition au run du programme 
- Tous les fichiers *.fxml décrivent un panel (plateau d'une certaine organisation pour y ajouter des objets graphiques ou d'autre panel)