
package testen;

import domein.Admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("username", "passwordHash", 1, "roles", "email@example.com");
    }

    @Test
    void testGetRoles() {
        String expected = "roles";
        String actual = admin.getRoles();
        assertEquals(expected, actual);
    }

    @Test
    void testGetIdAdmin() {
        int expected = 1;
        int actual = admin.getIdAdmin();
        assertEquals(expected, actual);
    }

    @Test
    void testSetIdAdmin() {
        int newId = 2;
        admin.setIdAdmin(newId);
        assertEquals(newId, admin.getIdAdmin());
    }

    @Test
    void testConstructorWithInvalidArguments() {
        String username = null;
        String passwordHash = null;
        int idAdmin = -1;
        String roles = null;
        String email = null;
        assertThrows(IllegalArgumentException.class, () -> new Admin(username, passwordHash, idAdmin, roles, email));
    }

    @Test
    void testGetGebruikersnaam() {
        String expected = "username";
        String actual = admin.getGebruikersnaam();
        assertEquals(expected, actual);
    }

    @Test
    void testGetEmail() {
        String expected = "email@example.com";
        String actual = admin.getEmail();
        assertEquals(expected, actual);
    }
}