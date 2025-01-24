package fr.esiee.modele;

public class Arret {
    private int id;
    private String nom;
    private TypeArret typeArret;

    public Arret() {
    }

    public Arret(int id, String nom, TypeArret typeArret) {
        this.id = id;
        this.nom = nom;
        this.typeArret = typeArret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeArret getTypeArret() {
        return typeArret;
    }

    public void setTypeArret(Role role) {
        this.typeArret = typeArret;
    }
}