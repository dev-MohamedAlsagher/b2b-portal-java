package repository;

import java.util.Date;
import java.util.List;

import domein.Bestelling;
import domein.BestellingDetails;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_Klant;
import domein.Interface_Leverancier;
import domein.Interface_Notificatie;
import domein.Klant;
import domein.Leverancier;
import domein.Product;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface LeverancierDao extends GenericDao<Leverancier> {

        public Leverancier getLeverancierByEmail(String email) throws EntityNotFoundException;

        public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException;

        public Leverancier getLeverancierById(int idLeverancier) throws EntityNotFoundException;

        public void updateLeverancier(Interface_Leverancier lever) throws EntityNotFoundException;

        public Interface_Bedrijf getBedrijfById(int id) throws EntityNotFoundException;

        public Interface_Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException;

        public void updateBedrijfStatus(Interface_Bedrijf bedrijf, Boolean actief) throws EntityNotFoundException;

        public List<Bestelling> getBestellingenByLeverancierId(int id)
                        throws EntityNotFoundException;

        public ObservableList<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException;

        public void veranderBetalingStatus(String idOrder) throws EntityNotFoundException;

        public List<BestellingDetails> getBestellingDetailsByOrderId(String id) throws EntityNotFoundException;

        List<Klant> getKlantenByLeverancierID(int leverancierId, int aantal, int begin) throws EntityNotFoundException;

        Interface_Klant getKlantById(int klantId) throws EntityNotFoundException;

        public Product getProductByProductId(int id) throws EntityNotFoundException;

        public void createNotificatie(String string, String string2, boolean b, Date date,
                        Interface_Bestelling bestelling)
                        throws EntityNotFoundException;

        public Interface_Notificatie getNotificatieByIdOrder(String idOrder) throws EntityNotFoundException;
}
