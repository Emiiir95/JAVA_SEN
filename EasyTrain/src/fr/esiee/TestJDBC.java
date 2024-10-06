package fr.esiee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class TestJDBC {
    public static void main(String[] args) {
        String urlLocal = "jdbc:mariadb://localhost:3306/easyTrain";
        String userLocal = "root";
        String pwdLocal = "root";

        String urlDistant = "jdbc:mariadb://mysql-emirsen.alwaysdata.net:3306/emirsen_easytrain";
        String userDistant = "emirsen";
        String pwdDistant = "Jesuistoutennoir95";

        Connection connectionLocal = null;
        Connection connectionDistant = null;

        // avant de run, commande pour lancer la BDD dans le terminal
        // > brew services start mariadb
        // Pour fermer la BDD
        // > brew services stop mariadb

        try {
            // Création d'une connexion à la BDD locale
            connectionLocal = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion OK à la base de données locale");
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données locale : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            // Connexion à la base de données distante
            connectionDistant = DriverManager.getConnection(urlDistant, userDistant, pwdDistant);
            System.out.println("Connexion OK à la base de données distante");
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données distante : " + e.getMessage());
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
