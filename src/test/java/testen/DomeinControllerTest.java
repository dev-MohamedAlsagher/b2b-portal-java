package testen;

import domein.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import repository.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomeinControllerTest {

    private DomeinController domeinController;
    private B2B_Portal mockApp;
    private LeverancierDao mockLeverancierRepo;
    private AdresDao mockAdresRepo;
    private AdminDao mockAdminRepo;

    @BeforeEach
    public void setUp() {
        mockLeverancierRepo = mock(LeverancierDaoJpa.class);
        mockAdresRepo = mock(AdresDaoJpa.class);
        mockAdminRepo = mock(AdminDaoJpa.class);
        mockApp = mock(B2B_Portal.class);

        domeinController = new DomeinController();
        domeinController.setApp(mockApp);
    }

    @AfterEach
    public void tearDown() {
        domeinController = null;
        mockApp = null;
        mockLeverancierRepo = null;
        mockAdresRepo = null;
        mockAdminRepo = null;
    }

    @Test
    public void testUitloggen() {

        domeinController.setLeverancier(new Leverancier());
        domeinController.setAdmin(new Admin());

        domeinController.uitloggen();

        assertNull(domeinController.getLeverancier());
        assertNull(domeinController.getAdmin());
    }

    @Test
    public void testAanmeldenAdmin_Success() {

        String email = "admin@example.com";
        String password = "password";
        Admin mockAdmin = mock(Admin.class);
        when(mockApp.aanmeldenAdmin(email, password)).thenReturn(mockAdmin);

        boolean result = domeinController.aanmeldenAdmin(email, password);

        assertTrue(result);
        assertEquals(mockAdmin, domeinController.getAdmin());
    }

    @Test
    public void testAanmeldenAdmin_Failure() {

        String email = "admin@example.com";
        String password = "wrongpassword";
        when(mockApp.aanmeldenAdmin(email, password)).thenReturn(null);

        boolean result = domeinController.aanmeldenAdmin(email, password);

        assertFalse(result);
        assertNull(domeinController.getAdmin());
    }

    @Test
    public void testAanmelden_Success() {

        String email = "leverancier@example.com";
        String password = "password";
        Leverancier mockLeverancier = mock(Leverancier.class);
        when(mockApp.aanmelden(email, password)).thenReturn(mockLeverancier);
        when(mockLeverancier.getIdLeverancier()).thenReturn(1);
        when(mockApp.getBestellingenByLeverancierId(1)).thenReturn(FXCollections.observableArrayList());

        boolean result = domeinController.aanmelden(email, password);

        assertTrue(result);
        assertEquals(mockLeverancier, domeinController.getLeverancier());
    }

    @Test
    public void testGetKlantenByLeverancierId() {

        int aantal = 10;
        int begin = 0;
        ObservableList<Interface_Klant> mockKlanten = FXCollections.observableArrayList();
        Leverancier mockLeverancier = mock(Leverancier.class);
        domeinController.setLeverancier(mockLeverancier);
        when(mockLeverancier.getIdLeverancier()).thenReturn(1);
        when(mockApp.getKlantenByLeverancierId(1, aantal, begin)).thenReturn(mockKlanten);

        ObservableList<Interface_Klant> result = domeinController.getKlantenByLeverancierId(aantal, begin);

        assertEquals(mockKlanten, result);
    }

    @Test
    public void testGetKlantById() {

        int klantId = 1;
        Interface_Klant mockKlant = mock(Interface_Klant.class);
        when(mockApp.getKlantById(klantId)).thenReturn(mockKlant);

        Interface_Klant result = domeinController.getKlantById(klantId);

        assertEquals(mockKlant, result);
    }

    @Test
    public void testFindBestellingenByKlant() {

        Interface_Klant mockKlant = mock(Interface_Klant.class);
        ObservableList<Interface_Bestelling> mockBestellingen = FXCollections.observableArrayList();
        when(mockApp.getBestellingenByKlantId(mockKlant)).thenReturn(mockBestellingen);

        ObservableList<Interface_Bestelling> result = domeinController.findBestellingenByKlant(mockKlant);

        assertEquals(mockBestellingen, result);
    }

    @Test
    public void testSetAantalBestellingen() {

        Interface_Klant mockKlant = mock(Interface_Klant.class);

        domeinController.setAantalBestellingen(mockKlant);

        verify(mockApp).setAantalBestellingenByKlant(mockKlant);
    }

    @Test
    public void testGetBestellingDetails() {

        Interface_Bestelling mockBestelling = mock(Interface_Bestelling.class);
        ObservableList<Interface_BestellingDetails> mockDetails = FXCollections.observableArrayList();
        when(mockApp.getBestellingDetails(mockBestelling)).thenReturn(mockDetails);

        ObservableList<Interface_BestellingDetails> result = domeinController.getBestellingDetails(mockBestelling);

        assertEquals(mockDetails, result);
    }

    @Test
    public void testGetProductByProductId() {

        Interface_BestellingDetails mockDetail = mock(Interface_BestellingDetails.class);
        Interface_Product mockProduct = mock(Interface_Product.class);
        when(mockDetail.getIdProduct()).thenReturn(1);
        when(mockApp.getProductByProductId(1)).thenReturn(mockProduct);

        Interface_Product result = domeinController.getProductByProductId(mockDetail);

        assertEquals(mockProduct, result);
    }

    @Test
    public void testGetBedrijven() {

        int aantal = 10;
        int begin = 0;
        ObservableList<Interface_Bedrijf> mockBedrijven = FXCollections.observableArrayList();
        when(mockApp.getBedrijven(aantal, begin)).thenReturn(mockBedrijven);

        ObservableList<Interface_Bedrijf> result = domeinController.getBedrijven(aantal, begin);

        assertEquals(mockBedrijven, result);
    }

    @Test
    public void testGetBedrijfByKlant() {

        Interface_Klant mockKlant = mock(Interface_Klant.class);
        Interface_Bedrijf mockBedrijf = mock(Interface_Bedrijf.class);
        when(mockKlant.getIdKlant()).thenReturn(1);
        when(mockApp.getBedrijfByKlantId(1)).thenReturn(mockBedrijf);

        Interface_Bedrijf result = domeinController.getBedrijfByKlant(mockKlant);

        assertEquals(mockBedrijf, result);
    }

    @Test
    public void testGetLeverancierGegevensByIdBedrijf() {

        int idBedrijf = 1;
        Interface_Leverancier mockLeverancier = mock(Interface_Leverancier.class);
        when(mockApp.getLeverancierGegevensByIdBedrijf(idBedrijf)).thenReturn(mockLeverancier);

        Interface_Leverancier result = domeinController.getLeverancierGegevensByIdBedrijf(idBedrijf);

        assertEquals(mockLeverancier, result);
    }

    @Test
    public void testGetAdresByIdAdres() {

        int idAdres = 1;
        Interface_Adres mockAdres = mock(Interface_Adres.class);
        when(mockApp.getAdresByIdAdres(idAdres)).thenReturn(mockAdres);

        Interface_Adres result = domeinController.getAdresByIdAdres(idAdres);

        assertEquals(mockAdres, result);
    }

    @Test
    public void testMaakNotificatie() {

        Interface_Bestelling mockBestelling = mock(Interface_Bestelling.class);
        Date date = new Date();

        domeinController.maakNotificatie(mockBestelling);

        verify(mockApp).createNotificatie("Gelieve te betalen!", "Betalingsherinnering", false, date, mockBestelling);
    }

    @Test
    public void testVeranderStatusOrder() {

        String id = "1";
        Klant mockKlant = mock(Klant.class);
        when(mockKlant.getAantalBestellingen()).thenReturn(5);

        domeinController.veranderStatusOrder(id, mockKlant);

        verify(mockApp).veranderBetalingStatus(id);
        verify(mockKlant).updateAantalBestellingen(4);
    }

    @Test
    public void testGetGoedKeuringen() {

        String soort = "soort";
        int aantal = 10;
        int begin = 0;
        ObservableList<Interface_GoedKeuringLeverancier> mockGoedkeuringen = FXCollections.observableArrayList();
        when(mockApp.getAllByStatusAfhandeling(soort, aantal, begin)).thenReturn(mockGoedkeuringen);

        ObservableList<Interface_GoedKeuringLeverancier> result = domeinController.getGoedKeuringen(soort, aantal,
                begin);

        assertEquals(mockGoedkeuringen, result);
    }

    @Test
    public void testGetLeverancierById() {

        int idLeverancier = 1;
        Interface_Leverancier mockLeverancier = mock(Interface_Leverancier.class);
        when(mockApp.getLeverancierById(idLeverancier)).thenReturn(mockLeverancier);

        Interface_Leverancier result = domeinController.getLeverancierById(idLeverancier);

        assertEquals(mockLeverancier, result);
    }

    @Test
    public void testUpdateGoedkeuringLeverancier() {

        int id = 1;
        String afgehandeld = "ja";

        domeinController.updateGoedkeuringLeverancier(id, afgehandeld);

        verify(mockApp).keuringVeranderVerzoekenLeverancier(id, afgehandeld);
    }

    @Test
    public void testUpdateLeverancierById() {

        int idLeverancier = 1;
        String gebruikersnaam = "gebruikersnaam";
        String email = "email@example.com";
        String iban = "NL00BANK0123456789";
        String btwNummer = "NL123456789B01";
        String telefoonnummer = "1234567890";
        String sector = "sector";
        String straat = "straat";
        String nummer = "10";
        String stad = "stad";
        String postcode = "1234AB";

        domeinController.updateLeverancierById(idLeverancier, gebruikersnaam, email, iban, btwNummer, telefoonnummer,
                sector, straat, nummer, stad, postcode);

        verify(mockApp).updateLeverancierById(idLeverancier, gebruikersnaam, email, iban, btwNummer, telefoonnummer,
                sector, straat, nummer, stad, postcode);
    }

    @Test
    public void testUpdateLeverancier() {

        Interface_Leverancier mockLeverancier = mock(Interface_Leverancier.class);

        domeinController.updateLeverancier(mockLeverancier);

        verify(mockApp).updateLeverancier(mockLeverancier);
    }

    @Test
    public void testUpdateBedrijfStatus() {

        Interface_Bedrijf mockBedrijf = mock(Interface_Bedrijf.class);
        Boolean actief = true;

        domeinController.updateBedrijfStatus(mockBedrijf, actief);

        verify(mockApp).updateBedrijfStatus(mockBedrijf, actief);
    }

    @Test
    public void testUpdateBedrijfList() {

        Interface_Bedrijf mockBedrijf = mock(Interface_Bedrijf.class);

        domeinController.updateBedrijfList(mockBedrijf);

        verify(mockApp).updateBedrijvenList(mockBedrijf);
    }

    @Test
    public void testGetNotificatieByIdOrder() {

        String idOrder = "1";
        Interface_Notificatie mockNotificatie = mock(Interface_Notificatie.class);
        when(mockApp.getNotificatieByIdOrder(idOrder)).thenReturn(mockNotificatie);

        Interface_Notificatie result = domeinController.getNotificatieByIdOrder(idOrder);

        assertEquals(mockNotificatie, result);
    }
}
