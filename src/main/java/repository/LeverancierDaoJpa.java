package repository;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingDetails;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_Klant;
import domein.Interface_Leverancier;
import domein.Interface_Notificatie;
import domein.Klant;
import domein.Leverancier;
import domein.Notificatie;
import domein.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeverancierDaoJpa extends GenericDaoJpa<Leverancier> implements LeverancierDao {

	@PersistenceContext
	private EntityManager entityManager;
	private EntityManagerFactory emf;

	public LeverancierDaoJpa() {
		super(Leverancier.class);
		emf = Persistence.createEntityManagerFactory("b2bDeleware");
		this.entityManager = emf.createEntityManager();
	}

	@Override
	public Leverancier getLeverancierByEmail(String email) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierByEmail", Leverancier.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierByIdBedrijf", Leverancier.class)
					.setParameter("idBedrijf", idBedrijf).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Leverancier getLeverancierById(int idLeverancier) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Leverancier.getLeverancierById", Leverancier.class)
					.setParameter("idLeverancier", idLeverancier).getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void updateLeverancier(Interface_Leverancier lever) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			Query query = em.createNamedQuery("Leverancier.updateLeverancierBetaalMethodes");
			query.setParameter("betaalMethodes", lever.getBetaalMethodes());
			query.setParameter("idLeverancier", lever.getIdLeverancier());
			int updatedEntities = query.executeUpdate();
			if (updatedEntities == 0) {
				throw new EntityNotFoundException();
			}
			transaction.commit();
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

	@Override
	public Interface_Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException {
		try {
			return em
					.createQuery("SELECT b FROM Klant k JOIN Bedrijf b ON k.idBedrijf = b.idBedrijf WHERE k.idKlant = :idKlant",
							Bedrijf.class)
					.setParameter("idKlant", id)
					.getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void updateBedrijfStatus(Interface_Bedrijf bedrijf, Boolean actief) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			Bedrijf bedrijfEntity = em.find(Bedrijf.class, bedrijf.getIdBedrijf());
			if (bedrijfEntity == null) {
				throw new EntityNotFoundException("Bedrijf not found with id: " + bedrijf.getIdBedrijf());
			}
			bedrijfEntity.setIsActief(actief);

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	public void close() {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	@Override
	public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Bestelling.getBestellingenByLeverancierId", Bestelling.class)
					.setParameter("idLeverancier", id).getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public ObservableList<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException {
		try {
			List<Bestelling> resultList = em.createNamedQuery("Bestelling.getBestellingenByKlantId", Bestelling.class)
					.setParameter("idKlant", id).getResultList();
			return FXCollections.observableList(resultList);
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void veranderBetalingStatus(String idOrder) throws EntityNotFoundException {
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			Bestelling order = em.find(Bestelling.class, idOrder);
			if (order == null) {
				throw new EntityNotFoundException("Order with ID " + idOrder + " not found");
			}
			order.updateBetalingStatus(true);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	@Override
	public List<BestellingDetails> getBestellingDetailsByOrderId(String id) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("BestellingDetails.getBestellingDetailsByOrderId", BestellingDetails.class)
					.setParameter("idOrder", id)
					.getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public List<Klant> getKlantenByLeverancierID(int leverancierId, int aantal, int begin)
			throws EntityNotFoundException {
		try {
			return em.createQuery(
					"SELECT DISTINCT k FROM Klant k JOIN Bestelling b ON k.idKlant = b.idKlant WHERE b.idLeverancier = :idLeverancier",
					Klant.class)
					.setParameter("idLeverancier", leverancierId)
					.setFirstResult(begin)
					.setMaxResults(aantal)
					.getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Interface_Klant getKlantById(int klantId) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Klant.getKlantById", Klant.class)
					.setParameter("idKlant", klantId)
					.getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public void createNotificatie(String text, String onderwerp, boolean geopend, Date datum,
			Interface_Bestelling bestelling) {
		EntityTransaction transaction = em.getTransaction();
		String idOrder = bestelling.getIdOrder();
		try {
			transaction.begin();

			Notificatie notificatie = new Notificatie(text, onderwerp, geopend, datum, idOrder);
			em.persist(notificatie);

			transaction.commit();

		} catch (Exception ex) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw ex;
		}
	}

	@Override
	public Product getProductByProductId(int id) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Product.getProductByProductId", Product.class)
					.setParameter("idProduct", id)
					.getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Interface_Notificatie getNotificatieByIdOrder(String idOrder) throws EntityNotFoundException {
		try {
			List<Notificatie> notificaties = entityManager
					.createNamedQuery("Notificatie.getBetalingsHerinneringNotificatieByIdOrder", Notificatie.class)
					.setParameter("idOrder", idOrder)
					.setMaxResults(1)
					.getResultList();
			if (notificaties.isEmpty()) {
				return null;
			}

			return notificaties.get(0);

		} catch (NoResultException e) {
			throw new EntityNotFoundException();
		}
	}
}
