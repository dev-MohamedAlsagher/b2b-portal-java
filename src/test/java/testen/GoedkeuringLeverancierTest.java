package testen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.GoedKeuringLeverancier;
import domein.Interface_GoedKeuringLeverancier;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class GoedKeuringLeverancierTest {

	private Interface_GoedKeuringLeverancier goedKeuringLeverancier;

	@BeforeEach
	void setUp() {

		goedKeuringLeverancier = new GoedKeuringLeverancier(1, "123", "username", "email@test.com", true, "roles", "iban",
				"btwNummer", "1234567890", "sector", "straat", "1", "stad", "1234", "afgehandeld", new Date(), 1);
	}

	@Test
	void testGetIdGoedkeuringLeverancier() {

		int result = goedKeuringLeverancier.getidGoedkeuringLeverancier();

		assertEquals(1, result);
	}

	@Test
	void testGetLeverancierNummer() {

		String result = goedKeuringLeverancier.getLeverancierNummer();

		assertEquals("123", result);
	}

	@Test
	void testGetGebruikersnaam() {

		String result = goedKeuringLeverancier.getGebruikersnaam();

		assertEquals("username", result);
	}

	@Test
	void testGetEmail() {

		String result = goedKeuringLeverancier.getEmail();

		assertEquals("email@test.com", result);
	}

	@Test
	void testGetIsActief() {

		boolean result = goedKeuringLeverancier.getIsActief();

		assertTrue(result);
	}

	@Test
	void testGetRoles() {

		String result = goedKeuringLeverancier.getRoles();

		assertEquals("roles", result);
	}

	@Test
	void testGetIban() {

		String result = goedKeuringLeverancier.getIban();

		assertEquals("iban", result);
	}

	@Test
	void testGetBtwNummer() {

		String result = goedKeuringLeverancier.getBtwNummer();

		assertEquals("btwNummer", result);
	}

	@Test
	void testGetTelefoonnummer() {

		String result = goedKeuringLeverancier.getTelefoonnummer();

		assertEquals("1234567890", result);
	}

	@Test
	void testGetSector() {

		String result = goedKeuringLeverancier.getSector();

		assertEquals("sector", result);
	}

	@Test
	void testGetStraat() {

		String result = goedKeuringLeverancier.getStraat();

		assertEquals("straat", result);
	}

	@Test
	void testGetNummer() {

		String result = goedKeuringLeverancier.getNummer();

		assertEquals("1", result);
	}

	@Test
	void testGetStad() {

		String result = goedKeuringLeverancier.getStad();

		assertEquals("stad", result);
	}

	@Test
	void testGetPostcode() {

		String result = goedKeuringLeverancier.getPostcode();

		assertEquals("1234", result);
	}

	@Test
	void testGetAfgehandeld() {

		String result = goedKeuringLeverancier.getAfgehandeld();

		assertEquals("afgehandeld", result);
	}

	@Test
	void testGetDatumAanvraag() {

		Date result = goedKeuringLeverancier.getDatumAanvraag();

		assertNotNull(result);
	}

	@Test
	void testGetIdLeverancier() {

		int result = goedKeuringLeverancier.getIdLeverancier();

		assertEquals(1, result);
	}

	@Test
	void testSetAfgehandeld() {

		String newAfgehandeld = "newAfgehandeld";

		((GoedKeuringLeverancier) goedKeuringLeverancier).setAfgehandeld(newAfgehandeld);

		assertEquals(newAfgehandeld, goedKeuringLeverancier.getAfgehandeld());
	}

	@Test
	void testSetAfgehandeldWithNull() {

		String newAfgehandeld = null;

		assertThrows(NullPointerException.class,
				() -> ((GoedKeuringLeverancier) goedKeuringLeverancier).setAfgehandeld(newAfgehandeld));
	}

	@Test
	void testSetAfgehandeldWithEmptyString() {

		String newAfgehandeld = "";

		assertThrows(IllegalArgumentException.class,
				() -> ((GoedKeuringLeverancier) goedKeuringLeverancier).setAfgehandeld(newAfgehandeld));
	}
}
