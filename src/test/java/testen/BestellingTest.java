package testen;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import domein.Bestelling;

public class BestellingTest {
    private Bestelling bestelling;

    @BeforeEach
    public void setUp() {
        bestelling = new Bestelling();
    }

    @AfterEach
    public void tearDown() {
        bestelling = null;
    }

    @Test
    public void testGetIdOrder() {
        String expectedId = "123";
        bestelling.setIdOrder(expectedId);
        String actualId = bestelling.getIdOrder();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetIdKlant() {
        int expectedId = 1;
        bestelling.setIdKlant(expectedId);
        int actualId = bestelling.getIdKlant();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetDatum() {
        Date expectedDate = new Date();
        bestelling.setDatum(expectedDate);
        String actualDate = bestelling.getDatum();
        assertNotNull(actualDate);
    }

    @Test
    public void testGetBetalingStatus() {
        bestelling.setBetalingStatus(true);
        String actualStatus = bestelling.getBetalingStatus();
        assertEquals("Betaald", actualStatus);
    }

    @Test
    public void testUpdateBetalingStatus() {
        bestelling.setBetalingStatus(false);
        bestelling.updateBetalingStatus(true);
        String actualStatus = bestelling.getBetalingStatus();
        assertEquals("Betaald", actualStatus);
    }

    @Test
    public void testSetIdOrder_Null() {
        assertThrows(NullPointerException.class, () -> bestelling.setIdOrder(null));
    }

}
