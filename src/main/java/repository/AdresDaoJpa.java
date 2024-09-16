package repository;

import domein.Adres;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class AdresDaoJpa extends GenericDaoJpa<Adres> implements AdresDao {

    public AdresDaoJpa() {
        super(Adres.class);
    }

    @Override
    public Adres getAdresById(int id) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Adres.getAdresById", Adres.class)
                    .setParameter("idAdres", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }

}
