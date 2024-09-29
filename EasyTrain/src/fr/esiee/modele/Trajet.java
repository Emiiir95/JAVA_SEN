package fr.esiee.modele;

import java.time.LocalDate;

public class Trajet {
    private String code;
    private LocalDate tempsDepart;
    private LocalDate tempsArrivee;
    private Arret arretDepart;
    private Arret arretArrivee;

    public Trajet() {
    }

    public Trajet(String code, LocalDate tempsDepart,LocalDate tempsArrivee, Arret arretDepart, Arret arretArrivee) {
        this.code = code;
        this.tempsDepart = tempsDepart;
        this.tempsArrivee = tempsArrivee;
        this.arretDepart = arretDepart;
        this.arretArrivee = arretArrivee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(LocalDate tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public LocalDate getTempsArrivee() {
        return tempsArrivee;
    }

    public void setTempsArrivee(LocalDate tempsArrivee) {
        this.tempsArrivee = tempsArrivee;
    }

    public Arret getArretDepart() {
        return arretDepart;
    }

    public void setArretDepart(Arret arretDepart) {
        this.arretDepart = arretDepart;
    }

    public Arret getArretArrivee() {
        return arretArrivee;
    }

    public void setArretArrivee(Arret arretArrivee) {
        this.arretArrivee = arretArrivee;
    }
}