# Mission EasyTrain1 
Emir SEN, 29/09/2024

## Modèle relationnel

### Utilisateur:
    - utilisateur(id: int(3), login: varchar(20), mdp: varchar(256), nom: varchar(30), prenom: varchar(30), date_embauche: datetime, role: enum('ADMIN', 'EMPLOYE'))
    - clé primaire : id
    - clé étrangère : __
    - champs unique : login

### Arret:
    - arret(id: int(3), nom: varchar(30))
    - clé primaire : id
    - clé étrangère : __
    - champs unique : __

### Trajet
    - trajet(code: varchar(30), temps_depart: datetime, temps_arrivee: datetime, arret_depart_id: int(3), arret_arrivee_id: int(3))
    - clé primaire : code
    - clé étrangère : - arret_depart_id référence à Arret(id)
                      - arret_arrivee_id référence à Arret(id)
    - champs unique : __

## Requête BDD potentiel

### 1/ Récupérer l'utilisateur qui a un login = emiiir_95:
    SELECT * FROM Utilisateur
    WHERE login = 'emiiir_95';

### 2/ Lister les utilisateurs qui sont admin:
    SELECT * FROM Utilisateur 
    WHERE role = 'ADMIN';

### 3/ Récupérer les trajets sur une période donnée:
    SELECT * FROM Trajet 
    WHERE temps_depart BETWEEN '2024-09-20 00:00:00' AND '2024-10-01 23:59:59';

### 4/ Supprimer un employé avec ce login = elPaul:
    DELETE FROM Utilisateur 
    WHERE login = 'elPaul';

### 5/ Ajouter un employé avec des infos:
    INSERT INTO Utilisateur (login, mdp, nom, prenom, date_embauche, role) 
    VALUES ('elPaul', 'monMdpSecret1', 'El', 'Paul', NOW(), 'EMPLOYE');

### 6/ Récupérer le temps d'arrivée d'un trajet avec son code = TRAJET001:
    SELECT temps_arrivee 
    FROM Trajet 
    WHERE code = 'TRAJET001';

### 7/ Modifier le temps d'arrivée d'un trajet avec son code = TRAJET001:
    UPDATE Trajet 
    SET temps_arrivee = '2024-10-15 15:30:00' 
    WHERE code = 'TRAJET001';
