package testen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import domein.Bedrijf;
import domein.Observer;

import static org.junit.jupiter.api.Assertions.*;

class BedrijfTest {

    private Bedrijf bedrijf;

    @Mock
    private Observer observer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bedrijf = new Bedrijf("Test", "logo", "sector", "test@mail.com", "1234567890", "IBAN", "BTW", new Date(), true,
                1);
        bedrijf.addObserver(observer);
    }

    @Test
    void testGetIdBedrijf() {
        int expectedId = 0;
        int actualId = bedrijf.getIdBedrijf();
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetIsActief() {
        boolean expectedIsActive = false;
        bedrijf.setIsActief(expectedIsActive);
        boolean actualIsActive = bedrijf.isIsActief();
        assertEquals(expectedIsActive, actualIsActive);
    }

    @Test
    void testSetNaam() {
        String expectedName = "New Name";
        bedrijf.setNaam(expectedName);
        String actualName = bedrijf.getNaam();
        assertEquals(expectedName, actualName);
    }

    @Test
    void testSetNaam_Null() {
        String name = null;
        assertThrows(NullPointerException.class, () -> bedrijf.setNaam(name));
    }

    @Test
    void testSetNaam_Empty() {
        String name = "";
        assertThrows(IllegalArgumentException.class, () -> bedrijf.setNaam(name));
    }
}
