package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.LeverancierDaoJpa;

@Entity
@Table(name = "leverancier")
@NamedQueries({
        @NamedQuery(name = "Leverancier.getLeverancierByEmail", query = "SELECT l FROM Leverancier l WHERE l.email = :email"),
        @NamedQuery(name = "Leverancier.getLeverancierByIdBedrijf", query = "SELECT l from Leverancier l where l.bedrijf.idBedrijf = :idBedrijf"),
        @NamedQuery(name = "Leverancier.updateLeverancierBetaalMethodes", query = "UPDATE Leverancier l SET l.betaalMethodes = :betaalMethodes WHERE l.idLeverancier = :idLeverancier"),
        @NamedQuery(name = "Leverancier.getLeverancierById", query = "SELECT l from Leverancier l where l.idLeverancier = :idLeverancier"),
        @NamedQuery(name = "Leverancier.updateLeverancierById", query = "UPDATE Leverancier l "
                + "SET l.gebruikersnaam = :gebruikersnaam,l.email = :email "
                + "WHERE l.idLeverancier = :idLeverancier"),

})
public class Leverancier extends Gebruiker implements Interface_Leverancier {
    private transient List<Observer> observers = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    private transient ObservableList<Interface_Bestelling> bestellingen;

    @ManyToOne
    @JoinColumn(name = "idBedrijf", referencedColumnName = "idBedrijf")
    private Bedrijf bedrijf;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLeverancier")
    private int idLeverancier;

    private String leveranciernummer;
    private String betaalMethodes;
    private String email;

    public Leverancier() {
        super();
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String leveranciernummer, String betaalMethodes,
            String roles, int idLeverancier, String email) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setBetaalMethodes(betaalMethodes);
        setIdLeverancier(idLeverancier);
        setEmail(email);
    }

    public Leverancier(String leveranciernummer, String gebruikersnaam, String email, String password_Hash,
            int idBedrijf, String betaalMethodes) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setEmail(email);
        setBetaalMethodes(betaalMethodes);

        LeverancierDaoJpa leverancierDao = new LeverancierDaoJpa();
        Bedrijf bedrijf = (Bedrijf) leverancierDao.getBedrijfById(idBedrijf);
        if (bedrijf != null) {
            setBedrijf(bedrijf);
        } else {
            throw new IllegalArgumentException("Bedrijf not found");
        }
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String leveranciernummer, String betaalMethodes,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setBetaalMethodes(betaalMethodes);
        setIdLeverancier(idLeverancier);
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            boolean isActief, String leveranciernummer,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setIdLeverancier(idLeverancier);
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
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
    public int getIdLeverancier() {
        return idLeverancier;
    }

    private void setIdLeverancier(int idLeverancier) {
        this.idLeverancier = idLeverancier;
        notifyObservers();
    }

    @Override
    public String getLeveranciernummer() {
        return leveranciernummer;
    }

    private void setLeveranciernummer(String leveranciernummer) {
        this.leveranciernummer = leveranciernummer;
        notifyObservers();
    }

    @Override
    public String getBetaalMethodes() {
        if (betaalMethodes != null) {
            return String.join(", ", betaalMethodes);
        } else {
            return "";
        }
    }

    public void setEmail(String email) {
        this.email = email;
        notifyObservers();
    }

    public void setBetaalMethodes(String betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
        notifyObservers();
    }

    @Override
    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    @Override
    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
        notifyObservers();
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public ObservableList<Interface_Bestelling> getBestellingen(int aantal, int begin) {
    	//bestellingen.subList(aantal, begin);
        return FXCollections.observableArrayList(bestellingen.stream()
                .skip(begin)
                .limit(aantal)
                .collect(Collectors.toList()));
    }

    @Override
    public ObservableList<Interface_Bestelling> getBestellingenByKlant(int klantId) {
        return FXCollections.observableArrayList(bestellingen.stream()
                .filter(bestelling -> bestelling.getIdKlant() == klantId)
                .distinct()
                .collect(Collectors.toList()));
    }

    public void setBestellingen(ObservableList<Interface_Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
}
