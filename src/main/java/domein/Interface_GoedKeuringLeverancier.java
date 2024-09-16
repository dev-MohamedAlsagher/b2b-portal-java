package domein;

import java.util.Date;

public interface Interface_GoedKeuringLeverancier {

	int getidGoedkeuringLeverancier();

	String getLeverancierNummer();

	String getGebruikersnaam();

	String getEmail();

	boolean getIsActief();

	String getRoles();

	String getIban();

	String getBtwNummer();

	String getTelefoonnummer();

	String getSector();

	String getStraat();

	String getNummer();

	String getStad();

	String getPostcode();

	String getAfgehandeld();

	Date getDatumAanvraag();

	int getIdLeverancier();

	void addObserver(Observer observer);

	void removeObserver(Observer observer);
}
