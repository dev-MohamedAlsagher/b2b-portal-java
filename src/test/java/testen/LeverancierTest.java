package testen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_GoedKeuringLeverancier;
import domein.Interface_Leverancier;
import domein.Klant;
import domein.Leverancier;
import domein.Observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeverancierTest {

    private Interface_Leverancier leverancier;
    private Bedrijf bedrijf;
    private Observer observer;

    @BeforeEach
    public void setUp() {
        leverancier = new Leverancier("username", "password", "123", "method1, method2", "roles", 1, "test@email.com");
        bedrijf = new Bedrijf();
        observer = new Observer() {
            @Override
            public void update(Interface_Leverancier leverancier) {
            }

            @Override
            public void update(Interface_Bestelling bestelling) {
            }

            @Override
            public void update(Interface_GoedKeuringLeverancier goedKeuringLeverancier) {
            }

            @Override
            public void update(Interface_Bedrijf bedrijf) {
            }

            @Override
            public void update(Klant klant) {

            }
        };
    }

    @Test
    public void testGetIdLeverancier() {

        int id = leverancier.getIdLeverancier();

        assertEquals(1, id);
    }

    @Test
    public void testGetLeveranciernummer() {

        String nummer = leverancier.getLeveranciernummer();

        assertEquals("123", nummer);
    }

    @Test
    public void testGetBetaalMethodes() {

        String methodes = leverancier.getBetaalMethodes();

        assertEquals("method1, method2", methodes);
    }

    @Test
    public void testGetEmail() {

        String email = leverancier.getEmail();

        assertEquals("test@email.com", email);
    }

    @Test
    public void testLeverancierWithNullUsername() {

        assertThrows(NullPointerException.class,
                () -> new Leverancier(null, "password", "123", "method1, method2", "roles", 1, "test@email.com"));
    }

    @Test
    public void testLeverancierWithEmptyUsername() {

        assertThrows(IllegalArgumentException.class,
                () -> new Leverancier("", "password", "123", "method1, method2", "roles", 1, "test@email.com"));
    }

    @Test
    public void testSetBetaalMethodes() {

        leverancier.setBetaalMethodes("method3, method4");

        assertEquals("method3, method4", leverancier.getBetaalMethodes());
    }

    @Test
    public void testSetBedrijf() {

        leverancier.setBedrijf(bedrijf);

        assertEquals(bedrijf, leverancier.getBedrijf());
    }

    @Test
    public void testAddObserver() {

        leverancier.addObserver(observer);

        assertNotNull(leverancier);
    }

    @Test
    public void testRemoveObserver() {

        leverancier.addObserver(observer);

        leverancier.removeObserver(observer);

        assertNotNull(leverancier);
    }

    @Test
    public void testGetBedrijf() {

        leverancier.setBedrijf(bedrijf);

        Bedrijf result = leverancier.getBedrijf();

        assertEquals(bedrijf, result);
    }

    @Test
    public void testGetBetaalMethodesReturnsEmptyStringWhenNull() {

        leverancier.setBetaalMethodes(null);

        String methodes = leverancier.getBetaalMethodes();

        assertEquals("", methodes);
    }

}