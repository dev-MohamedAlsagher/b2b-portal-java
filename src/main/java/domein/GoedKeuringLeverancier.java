package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "goedkeuringleverancier")
@NamedQueries({
		@NamedQuery(name = "GoedKeuringLeverancier.keuringVeranderVerzoekenLeverancier", query = "UPDATE GoedKeuringLeverancier g SET g.afgehandeld = :afgehandeld WHERE g.idGoedkeuringLeverancier = :idGoedkeuringLeverancier"),
		@NamedQuery(name = "GoedKeuringLeverancier.getAllByStatusAfhandeling", query = "select g FROM GoedKeuringLeverancier g where g.afgehandeld = :afgehandeld"),
})

public class GoedKeuringLeverancier implements Serializable, Interface_GoedKeuringLeverancier {
	private transient List<Observer> observers = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGoedkeuringLeverancier;

	private String leverancierNummer;
	private String gebruikersnaam;
	private String email;
	private boolean isActief;
	private String roles;
	private String iban;
	private String btwNummer;
	private String telefoonnummer;
	private String sector;
	private String straat;
	private String nummer;
	private String stad;
	private String postcode;
	private String afgehandeld;
	private Date datumAanvraag;
	private int idLeverancier;

	public GoedKeuringLeverancier() {
	};

	public GoedKeuringLeverancier(int idGoedkeuringLeverancier, String leverancierNummer, String gebruikersnaam,
			String email, boolean isActief, String roles, String iban, String btwNummer, String telefoonnummer,
			String sector, String straat, String nummer, String stad, String postcode, String afgehandeld,
			Date datumAanvraag, int idLeverancier) {
		super();
		this.idGoedkeuringLeverancier = idGoedkeuringLeverancier;
		this.leverancierNummer = leverancierNummer;
		this.gebruikersnaam = gebruikersnaam;
		this.email = email;
		this.isActief = isActief;
		this.roles = roles;
		this.iban = iban;
		this.btwNummer = btwNummer;
		this.telefoonnummer = telefoonnummer;
		this.sector = sector;
		this.straat = straat;
		this.nummer = nummer;
		this.stad = stad;
		this.postcode = postcode;
		this.afgehandeld = afgehandeld;
		this.datumAanvraag = datumAanvraag;
		this.idLeverancier = idLeverancier;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public void setIdGoedkeuringLeverancier(int idGoedkeuringLeverancier) {
		this.idGoedkeuringLeverancier = idGoedkeuringLeverancier;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setAfgehandeld(String afgehandeld) {
		if (afgehandeld == null) {
			throw new NullPointerException("afgehandeld cannot be null");
		}
		if (afgehandeld.isEmpty()) {
			throw new IllegalArgumentException("afgehandeld cannot be empty");
		}
		this.afgehandeld = afgehandeld;
		notifyObservers();
	}

	public void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}

	@Override
	public int getidGoedkeuringLeverancier() {
		return this.idGoedkeuringLeverancier;
	}

	@Override
	public String getLeverancierNummer() {
		return this.leverancierNummer;
	}

	@Override
	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public boolean getIsActief() {
		return this.isActief;
	}

	@Override
	public String getRoles() {
		return this.roles;
	}

	@Override
	public String getIban() {
		return this.iban;
	}

	@Override
	public String getBtwNummer() {
		return this.btwNummer;
	}

	@Override
	public String getTelefoonnummer() {
		return this.telefoonnummer;
	}

	@Override
	public String getSector() {
		return this.sector;
	}

	@Override
	public String getStraat() {
		return this.straat;
	}

	@Override
	public String getNummer() {
		return this.nummer;
	}

	@Override
	public String getStad() {
		return this.stad;
	}

	@Override
	public String getPostcode() {
		return this.postcode;
	}

	@Override
	public String getAfgehandeld() {
		return this.afgehandeld;
	}

	@Override
	public Date getDatumAanvraag() {
		return this.datumAanvraag;
	}

	@Override
	public int getIdLeverancier() {
		return this.idLeverancier;
	}
}
