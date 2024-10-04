/*
Sen
04/09/2024
TP applicative
* */

package fr.esiee;


import fr.esiee.modele.Role;
import fr.esiee.modele.Utilisateur;
import java.time.LocalDate;
import java.time.LocalDateTime;
import fr.esiee.modele.Arret;
import fr.esiee.modele.Trajet;

public class Main {
    public static void main(String[] args) {

        Utilisateur utilisateur1 = new Utilisateur();

        utilisateur1.setId(1);
        utilisateur1.setLogin("user1");
        utilisateur1.setMdp("password1");
        utilisateur1.setNom("Sen");
        utilisateur1.setPrenom("Emir");
        utilisateur1.setDateEmbauche(LocalDate.of(2024, 2, 2));
        utilisateur1.setRole(Role.EMPLOYE);

        Utilisateur utilisateur2 = new Utilisateur(2, "user2", "password2", "mami", "wata", LocalDate.of(2023, 2, 2), Role.ADMIN);

        System.out.println("Utilisateur 1:");
        System.out.println("ID: " + utilisateur1.getId());
        System.out.println("Login: " + utilisateur1.getLogin());
        System.out.println("Nom: " + utilisateur1.getNom());
        System.out.println("Prénom: " + utilisateur1.getPrenom());
        System.out.println("Date d'embauche: " + utilisateur1.getDateEmbauche());
        System.out.println("Rôle: " + utilisateur1.getRole());

        utilisateur2.setLogin("superadmin");
        System.out.println("\nNouvelle valeur du login de l'utilisateur 2: " + utilisateur2.getLogin());

        System.out.println("\nUtilisateur 2:");
        System.out.println("ID: " + utilisateur2.getId());
        System.out.println("Login: " + utilisateur2.getLogin());
        System.out.println("Nom: " + utilisateur2.getNom());
        System.out.println("Prénom: " + utilisateur2.getPrenom());
        System.out.println("Date d'embauche: " + utilisateur2.getDateEmbauche());
        System.out.println("Rôle: " + utilisateur2.getRole());

        Arret arret1 = new Arret(1, "Gare de Lyon");
        Arret arret2 = new Arret(2, "Lyon");
        Arret arret3 = new Arret(3, "Marseille");
        Arret arret4 = new Arret(4, "Toulouse");

        Trajet trajet1 = new Trajet("train 1", LocalDateTime.of(2024, 9, 29, 10, 0),
                LocalDateTime.of(2024, 9, 29, 10, 0), arret1, arret2);

        Trajet trajet2 = new Trajet("train 2", LocalDateTime.of(2024, 9, 30, 13, 0),
                LocalDateTime.of(2024, 9, 30, 13, 0), arret1, arret3);

        trajet2.setTempsDepart(LocalDateTime.of(2024, 10, 1, 19, 0));
        trajet2.setTempsArrivee(LocalDateTime.of(2024, 10, 1, 19, 0));
        trajet2.setArretArrivee(arret4);

        System.out.println("\nTrajet 1 :");
        System.out.println("Code : " + trajet1.getCode());
        System.out.println("Temps de départ : " + trajet1.getTempsDepart());
        System.out.println("Temps d'arrivée : " + trajet1.getTempsArrivee());
        System.out.println("Arrêt de départ : " + trajet1.getArretDepart().getNom());
        System.out.println("Arrêt d'arrivée : " + trajet1.getArretArrivee().getNom());

        System.out.println("\nTrajet 2 :");
        System.out.println("Code : " + trajet2.getCode());
        System.out.println("Temps de départ : " + trajet2.getTempsDepart());
        System.out.println("Temps d'arrivée : " + trajet2.getTempsArrivee());
        System.out.println("Arrêt de départ : " + trajet2.getArretDepart().getNom());
        System.out.println("Arrêt d'arrivée : " + trajet2.getArretArrivee().getNom());
    }
}
