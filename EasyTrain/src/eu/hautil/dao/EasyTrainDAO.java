package eu.hautil.dao;

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
        String query = "INSERT INTO Utilisateur (login, mdp, nom, prenom, date_embauche, role) VALUES (?, ?, ?, ?, ?, ?)";
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
}
