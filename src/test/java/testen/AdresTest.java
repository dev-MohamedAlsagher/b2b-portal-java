package testen;

import org.junit.jupiter.api.*;
import java.util.Date;

import domein.Adres;

import domein.Interface_Adres;

import static org.junit.jupiter.api.Assertions.*;

class AdresTest {

  private Interface_Adres adres;

  @BeforeEach
  void setUp() {
    adres = new Adres("Test Street", "123", "Test City", "12345", new Date());
  }

  @AfterEach
  void tearDown() {
    adres = null;
  }

  @Test
  void testGetIdAdres() {
    int expectedId = 0;
    int actualId = adres.getIdAdres();
    assertEquals(expectedId, actualId);
  }

  @Test
  void testGetNummer() {
    String expectedNummer = "123";
    String actualNummer = adres.getNummer();
    assertEquals(expectedNummer, actualNummer);
  }

  @Test
  void testGetPostcode() {
    String expectedPostcode = "12345";
    String actualPostcode = adres.getPostcode();
    assertEquals(expectedPostcode, actualPostcode);
  }

  @Test
  void testGetLaatstGebruikt() {
    Date expectedDate = new Date();
    Date actualDate = adres.getLaatstGebruikt();
    assertEquals(expectedDate, actualDate);
  }

  @Test
  void testGetStraat() {
    String expectedStraat = "Test Street 123";
    String actualStraat = adres.getStraat();
    assertEquals(expectedStraat, actualStraat);
  }

  @Test
  void testGetStad() {
    String expectedStad = "Test City, 12345";
    String actualStad = adres.getStad();
    assertEquals(expectedStad, actualStad);
  }

  @Test
  void testToString() {
    String expectedString = "Test Street 123 Test City 12345";
    String actualString = adres.toString();
    assertEquals(expectedString, actualString);
  }
}
