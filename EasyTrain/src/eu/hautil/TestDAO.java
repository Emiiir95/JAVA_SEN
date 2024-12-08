package eu.hautil;

import eu.hautil.dao.EasyTrainDAO;
import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Role;

import java.time.LocalDate;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        EasyTrainDAO dao = new EasyTrainDAO();

        // Test de la méthode getUtilisateurById
        System.out.println("===== Test de getUtilisateurById =====");
        Utilisateur utilisateur = dao.getUtilisateurById(1);
        if (utilisateur != null) {
            System.out.println("Utilisateur trouvé : " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet ID.");
        }

        // Test de la méthode getAllUtilisateurs
        System.out.println("\n===== Test de getAllUtilisateurs =====");
        List<Utilisateur> utilisateurs = dao.getAllUtilisateurs();
        if (!utilisateurs.isEmpty()) {
            utilisateurs.forEach(u -> System.out.println("Utilisateur : " + u.getNom() + " " + u.getPrenom()));
        } else {
            System.out.println("Aucun utilisateur trouvé dans la base de données.");
        }

        // Test de la méthode ajouterUtilisateur
        System.out.println("\n===== Test de ajouterUtilisateur =====");
        Utilisateur nouvelUtilisateur = new Utilisateur(0, "jdoe", "password123", "Doe", "John", LocalDate.now(), Role.EMPLOYE);
        boolean isAdded = dao.ajouterUtilisateur(nouvelUtilisateur);
        if (isAdded) {
            System.out.println("Utilisateur ajouté avec succès : ID généré = " + nouvelUtilisateur.getId());
        } else {
            System.out.println("Échec de l'ajout de l'utilisateur.");
        }

        // Vérification que l'utilisateur ajouté est présent
        System.out.println("\n===== Vérification de l'ajout =====");
        Utilisateur utilisateurAjoute = dao.getUtilisateurById(nouvelUtilisateur.getId());
        if (utilisateurAjoute != null) {
            System.out.println("Nouvel utilisateur trouvé : " + utilisateurAjoute.getNom() + " " + utilisateurAjoute.getPrenom());
        } else {
            System.out.println("Nouvel utilisateur non trouvé.");
        }
    }
}
