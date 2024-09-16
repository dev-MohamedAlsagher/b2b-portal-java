package repository;

import domein.Adres;
import jakarta.persistence.EntityNotFoundException;

public interface AdresDao extends GenericDao<Adres> {
	public Adres getAdresById(int id) throws EntityNotFoundException;
}
