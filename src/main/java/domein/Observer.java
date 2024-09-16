package domein;

public interface Observer {
    void update(Klant klant);

    void update(Interface_Leverancier leverancier);

    void update(Interface_GoedKeuringLeverancier goedKeuringLeverancier);

    void update(Interface_Bedrijf bedrijf);

    void update(Interface_Bestelling updatedBestelling);
}
