package domein;

import java.util.Date;

public interface Interface_Adres {

	int getIdAdres();

	String getNummer();

	String getPostcode();

	Date getLaatstGebruikt();

	String getStraat();

	String getStad();

	String toString();

}