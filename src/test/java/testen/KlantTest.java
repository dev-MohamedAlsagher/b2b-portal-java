package testen;

import domein.Klant;
import domein.Interface_Klant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KlantTest {

    private Interface_Klant klant;

    @BeforeEach
    void setUp() {
        klant = new Klant("gebruikersnaam", "klantnummer", new String[] { "betaalMethode1", "betaalMethode2" }, "email",
                1);
    }

    @Test
    void testGetIdKlant() {

        int result = klant.getIdKlant();

        assertEquals(0, result);
    }

    @Test
    void testGetGebruikersnaam() {

        String result = klant.getGebruikersnaam();

        assertEquals("gebruikersnaam", result);
    }

    @Test
    void testGetKlantnummer() {

        String result = klant.getKlantnummer();

        assertEquals("klantnummer", result);
    }

    @Test
    void testGetEmail() {

        String result = klant.getEmail();

        assertEquals("email", result);
    }

    @Test
    void testGetIdBedrijf() {

        int result = klant.getIdBedrijf();

        assertEquals(1, result);
    }

    @Test
    void testGetAantalBestellingen() {

        int result = klant.getAantalBestellingen();

        assertEquals(0, result);
    }

    @Test
    void testGetTotaalBestellingen() {

        int result = klant.getTotaalBestellingen();

        assertEquals(0, result);
    }

    @Test
    void testSetAantalBestellingen() {

        int expected = 5;

        klant.setAantalBestellingen(expected);
        int result = klant.getAantalBestellingen();

        assertEquals(expected, result);
    }

    @Test
    void testSetTotaalBestellingen() {

        int expected = 10;

        klant.setTotaalBestellingen(expected);
        int result = klant.getTotaalBestellingen();

        assertEquals(expected, result);
    }

    @Test
    void testSetAantalBestellingenNegative() {

        int expected = -5;

        assertThrows(IllegalArgumentException.class, () -> klant.setAantalBestellingen(expected));
    }

    @Test
    void testSetTotaalBestellingenNegative() {

        int expected = -10;

        assertThrows(IllegalArgumentException.class, () -> klant.setTotaalBestellingen(expected));
    }
}
