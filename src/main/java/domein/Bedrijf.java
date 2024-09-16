package domein;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "bedrijf")
@NamedQueries({
		@NamedQuery(name = "Bedrijf.getBedrijfById", query = "select b FROM Bedrijf b where b.idBedrijf = :idBedrijf"),
		@NamedQuery(name = "Bedrijf.updateStatus", query = "UPDATE Bedrijf b SET b.isActief = :isActief WHERE b.idBedrijf = :idBedrijf"),
		@NamedQuery(name = "Bedrijf.getBedrijven", query = "SELECT b FROM Bedrijf b"),
		@NamedQuery(name = "Bedrijf.getBedrijfById", query = "select b FROM Bedrijf b where b.idBedrijf = :idBedrijf"), })

public class Bedrijf implements Serializable, Interface_Bedrijf {
	private transient List<Observer> observers = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBedrijf;
	private String naam;
	private String logo;
	private String sector;
	private String email;
	private String iban;
	private String btwNummer;
	private String telefoonnummer;
	private Date gebruikerSinds;
	private int idAdres;
	private boolean isActief;

	public Bedrijf() {
	}

	@Override
	public int getIdBedrijf() {
		return this.idBedrijf;
	}

	private void setIdBedrijf(int idBedrijf) {
		this.idBedrijf = idBedrijf;
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

	@Override
	public String getNaam() {
		return this.naam;
	}

	@Override
	public String getLogo() {
		return this.logo;
	}

	private void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String getSector() {
		return this.sector;
	}

	@Override
	public String getEmail() {
		return this.email;
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
	public String getGebruikerSinds() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.gebruikerSinds);
	}

	private void setGebruikerSinds(Date gebruikerSinds) {
		this.gebruikerSinds = gebruikerSinds;
	}

	@Override
	public boolean isIsActief() {
		return this.isActief;
	}

	@Override
	public void setIsActief(boolean isActief) {
		this.isActief = isActief;
		notifyObservers();
	}

	@Override
	public int getIdAdres() {
		return idAdres;
	}

	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public Bedrijf(
			String bedrijfsnaam,
			String logo,
			String sector,
			String bedrmail,
			String bedrtelefoonnummer,
			String iban,
			String btwNummer,
			Date gebruikerSinds,
			Boolean isActief,
			int idAdres) {
		setNaam(bedrijfsnaam);
		setLogo(logo);
		setSector(sector);
		setEmail(bedrmail);
		setTelefoonnummer(bedrtelefoonnummer);
		setIban(iban);
		setBtwNummer(btwNummer);
		setGebruikerSinds(gebruikerSinds);
		setIsActief(isActief);
		setIdAdres(idAdres);
	}

	public Bedrijf(int idBedrijf, String naam, String logo, String sector, String email, String iban, String btwNummer,
			String telefoonnummer, Date gebruikerSinds, int idAdres, boolean isActief) {
		setIdBedrijf(idBedrijf);
		;
		setNaam(naam);
		setLogo(logo);
		setSector(sector);
		setEmail(email);
		setIban(iban);
		setBtwNummer(btwNummer);
		setTelefoonnummer(telefoonnummer);
		setGebruikerSinds(gebruikerSinds);
		setIdAdres(idAdres);
		setIsActief(isActief);
	}

	@Override
	public void setNaam(String naam) {
		if (naam == null) {
			throw new NullPointerException("Naam mag niet null zijn");
		}
		if (naam.isBlank()) {
			throw new IllegalArgumentException("Naam mag niet leeg zijn");
		}

		this.naam = naam;
	}

	@Override
	public void setSector(String sector) {
		this.sector = sector;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}

	@Override
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	@Override
	public String toString() {
		return "Bedrijf: idBedrijf=" + idBedrijf + ", naam=" + naam + ", logo=" + logo + ", sector=" + sector + ", email="
				+ email + ", iban=" + iban + ", btwNummer=" + btwNummer + ", telefoonnummer=" + telefoonnummer
				+ ", gebruikerSinds=" + gebruikerSinds + ", idAdres=" + idAdres + ", isActief=" + isActief;
	}

}