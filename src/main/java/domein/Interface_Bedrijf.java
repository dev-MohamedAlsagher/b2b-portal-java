package domein;

public interface Interface_Bedrijf {

	int getIdBedrijf();

	String getNaam();

	void setNaam(String naam);

	String getLogo();

	String getSector();

	void setSector(String sector);

	String getEmail();

	void setEmail(String email);

	String getIban();

	void setIban(String iban);

	String getBtwNummer();

	void setBtwNummer(String btwNummer);

	String getTelefoonnummer();

	void setTelefoonnummer(String telefoonnummer);

	String getGebruikerSinds();

	boolean isIsActief();

	int getIdAdres();

	void setIsActief(boolean isActief);

	void addObserver(Observer observer);

	void removeObserver(Observer observer);
}