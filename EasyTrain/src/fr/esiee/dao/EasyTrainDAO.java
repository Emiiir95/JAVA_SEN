package fr.esiee.dao;

import fr.esiee.modele.Arret;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Role;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EasyTrainDAO {
    String urlLocal = "jdbc:mariadb://localhost:3306/emirsen_easytrain";
    String userLocal = "root";
    String pwdLocal = "root";

    private Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
    }

    // Récupérer un utilisateur par son identifiant unique
    public Utilisateur getUtilisateurById(int id) {
        String query = "SELECT * FROM Utilisateur WHERE id = ?";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUtilisateur(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer la liste de tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM Utilisateur";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                utilisateurs.add(mapUtilisateur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    // Ajouter un utilisateur et récupérer son ID généré
    public boolean ajouterUtilisateur(Utilisateur u) {
        String query = "INSERT INTO Utilisateur (login, mdp, nom, prenom, date_embauche, role) VALUES (?, SHA2(?, 256), ?, ?, ?, ?)";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getMdp());
            stmt.setString(3, u.getNom());
            stmt.setString(4, u.getPrenom());
            stmt.setDate(5, Date.valueOf(u.getDateEmbauche()));
            stmt.setString(6, u.getRole().name());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        u.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer un arrêt par son identifiant unique
    public Arret getArretById(int id) {
        String query = "SELECT * FROM Arret WHERE id = ?";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Arret(rs.getInt("id"), rs.getString("nom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer la liste de tous les arrêts
    public List<Arret> getAllArrets() {
        List<Arret> arrets = new ArrayList<>();
        String query = "SELECT * FROM Arret";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                arrets.add(new Arret(rs.getInt("id"), rs.getString("nom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrets;
    }

    // Ajouter un arrêt et récupérer son ID généré
    public boolean ajouterArret(Arret a) {
        String query = "INSERT INTO Arret (nom) VALUES (?)";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, a.getNom());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        a.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode privée pour mapper un ResultSet à un objet Utilisateur
    private Utilisateur mapUtilisateur(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String login = rs.getString("login");
        String mdp = rs.getString("mdp");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        LocalDate dateEmbauche = rs.getDate("date_embauche").toLocalDate();
        Role role = Role.valueOf(rs.getString("role"));
        return new Utilisateur(id, login, mdp, nom, prenom, dateEmbauche, role);
    }

    // Récupérer un trajet par son identifiant unique
    public Trajet getTrajetById(String code) {
        String query = "SELECT t.*, a1.nom AS depart_nom, a2.nom AS arrivee_nom " +
                "FROM Trajet t " +
                "JOIN Arret a1 ON t.arret_depart_id = a1.id " +
                "JOIN Arret a2 ON t.arret_arrivee_id = a2.id " +
                "WHERE t.code = ?";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Arret arretDepart = new Arret(rs.getInt("arret_depart_id"), rs.getString("depart_nom"));
                    Arret arretArrivee = new Arret(rs.getInt("arret_arrivee_id"), rs.getString("arrivee_nom"));
                    return new Trajet(rs.getString("code"),
                            rs.getTimestamp("temps_depart").toLocalDateTime(),
                            rs.getTimestamp("temps_arrivee").toLocalDateTime(),
                            arretDepart, arretArrivee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Récupérer la liste de tous les trajets et leurs arrêts respectifs
    public List<Trajet> getAllTrajets() {
        List<Trajet> trajets = new ArrayList<>();
        String query = "SELECT t.*, a1.nom AS depart_nom, a2.nom AS arrivee_nom " +
                "FROM Trajet t " +
                "JOIN Arret a1 ON t.arret_depart_id = a1.id " +
                "JOIN Arret a2 ON t.arret_arrivee_id = a2.id";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Arret arretDepart = new Arret(rs.getInt("arret_depart_id"), rs.getString("depart_nom"));
                Arret arretArrivee = new Arret(rs.getInt("arret_arrivee_id"), rs.getString("arrivee_nom"));
                trajets.add(new Trajet(rs.getString("code"),
                        rs.getTimestamp("temps_depart").toLocalDateTime(),
                        rs.getTimestamp("temps_arrivee").toLocalDateTime(),
                        arretDepart, arretArrivee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }


    // Ajouter un trajet
    public boolean ajouterTrajet(Trajet t) {
        String query = "INSERT INTO Trajet (code, temps_depart, temps_arrivee, arret_depart_id, arret_arrivee_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnexion();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, t.getCode());
            stmt.setTimestamp(2, Timestamp.valueOf(t.getTempsDepart()));
            stmt.setTimestamp(3, Timestamp.valueOf(t.getTempsArrivee()));
            stmt.setInt(4, t.getArretDepart().getId());
            stmt.setInt(5, t.getArretArrivee().getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
