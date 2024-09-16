package domein;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
@NamedQuery(name = "Admin.getAdminByEmail", query = "SELECT ad FROM Admin ad WHERE ad.email = :email")
public class Admin extends Gebruiker {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAdmin")
	private int idAdmin;
	private String gebruikersnaam;
	private String email;

	@Column(columnDefinition = "VARCHAR(255)")
	private String roles;

	public Admin(String gebruikersnaam, String password_Hash, int idAdmin, String roles, String email) {
		super(gebruikersnaam, password_Hash);
		this.idAdmin = idAdmin;
		this.gebruikersnaam = gebruikersnaam;
		this.email = email;
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

	public Admin() {
		super();
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {

		if (idAdmin < 0) {
			throw new IllegalArgumentException("IdAdmin mag niet negatief zijn");
		}

		this.idAdmin = idAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Email mag niet leeg zijn");
		}
		this.email = email;
	}

	public void setRoles(String roles) {

		if (roles == null || roles.isBlank()) {
			throw new IllegalArgumentException("Roles mag niet leeg zijn");
		}
		this.roles = roles;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {

		if (gebruikersnaam == null || gebruikersnaam.isBlank()) {
			throw new IllegalArgumentException("Gebruikersnaam mag niet leeg zijn");
		}
		this.gebruikersnaam = gebruikersnaam;
	}
}
