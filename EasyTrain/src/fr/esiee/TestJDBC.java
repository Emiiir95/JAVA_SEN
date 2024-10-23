package fr.esiee;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) {
        String urlLocal = "jdbc:mariadb://localhost:3306/emirsen_easytrain";
        String userLocal = "root";
        String pwdLocal = "root";

        String urlDistant = "jdbc:mariadb://mysql-emirsen.alwaysdata.net:3306/emirsen_easytrain";
        String userDistant = "emirsen";
        String pwdDistant = "Jesuistoutennoir95";

        Connection connectionLocal = null;
        Connection connectionDistant = null;

        try {
            // Création d'une connexion à la BDD locale
            connectionLocal = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion OK à la base de données locale\n");

            // Création de la requête
            Statement stmt = connectionLocal.createStatement();

            // Vérifier s'il existe un utilisateur avec le login 'test'
            String req1 = "SELECT COUNT(login) AS count FROM Utilisateur WHERE nom = 'test';";
            ResultSet resultReq1 = stmt.executeQuery(req1);

            // Si un utilisateur est trouvé avec le nom 'test', effectuer la suppression
            if (resultReq1.next() && resultReq1.getInt("count") > 0) {
                System.out.println("----------> Utilisateur 'test' trouvé, suppression en cours...");

                // Supprimer un utilisateur
                String req2 = "DELETE FROM Utilisateur WHERE login = 'test';";
                int resultReq2 = stmt.executeUpdate(req2);

                System.out.println("----------> Utilisateur supprimé : " + resultReq2);
            } else {
                System.out.println("----------> Aucun utilisateur avec le nom 'test' trouvé, suppression ignorée.");
            }

            // Ajouter un utilisateur
            String req3 = "INSERT INTO Utilisateur (login, mdp, nom, prenom, date_embauche, role) VALUES ('test', SHA2('test', 256), 'test', 'test', NOW(), 'EMPLOYE');";
            int resultReq3 = stmt.executeUpdate(req3);
            System.out.println("----------> Utilisateur avec le login = 'test' ajouté");

            // Update un utilisateur
            String req4 = "UPDATE Utilisateur SET prenom = 'update' WHERE nom = 'test';";
            int resultReq4 = stmt.executeUpdate(req4);
            System.out.println("----------> Utilisateur avec le login = 'test' modifié");

            // Récupérer les utilisateurs
            String req5 = "SELECT prenom, nom FROM Utilisateur";
            ResultSet resultReq5 = stmt.executeQuery(req5);

            // Parcourir les résultats et les afficher
            System.out.println("\n---------- Voici les utilisateurs ----------");
            while (resultReq5.next()) {
                String prenom = resultReq5.getString("prenom");
                String nom = resultReq5.getString("nom");
                // Afficher les valeurs
                System.out.println("Prénom: " + prenom + ", Nom: " + nom);
            }
            System.out.println("--------------------------------------------\n");

            // Fermer le ResultSet
            resultReq5.close();
            // Fermer le Statement
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données locale : " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        try {
            // Connexion à la base de données distante
            connectionDistant = DriverManager.getConnection(urlDistant, userDistant, pwdDistant);
            System.out.println("\nConnexion OK à la base de données distante\n");
        } catch (SQLException e) {
            System.err.println("\nÉchec de la connexion à la base de données distante : " + e.getMessage() + "\n");
            e.printStackTrace();
        } finally {
            // Fermeture des connexions
            try {
                if (connectionLocal != null) connectionLocal.close();
                if (connectionDistant != null) connectionDistant.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
