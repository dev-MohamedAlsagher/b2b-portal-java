package testen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import domein.BestellingDetails;

class BestellingDetailsTest {

  private BestellingDetails bestellingDetails;

  @BeforeEach
  void setUp() {
    bestellingDetails = new BestellingDetails(1, 100.0, 1, 1, "1");
  }

  @Test
  void testGetEenheidsPrijs() {
    double expected = 100.0;
    double actual = bestellingDetails.getEenheidsPrijs();
    assertEquals(expected, actual);
  }

  @Test
  void testGetTotaalPrijs() {
    double expected = 100.0;
    double actual = bestellingDetails.getTotaalPrijs();
    assertEquals(expected, actual);
  }

  @Test
  void testGetIdOrderDetails() {
    int expected = 1;
    int actual = bestellingDetails.getIdOrderDetails();
    assertEquals(expected, actual);
  }

  @Test
  void testGetIdProduct() {
    int expected = 1;
    int actual = bestellingDetails.getIdProduct();
    assertEquals(expected, actual);
  }

  @Test
  void testGetAantal() {
    int expected = 1;
    int actual = bestellingDetails.getAantal();
    assertEquals(expected, actual);
  }

  @Test
  void testGetIdOrder() {
    String expected = "1";
    String actual = bestellingDetails.getIdOrder();
    assertEquals(expected, actual);
  }

  @Test
  void testInvalidEenheidsPrijs() {
    double invalidEenheidsPrijs = -1.0;
    assertThrows(IllegalArgumentException.class, () -> {
      bestellingDetails.setEenheidsPrijs(invalidEenheidsPrijs);
    });
  }
}
