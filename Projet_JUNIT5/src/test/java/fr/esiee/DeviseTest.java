package fr.esiee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getQuantite() {
    }

    @Test
    void add() {
        Devise m12CHF= new Devise(12, "CHF");
        Devise m14CHF= new Devise(14, "CHF");
        Devise expected = new Devise(26, "CHF");
        Devise result = m12CHF.add(m14CHF);

        assertEquals(expected, result, "La somme des devises n'est pas correcte");
    }
}