package domein;

import javafx.collections.ObservableList;

public interface Interface_Leverancier extends Interface_Gebruiker {

	int getIdLeverancier();

	String getLeveranciernummer();

	String getBetaalMethodes();

	Bedrijf getBedrijf();

	String getEmail();

	void setBetaalMethodes(String selectedPaymentMethods);

	void setBedrijf(Bedrijf bedrijf);

	void addObserver(Observer observer);

	void removeObserver(Observer observer);

	ObservableList<Interface_Bestelling> getBestellingen(int aantal, int begin);

	ObservableList<Interface_Bestelling> getBestellingenByKlant(int klantId);

}