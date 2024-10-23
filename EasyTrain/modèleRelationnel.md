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

---

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

## Correction requête BDD potentiel

### Correction 1/ Récupérer l'utilisateur qui a un login = ... et un mdp = ...
    SELECT id, login, mdp, nom, prenom, role, date_embauche
    FROM Utilisateur
    WHERE login = 'unloginparticulier';
    AND mdp = SHA2('lemdpsaisie', 256)

### Correction 2/ Lister les utilisateurs qui sont admin:
    SELECT id, login, mdp, nom, prenom, role, date_embauche 
    FROM Utilisateur 
    WHERE role = 'ADMIN';

### Correction 3/ Récupérer les trajets sur une période donnée:
    SELECT code, temps_depart, temps_arrivee, arret_depart_id, arret_arrivee_id
    FROM Trajet 
    WHERE temps_depart BETWEEN '<date1>' AND '<date2>'
        And temps_arivee BETWEEN '<date1>' AND '<date2>'

#### 3 WOW: lister tous les trajets (toute les infos de trajet, arrets compris)
    SELECT code, temps_depart, temps_arrivee, arret_depart_id, arret_arrivee_id, ad.nom as nomAD, ad.id as idAD, aa.nom as nomAA, aa.id as idAA
    FROM Trajet t, Arret ad, Arret aa
    WHERE ad.id = arret_depart_id 
    	AND aa.id = arret_arrivee_id
        AND temps_depart BETWEEN '<date1>' AND '<date2>'
        And temps_arivee BETWEEN '<date1>' AND '<date2>'

    -- Plus optimiser (jointure)
    SELECT code, temps_depart, temps_arrivee, arret_depart_id, arret_arrivee_id, ad.nom as nomAD, ad.id as idAD, aa.nom as nomAA, aa.id as idAA
    FROM Trajet t JOIN Arret ad ON t.arret_depart_id = ad.id JOIN Arret aa ON t.arret_arrivee_id = aa.id 
    WHERE temps_depart BETWEEN '<date1>' AND '<date2>'
	And temps_arrivee BETWEEN '<date1>' AND '<date2>'

### Correction 4/ Supprimer un employé avec ce login = elPaul:
    DELETE FROM Utilisateur 
    WHERE login = 'elPaul';

### Correction 5/ Ajouter un employé avec des infos:
    INSERT INTO Utilisateur (login, mdp, nom, prenom, date_embauche, role) 
    VALUES ('elPaul', SHA2('elPaul', 256), 'El', 'Paul', NOW(), 'EMPLOYE');

### Correction 6/ Récupérer le temps d'arrivée d'un trajet avec son code = TRAJET001:
    SELECT temps_arrivee 
    FROM Trajet 
    WHERE code = 'TRAJET001';

### Correction 7/ Modifier le temps d'arrivée d'un trajet avec son code = TRAJET001:
    UPDATE Trajet 
    SET temps_arrivee = '2024-10-15 15:30:00' 
    WHERE code = 'TRAJET001';
