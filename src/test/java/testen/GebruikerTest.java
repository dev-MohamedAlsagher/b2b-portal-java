package testen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Gebruiker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GebruikerTest {

    private Gebruiker gebruiker;

    @BeforeEach
    public void setUp() {
        gebruiker = new Gebruiker("username", "password");
    }

    @Test
    public void testGetGebruikersnaam() {

        String username = gebruiker.getGebruikersnaam();

        assertEquals("username", username);
    }

    @Test
    public void testGetPassword_Hash() {

        String password = gebruiker.getPassword_Hash();

        assertEquals("password", password);
    }

    @Test
    public void testGebruikerWithNullUsername() {

        assertThrows(NullPointerException.class, () -> new Gebruiker(null, "password"));
    }

    @Test
    public void testGebruikerWithEmptyUsername() {

        assertThrows(IllegalArgumentException.class, () -> new Gebruiker("", "password"));
    }
}