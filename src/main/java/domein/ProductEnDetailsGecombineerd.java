package domein;

public class ProductEnDetailsGecombineerd {

	private String productNaam;
	private Double eenheidsPrijs;
	private Double btw;
	private int aantal;
	private Double totaalPrijs;

	public ProductEnDetailsGecombineerd() {
	}

	public ProductEnDetailsGecombineerd(String productNaam, Double eenheidsPrijs, Double btw, int aantal) {
		super();
		this.productNaam = productNaam;
		this.eenheidsPrijs = eenheidsPrijs;
		this.btw = btw;
		this.aantal = aantal;
		setTotaalPrijs();

	}

	public String getProductNaam() {
		return productNaam;
	}

	public void setProductNaam(String productNaam) {
		this.productNaam = productNaam;
	}

	public Double getEenheidsPrijs() {
		return eenheidsPrijs;
	}

	public void setEenheidsPrijs(Double eenheidsPrijs) {
		this.eenheidsPrijs = eenheidsPrijs;
	}

	public Double getBtw() {
		return btw;
	}

	public void setBtw(Double btwTarief) {
		this.btw = btwTarief;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Double getTotaalPrijs() {
		return totaalPrijs;
	}

	public void setTotaalPrijs() {
		this.totaalPrijs = (eenheidsPrijs + btw) * aantal;
	}

}
