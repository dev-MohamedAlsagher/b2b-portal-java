package domein;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "adres")
@NamedQueries({
		@NamedQuery(name = "Adres.getAdresById", query = "select  a FROM Adres a where a.idAdres = :idAdres") })

public class Adres implements Serializable, Interface_Adres {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdres;
	private String straat;
	private String nummer;
	private String stad;
	private String postcode;
	private Date laatstGebruikt;

	public Adres(String straat, String nummer, String stad, String postcode, Date laatstGebruikt) {
		super();
		setIdAdres(idAdres);
		setStraat(straat);
		setNummer(nummer);
		setStad(stad);
		setPostcode(postcode);
		setLaatstGebruikt(laatstGebruikt);
	}

	public Adres() {
		super();
	}

	@Override
	public int getIdAdres() {
		return idAdres;
	}

	private void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	private void setStraat(String straat) {
		this.straat = straat;
	}

	@Override
	public String getNummer() {
		return nummer;
	}

	private void setNummer(String nummer) {
		this.nummer = nummer;
	}

	private void setStad(String stad) {
		this.stad = stad;
	}

	@Override
	public String getPostcode() {
		return postcode;
	}

	private void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public Date getLaatstGebruikt() {
		return laatstGebruikt;
	}

	private void setLaatstGebruikt(Date laatstGebruikt) {
		this.laatstGebruikt = laatstGebruikt;
	}

	@Override
	public String getStraat() {
		return straat + " " + nummer;
	}

	@Override
	public String getStad() {
		return stad + ", " + postcode;
	}

	@Override
	public String toString() {
		return straat + " " + nummer + " " + stad + " " + postcode;
	}

}
