package fr.esiee.modele;

import java.time.LocalDateTime;

public class Trajet {
    private String code;
    private LocalDateTime tempsDepart;
    private LocalDateTime tempsArrivee;
    private Arret arretDepart;
    private Arret arretArrivee;

    public Trajet() {
    }

    public Trajet(String code, LocalDateTime tempsDepart,LocalDateTime tempsArrivee, Arret arretDepart, Arret arretArrivee) {
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

    public LocalDateTime getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(LocalDateTime tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public LocalDateTime getTempsArrivee() {
        return tempsArrivee;
    }

    public void setTempsArrivee(LocalDateTime tempsArrivee) {
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