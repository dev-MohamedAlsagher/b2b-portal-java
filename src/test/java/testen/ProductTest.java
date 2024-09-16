package testen;

import domein.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1, 1, "naam", 10.0, 20, "foto", 5, "beschrijving", "categorie");
    }

    @Test
    void testGetIdProduct() {

        int result = product.getIdProduct();

        assertEquals(1, result);
    }

    @Test
    void testGetIdLeverancier() {

        int result = product.getIdLeverancier();

        assertEquals(1, result);
    }

    @Test
    void testGetNaam() {

        String result = product.getNaam();

        assertEquals("naam", result);
    }

    @Test
    void testGetEenheidsprijs() {

        double result = product.getEenheidsprijs();

        assertEquals(10.0, result);
    }

    @Test
    void testGetBtwTarief() {

        double result = product.getBtwTarief();

        assertEquals(20, result);
    }

    @Test
    void testGetFoto() {

        String result = product.getFoto();

        assertEquals("foto", result);
    }

    @Test
    void testGetAantal() {

        int result = product.getAantal();

        assertEquals(5, result);
    }

    @Test
    void testGetBeschrijving() {

        String result = product.getBeschrijving();

        assertEquals("beschrijving", result);
    }

    @Test
    void testGetCategorie() {

        String result = product.getCategorie();

        assertEquals("categorie", result);
    }
}