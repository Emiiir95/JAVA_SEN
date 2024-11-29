package fr.esiee;

import java.sql.*;
import java.util.*;

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

            Scanner scanner = new Scanner(System.in);
            int choix;

            do {
                System.out.println("Choisissez une action :");
                System.out.println("1 - Ajouter un nouvel utilisateur");
                System.out.println("2 - Modifier un utilisateur");
                System.out.println("3 - Supprimer un utilisateur");
                System.out.println("4 - Afficher les utilisateurs");
                System.out.println("5 - Afficher un utilisateur par l'Id");
                System.out.println("0 - Quitter");
                System.out.print("Votre choix: ");
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        ajouterUtilisateur(scanner, connectionLocal);
                        break;
                    case 2:
                        modifierUtilisateur(scanner, connectionLocal);
                        break;
                    case 3:
                        supprimerUtilisateur(scanner, connectionLocal);
                        break;
                    case 4:
                        afficherUtilisateurs(connectionLocal);
                        break;
                    case 5:
                        selectById(scanner, connectionLocal);
                        break;
                    case 0:
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (choix != 0);

            scanner.close();

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
                synchronizeDatabases(connectionLocal, connectionDistant);
                if (connectionLocal != null) connectionLocal.close();
                if (connectionDistant != null) connectionDistant.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void ajouterUtilisateur(Scanner scanner, Connection connection) {
        try {
            System.out.print("Entrez le login : ");
            String login = scanner.nextLine();
            System.out.print("Entrez le mot de passe : ");
            String mdp = scanner.nextLine();
            System.out.print("Entrez le nom : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez le prénom : ");
            String prenom = scanner.nextLine();

            String role = "";
            boolean validRole = false;
            while (!validRole) {
                System.out.print("Choisissez un rôle (1 - ADMIN / 2 - EMPLOYE) : ");
                int choixRole = scanner.nextInt();
                scanner.nextLine();
                if (choixRole == 1) {
                    role = "ADMIN";
                    validRole = true;
                } else if (choixRole == 2) {
                    role = "EMPLOYE";
                    validRole = true;
                } else {
                    System.out.println("Rôle invalide. Veuillez entrer 1 pour ADMIN ou 2 pour EMPLOYEE.");
                }
            }


            String req = "INSERT INTO Utilisateur (login, mdp, nom, prenom, role, date_embauche) VALUES (?, SHA2(?, 256), ?, ?, ?, NOW())";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, login);
            pstmt.setString(2, mdp);
            pstmt.setString(3, nom);
            pstmt.setString(4, prenom);
            pstmt.setString(5, role);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Utilisateur ajouté avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout de l'utilisateur.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    private static void modifierUtilisateur(Scanner scanner, Connection connection) {
        try {
            System.out.print("Entrez le login de l'utilisateur à modifier : ");
            String login = scanner.nextLine();

            // Vérifier si l'utilisateur existe déjà
            String verificationReq = "SELECT COUNT(login) FROM Utilisateur WHERE login = ?";
            PreparedStatement verificationStmt = connection.prepareStatement(verificationReq);
            verificationStmt.setString(1, login);
            ResultSet verificationResult = verificationStmt.executeQuery();

            if (verificationResult.next() && verificationResult.getInt(1) == 0) {
                System.out.println("Aucun utilisateur trouvé avec ce login.");
                verificationResult.close();
                verificationStmt.close();
                return;
            }

            System.out.print("Entrez le nouveau login : ");
            String nouveauLogin = scanner.nextLine();

            // Vérifier si le nouveau login est déjà utilisé
            String nouveauLoginVerificationReq = "SELECT COUNT(login) FROM Utilisateur WHERE login = ?";
            PreparedStatement nouveauLoginVerificationStmt = connection.prepareStatement(nouveauLoginVerificationReq);
            nouveauLoginVerificationStmt.setString(1, nouveauLogin);
            ResultSet nouveauLoginVerificationResult = nouveauLoginVerificationStmt.executeQuery();

            if (nouveauLoginVerificationResult.next() && nouveauLoginVerificationResult.getInt(1) > 0) {
                System.out.println("Ce nouveau login est déjà utilisé. Veuillez en choisir un autre.");
                nouveauLoginVerificationResult.close();
                nouveauLoginVerificationStmt.close();
                return;
            }

            // Mettez à jour l'utilisateur
            String req = "UPDATE Utilisateur SET login = ? WHERE login = ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, nouveauLogin);
            pstmt.setString(2, login);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Utilisateur modifié avec succès !");
            } else {
                System.out.println("Erreur lors de la modification de l'utilisateur.");
            }

            nouveauLoginVerificationResult.close();
            nouveauLoginVerificationStmt.close();
            verificationResult.close();
            verificationStmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }

    private static void supprimerUtilisateur(Scanner scanner, Connection connection) {
        try {
            System.out.print("Entrez le login de l'utilisateur à supprimer : ");
            String login = scanner.nextLine();

            String req = "DELETE FROM Utilisateur WHERE login = ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, login);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec ce login.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    private static void afficherUtilisateurs(Connection connection) {
        try {
            String req = "SELECT id, login, prenom, nom, role FROM Utilisateur";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(req);

            System.out.println("\n---------- Voici les utilisateurs ----------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                String role = resultSet.getString("role");
                System.out.println("Id: " + id +", Login: " + login + ", Prénom: " + prenom + ", Nom: " + nom + ", Role: " + role);
            }
            System.out.println("--------------------------------------------\n");

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des utilisateurs : " + e.getMessage());
        }
    }

    private static void selectById(Scanner scanner, Connection connection) {
        try {
            System.out.print("Entrez l'ID de l'utilisateur : ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String req = "SELECT login, prenom, nom, role FROM Utilisateur WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                String role = resultSet.getString("role");

                System.out.println("Détails de l'utilisateur :");
                System.out.println("Login : " + login);
                System.out.println("Prénom : " + prenom);
                System.out.println("Nom : " + nom);
                System.out.println("Rôle : " + role);
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sélection de l'utilisateur : " + e.getMessage());
        }
    }

    private static void synchronizeDatabases(Connection connectionLocal, Connection connectionDistant) {
        try {
            // Récupérer tous les utilisateurs de la base de données locale
            String reqLocal = "SELECT * FROM Utilisateur";
            Statement stmtLocal = connectionLocal.createStatement();
            ResultSet resultSetLocal = stmtLocal.executeQuery(reqLocal);

            // Stocker les utilisateurs locaux
            Map<String, String[]> localUsers = new HashMap<>();

            while (resultSetLocal.next()) {
                String login = resultSetLocal.getString("login");
                String mdp = resultSetLocal.getString("mdp");
                String nom = resultSetLocal.getString("nom");
                String prenom = resultSetLocal.getString("prenom");
                String role = resultSetLocal.getString("role");

                // Ajouter à la map
                localUsers.put(login, new String[]{mdp, nom, prenom, role});
            }

            // Récupérer tous les utilisateurs de la base de données distante
            String reqDistant = "SELECT * FROM Utilisateur";
            Statement stmtDistant = connectionDistant.createStatement();
            ResultSet resultSetDistant = stmtDistant.executeQuery(reqDistant);

            // Stocker les logins des utilisateurs distants
            Set<String> distantLogins = new HashSet<>();

            while (resultSetDistant.next()) {
                String distantLogin = resultSetDistant.getString("login");
                distantLogins.add(distantLogin);
            }

            // Synchroniser la base de données distante
            for (Map.Entry<String, String[]> entry : localUsers.entrySet()) {
                String login = entry.getKey();
                String[] userData = entry.getValue();

                // Vérifier si l'utilisateur existe déjà dans la base de données distante
                if (distantLogins.contains(login)) {
                    // Mettre à jour l'utilisateur dans la base de données distante
                    String updateReq = "UPDATE Utilisateur SET mdp = SHA2(?, 256), nom = ?, prenom = ?, role = ? WHERE login = ?";
                    PreparedStatement updateStmt = connectionDistant.prepareStatement(updateReq);
                    updateStmt.setString(1, userData[0]);
                    updateStmt.setString(2, userData[1]);
                    updateStmt.setString(3, userData[2]);
                    updateStmt.setString(4, userData[3]);
                    updateStmt.setString(5, login);
                    updateStmt.executeUpdate();
                } else {
                    // Ajouter l'utilisateur à la base de données distante
                    String insertReq = "INSERT INTO Utilisateur (login, mdp, nom, prenom, role, date_embauche) VALUES (?, SHA2(?, 256), ?, ?, ?, NOW())";
                    PreparedStatement insertStmt = connectionDistant.prepareStatement(insertReq);
                    insertStmt.setString(1, login);
                    insertStmt.setString(2, userData[0]);
                    insertStmt.setString(3, userData[1]);
                    insertStmt.setString(4, userData[2]);
                    insertStmt.setString(5, userData[3]);
                    insertStmt.executeUpdate();
                }
            }

            // Supprimer les utilisateurs qui ne sont plus dans la base de données locale
            for (String distantLogin : distantLogins) {
                if (!localUsers.containsKey(distantLogin)) {
                    String deleteReq = "DELETE FROM Utilisateur WHERE login = ?";
                    PreparedStatement deleteStmt = connectionDistant.prepareStatement(deleteReq);
                    deleteStmt.setString(1, distantLogin);
                    deleteStmt.executeUpdate();
                }
            }

            // Fermez les ressources
            resultSetLocal.close();
            stmtLocal.close();
            resultSetDistant.close();
            stmtDistant.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la synchronisation des bases de données : " + e.getMessage());
        }
    }


}