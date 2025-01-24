package fr.esiee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviseTest {

    private static int compteurTest = 0;
    private Devise m12CHF;
    private Devise m14CHF;

    @BeforeEach
    void setUp() {
        System.out.println("\nJe suis avant exécution d'une méthode de test");

        compteurTest++;
        System.out.println(compteurTest + "e passage avant exécution d'une méthode de test");

        m12CHF= new Devise(12, "CHF");
        m14CHF= new Devise(14, "CHF");
    }

    @AfterEach
    void tearDown() {
        System.out.println(compteurTest + "e passage APRES exécution d'une méthode de test");
        System.out.println("Je suis APRES exécution d'une méthode de test");
    }

    @Test
    void getQuantite() {
    }

    @Test
    void add() {
        Devise expected = new Devise(26, "CHF");
        Devise result = m12CHF.add(m14CHF);

        assertEquals(expected, result, "La somme des devises n'est pas correcte");
    }

    @Test
    void equals() {
        Devise m14USD = new Devise(14, "USD");

        assertEquals(m12CHF, m12CHF, "m12CHF devrait être égal à lui-même");
        assertNotEquals(m12CHF, m14CHF, "m12CHF ne devrait pas être égal à m14CHF");
        assertNotEquals(m14CHF, m14USD, "m14CHF ne devrait pas être égal à m14USD");
    }
}