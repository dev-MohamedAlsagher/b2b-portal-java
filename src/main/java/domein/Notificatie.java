package domein;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "notificatie")
@NamedQuery(name = "Notificatie.getBetalingsHerinneringNotificatieByIdOrder", query = "SELECT n FROM Notificatie n WHERE n.idOrder = :idOrder AND n.onderwerp = 'BetalingsHerinnering' ORDER BY n.datum DESC")
public class Notificatie implements Interface_Notificatie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idNotificatie;
	private String text;
	private String onderwerp;
	private boolean geopend;
	private boolean afgehandeld;
	private String idOrder;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	@Override
	public String getIdNotificatie() {
		return this.idNotificatie;
	}

	@Override
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		if (text == null) {
			throw new IllegalArgumentException("text cannot be null");
		}
		this.text = text;
	}

	@Override
	public String getOnderwerp() {
		return this.onderwerp;
	}

	public void setOnderwerp(String onderwerp) {
		if (onderwerp == null) {
			throw new IllegalArgumentException("onderwerp cannot be null");
		}
		this.onderwerp = onderwerp;
	}

	@Override
	public boolean isGeopend() {
		return this.geopend;
	}

	public void setGeopend(boolean geopend) {
		this.geopend = geopend;
	}

	@Override
	public String getDatum() {
		Date datum = this.datum;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(datum);
		return formattedDate;
	}

	@Override
	public Boolean getHerinneringPeriode() {
		String datumString = getDatum();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate notificatieDatum = LocalDate.parse(datumString, formatter);
		long daysDifference = ChronoUnit.DAYS.between(notificatieDatum, LocalDate.now());

		return daysDifference < 3;
	}

	public void setDatum(Date datum) {
		if (datum == null) {
			throw new NullPointerException("datum cannot be null");
		}
		this.datum = datum;
	}

	/**
	 * 
	 * @param idNotificatie
	 * @param text
	 * @param onderwerp
	 * @param geopend
	 * @param datum
	 */

	public Notificatie(String text, String onderwerp, boolean geopend, Date datum, String idOrder) {
		this.text = text;
		this.onderwerp = onderwerp;
		this.geopend = geopend;
		this.datum = datum;
		this.afgehandeld = false;
		this.idOrder = idOrder;
	}

	public Notificatie() {
	}

	@Override
	public String toString() {
		return "Notificatie: idNotificatie: " + idNotificatie + ", onderwerp: " + onderwerp + ", text: " + text
				+ ", geopend: " + geopend + ", datum: " + datum + "]";
	}

	public void setAfgehandeld(boolean b) {
		this.afgehandeld = b;
	}

	public void setIdOrder(String idOrder) {
		if (idOrder == null) {
			throw new IllegalArgumentException("idOrder cannot be null");
		}
		this.idOrder = idOrder;
	}

}