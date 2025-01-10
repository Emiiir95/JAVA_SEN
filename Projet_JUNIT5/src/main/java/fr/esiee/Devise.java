
package fr.esiee;

public class Devise {
    private int quantite;
    private String monnaie;

    public Devise(int somme, String monnaie) {
        this.quantite = somme;
        this.monnaie = monnaie;
    }
    public int getQuantite() {
        return quantite;
    }
    public String getMonnaie() {
        return monnaie;
    }
    public Devise add(Devise m) {
        return new Devise(getQuantite()+m.getQuantite(), getMonnaie());
    }

    @Override
    public boolean equals(Object obj) {
        // Vérifie si l'objet comparé est le même que l'objet actuel
        if (this == obj) {
            return true;
        }
        // Vérifie si l'objet comparé est null ou si ce n'est pas une instance de Devise
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Convertit l'objet en Devise
        Devise devise = (Devise) obj;
        // Vérifie si la quantité et la monnaie sont identiques
        return quantite == devise.quantite && monnaie.equals(devise.monnaie);
    }

}

