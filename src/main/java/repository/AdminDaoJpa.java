package repository;

import java.util.Date;
import java.util.List;

import domein.Admin;
import domein.Adres;
import domein.Bedrijf;
import domein.GoedKeuringLeverancier;
import domein.Interface_Bedrijf;
import domein.Leverancier;
import domein.Klant;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDaoJpa extends GenericDaoJpa<Admin> implements AdminDao {

	public AdminDaoJpa() {
		super(Admin.class);
	}

	@Override
	public Admin getAdminByEmail(String email) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Admin.getAdminByEmail", Admin.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	public List<Bedrijf> getBedrijven() throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Bedrijf.getBedrijven", Bedrijf.class).getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	public int createBedrijf(
			String bedrijfsnaam,
			String sector,
			String bedrmail,
			String bedrtelefoonnummer,
			String iban,
			String btwNummer,
			Boolean isActief,
			int idAdres) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			Date date = new Date();
			Bedrijf newBedrijf = new Bedrijf(bedrijfsnaam,
					"https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png",
					sector, bedrmail, bedrtelefoonnummer, iban, btwNummer, date, isActief, idAdres);

			em.persist(newBedrijf);

			em.flush();

			transaction.commit();

			return newBedrijf.getIdBedrijf();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	@Override
	public Interface_Bedrijf getBedrijfById(int id) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Bedrijf.getBedrijfById", Bedrijf.class)
					.setParameter("idBedrijf", id)
					.getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	public void keuringVeranderVerzoekenLeverancier(int id, String afgehandeld) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			GoedKeuringLeverancier keuringLeverancier = em.find(GoedKeuringLeverancier.class, id);
			if (keuringLeverancier != null) {
				keuringLeverancier.setAfgehandeld(afgehandeld);
			} else {
				throw new EntityNotFoundException("GoedKeuringLeverancier not found with id: " + id);
			}

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public ObservableList<GoedKeuringLeverancier> getAllByStatusAfhandeling(String afgehandeld, int aantal, int begin)
			throws EntityNotFoundException {
		try {
			List<GoedKeuringLeverancier> resultList = em
					.createNamedQuery("GoedKeuringLeverancier.getAllByStatusAfhandeling", GoedKeuringLeverancier.class)
					.setParameter("afgehandeld", afgehandeld)
					.setFirstResult(begin)
					.setMaxResults(aantal)
					.getResultList();
			return FXCollections.observableList(resultList);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	public void updateBedrijfByIdBedrijf(int idBedrijf, String iban, String btwNummer, String telefoonnummer,
			String sector, int idAdres) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Bedrijf bedrijf = em.find(Bedrijf.class, idBedrijf);
			if (bedrijf == null) {
				throw new EntityNotFoundException("Bedrijf not found with ID: " + idBedrijf);
			}
			bedrijf.setIban(iban);
			bedrijf.setBtwNummer(btwNummer);
			bedrijf.setTelefoonnummer(telefoonnummer);
			bedrijf.setSector(sector);
			bedrijf.setIdAdres(idAdres);

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public int createAdres(String straat, String nummer, String stad, String postcode) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Date date = new Date();
			Adres newAdres = new Adres(straat, nummer, stad, postcode, date);
			em.persist(newAdres);
			em.flush();
			transaction.commit();

			return newAdres.getIdAdres();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email) {
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Leverancier leverancier = em.find(Leverancier.class, idLeverancier);
			if (leverancier == null) {
				throw new EntityNotFoundException("Leverancier with ID " + idLeverancier + " not found");
			}

			leverancier.setGebruikersnaam(gebruikersnaam);
			leverancier.setEmail(email);

			em.merge(leverancier);

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public boolean createLeverancier(String leveranciernummer, String gebruikersnaam, String email, String wachtwoord,
			String betaalmethodes, int idBedrijf) throws EntityNotFoundException {
		boolean gelukt = false;
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Leverancier newLeverancier = new Leverancier(leveranciernummer, gebruikersnaam, email, wachtwoord, idBedrijf,
					betaalmethodes);
			em.persist(newLeverancier);
			em.flush();
			transaction.commit();

			gelukt = true;
			return gelukt;
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public boolean createKlant(String Klantnummer, String gebruikersnaam, String email, String wachtwoord, int idBedrijf)
			throws EntityNotFoundException {
		boolean gelukt = false;
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			Klant newKlant = new Klant(Klantnummer, gebruikersnaam, email, wachtwoord, idBedrijf);
			em.persist(newKlant);

			em.flush();

			transaction.commit();

			gelukt = true;
			return gelukt;

		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

}
