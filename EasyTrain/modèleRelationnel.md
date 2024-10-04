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
    

