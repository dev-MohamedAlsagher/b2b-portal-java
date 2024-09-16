package domein;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@NamedQueries({
		@NamedQuery(name = "Product.getProductByProductId", query = "select p FROM Product p where p.idProduct = :idProduct") })
public class Product implements Serializable, Interface_Product {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	private int idLeverancier;
	private String naam;
	private double eenheidsprijs;
	private int btwTarief;
	private String foto;
	private int aantal;
	private String beschrijving;
	private String categorie;

	@Override
	public int getIdProduct() {
		return this.idProduct;
	}

	public Product() {

	}

	private void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public int getIdLeverancier() {
		return this.idLeverancier;
	}

	private void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}

	@Override
	public String getNaam() {
		return this.naam;
	}

	private void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public double getEenheidsprijs() {
		return this.eenheidsprijs;
	}

	private void setEenheidsprijs(double eenheidsprijs) {
		this.eenheidsprijs = eenheidsprijs;
	}

	@Override
	public double getBtwTarief() {
		return this.btwTarief;
	}

	private void setBtwTarief(int btwTarief) {
		this.btwTarief = btwTarief;
	}

	@Override
	public String getFoto() {
		return this.foto;
	}

	private void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int getAantal() {
		return this.aantal;
	}

	private void setAantal(int aantal) {
		this.aantal = aantal;
	}

	@Override
	public String getBeschrijving() {
		return this.beschrijving;
	}

	private void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	@Override
	public String getCategorie() {
		return this.categorie;
	}

	private void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * 
	 * @param idProduct
	 * @param idLeverancier
	 * @param naam
	 * @param eenheidsprijs
	 * @param btwTarief
	 * @param foto
	 * @param aantal
	 * @param beschrijving
	 * @param categorie
	 */
	public Product(int idProduct, int idLeverancier, String naam, double eenheidsprijs, int btwTarief, String foto,
			int aantal, String beschrijving, String categorie) {
		setIdProduct(idProduct);
		setIdLeverancier(idLeverancier);
		setAantal(aantal);
		setBeschrijving(beschrijving);
		setBtwTarief(btwTarief);
		setEenheidsprijs(eenheidsprijs);
		setFoto(foto);
		setCategorie(categorie);
		setNaam(naam);
	}

}