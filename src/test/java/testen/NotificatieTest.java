package testen;

import domein.Notificatie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NotificatieTest {

    private Notificatie notificatie;

    @BeforeEach
    void setUp() {
        notificatie = new Notificatie("text", "onderwerp", false, new Date(), "idOrder");
    }

    @Test
    void testGetIdNotificatie() {

        String result = notificatie.getIdNotificatie();

        assertNull(result);
    }

    @Test
    void testGetText() {

        String result = notificatie.getText();

        assertEquals("text", result);
    }

    @Test
    void testSetText() {

        String expected = "new text";

        notificatie.setText(expected);
        String result = notificatie.getText();

        assertEquals(expected, result);
    }

    @Test
    void testSetTextNull() {

        String expected = null;

        assertThrows(IllegalArgumentException.class, () -> notificatie.setText(expected));
    }

    @Test
    void testGetOnderwerp() {

        String result = notificatie.getOnderwerp();

        assertEquals("onderwerp", result);
    }

    @Test
    void testSetOnderwerp() {

        String expected = "new onderwerp";

        notificatie.setOnderwerp(expected);
        String result = notificatie.getOnderwerp();

        assertEquals(expected, result);
    }

    @Test
    void testSetOnderwerpNull() {

        String expected = null;

        assertThrows(IllegalArgumentException.class, () -> notificatie.setOnderwerp(expected));
    }

    @Test
    void testIsGeopend() {

        boolean result = notificatie.isGeopend();

        assertFalse(result);
    }

    @Test
    void testSetGeopend() {

        boolean expected = true;

        notificatie.setGeopend(expected);
        boolean result = notificatie.isGeopend();

        assertEquals(expected, result);
    }

    @Test
    void testGetDatum() {

        String result = notificatie.getDatum();

        assertNotNull(result);
    }

    @Test
    void testSetDatum() {

        Date expectedDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String expected = sdf.format(expectedDate);

        notificatie.setDatum(expectedDate);
        String result = notificatie.getDatum();

        assertEquals(expected, result);
    }

    @Test
    void testSetDatumNull() {

        Date expected = null;

        assertThrows(NullPointerException.class, () -> notificatie.setDatum(expected));
    }
}