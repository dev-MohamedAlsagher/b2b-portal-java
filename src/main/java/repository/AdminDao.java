package repository;

import java.util.List;

import domein.Admin;
import domein.Bedrijf;
import domein.GoedKeuringLeverancier;
import domein.Interface_Bedrijf;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface AdminDao extends GenericDao<Admin> {

	public Admin getAdminByEmail(String email) throws EntityNotFoundException;

	public List<Bedrijf> getBedrijven() throws EntityNotFoundException;

	public void updateBedrijfByIdBedrijf(int idBedrijf, String iban, String btwNummer, String telefoonnummer,
			String sector, int idAdres) throws EntityNotFoundException;

	public int createBedrijf(String bedrijfsnaam, String sector, String bedrmail, String bedrtelefoonnummer, String iban,
			String btwNummer, Boolean isActief, int idAdres) throws EntityNotFoundException;

	public Interface_Bedrijf getBedrijfById(int idBedrijf) throws EntityNotFoundException;

	public void keuringVeranderVerzoekenLeverancier(int id, String afgehandeld) throws EntityNotFoundException;

	public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld, int aantal, int begin)
			throws EntityNotFoundException;

	public int createAdres(String straat, String nummer, String stad, String postcode) throws EntityNotFoundException;

	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email)
			throws EntityNotFoundException;

	public boolean createLeverancier(String Leveranciernummer, String gebruikersnaam, String email, String wachtwoord,
			String betaalmethodes, int idBedrijf)
			throws EntityNotFoundException;

	public boolean createKlant(String Klantnummer, String gebruikersnaam, String email, String wachtwoord, int idBedrijf)
			throws EntityNotFoundException;

}
