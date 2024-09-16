package domein;

import java.io.Serializable;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Gebruiker implements Serializable, Interface_Gebruiker {

	private static final long serialVersionUID = 1L;

	private String gebruikersnaam;
	private String password_Hash;

	public Gebruiker(String gebruikersnaam, String password_Hash) {
		setGebruikersnaam(gebruikersnaam);
		setPassword_Hash(password_Hash);
	}

	public Gebruiker() {
	}

	@Override
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam == null) {
			throw new NullPointerException("vul een gebruikersnaam in");
		}
		if (gebruikersnaam.isEmpty()) {
			throw new IllegalArgumentException("de gebruikersnaam is niet aanvaard");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getPassword_Hash() {
		return password_Hash;
	}

	private void setPassword_Hash(String password_Hash) {
		this.password_Hash = password_Hash;
	}

}