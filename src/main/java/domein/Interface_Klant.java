package domein;

public interface Interface_Klant {

	int getIdKlant();

	String getGebruikersnaam();

	String getKlantnummer();

	String getEmail();

	int getIdBedrijf();

	int getAantalBestellingen();

	int getTotaalBestellingen();

	void setAantalBestellingen(int aantalBestellingen);

	void setTotaalBestellingen(int aantalBestellingen);

	void addObserver(Observer observer);

	void removeObserver(Observer observer);

}