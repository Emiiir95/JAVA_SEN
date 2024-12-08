package eu.hautil;

import eu.hautil.dao.EasyTrainDAO;
import fr.esiee.modele.Arret;
import fr.esiee.modele.Trajet;
import fr.esiee.modele.Utilisateur;
import fr.esiee.modele.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        // Test de la méthode getArretById
        System.out.println("\n===== Test de getArretById =====");
        Arret arret = dao.getArretById(1);
        if (arret != null) {
            System.out.println("Arrêt trouvé : " + arret.getNom());
        } else {
            System.out.println("Aucun arrêt trouvé avec cet ID.");
        }

        // Test de la méthode getAllArrets
        System.out.println("\n===== Test de getAllArrets =====");
        List<Arret> arrets = dao.getAllArrets();
        if (!arrets.isEmpty()) {
            arrets.forEach(a -> System.out.println("Arrêt : " + a.getNom()));
        } else {
            System.out.println("Aucun arrêt trouvé dans la base de données.");
        }

        // Test de la méthode ajouterArret
        System.out.println("\n===== Test de ajouterArret =====");
        Arret nouvelArret = new Arret(0, "Paris Gare de Lyon");
        boolean isArretAdded = dao.ajouterArret(nouvelArret);
        if (isArretAdded) {
            System.out.println("Arrêt ajouté avec succès : ID généré = " + nouvelArret.getId());
        } else {
            System.out.println("Échec de l'ajout de l'arrêt.");
        }

        // Vérification que l'arrêt ajouté est présent
        System.out.println("\n===== Vérification de l'ajout d'un arrêt =====");
        Arret arretAjoute = dao.getArretById(nouvelArret.getId());
        if (arretAjoute != null) {
            System.out.println("Nouvel arrêt trouvé : " + arretAjoute.getNom());
        } else {
            System.out.println("Nouvel arrêt non trouvé.");
        }

        String trajetCode = "TRAJET001";  // Utilisez un code de trajet valide
        Trajet trajet = dao.getTrajetById(trajetCode);
        if (trajet != null) {
            System.out.println("Trajet récupéré par ID : " + trajet);
        } else {
            System.out.println("Aucun trajet trouvé avec ce code : " + trajetCode);
        }

        // Test de la méthode getAllTrajets
        List<Trajet> trajets = dao.getAllTrajets();
        System.out.println("Liste de tous les trajets : ");
        for (Trajet t : trajets) {
            System.out.println(t);
        }

        // Test de la méthode ajouterTrajet avec LocalDateTime
        Arret arretDepart = new Arret(1, "Gare Saint-Lazare");
        Arret arretArrivee = new Arret(2, "Gare de Lyon");

        // Conversion des chaînes de caractères en LocalDateTime
        String stringTempsDepart = "2024-12-09 08:00:00";
        String stringTempsArrivee = "2024-12-09 09:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime tempsDepart = LocalDateTime.parse(stringTempsDepart, formatter);
        LocalDateTime tempsArrivee = LocalDateTime.parse(stringTempsArrivee, formatter);

        // Création d'un nouveau trajet avec LocalDateTime
        Trajet nouveauTrajet = new Trajet("TRAJET004", tempsDepart, tempsArrivee, arretDepart, arretArrivee);

        boolean ajoutReussi = dao.ajouterTrajet(nouveauTrajet);
        if (ajoutReussi) {
            System.out.println("Trajet ajouté avec succès !");
        } else {
            System.out.println("Échec de l'ajout du trajet.");
        }
    }
}
