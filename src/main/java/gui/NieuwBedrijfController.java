package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NieuwBedrijfController {

	private DomeinController controller;

	@FXML
	private Stage primaryStage;
	@FXML
	private ImageView delawareLogo, logo;
	@FXML
	private Button voegBedrijfToeButton, bedrijven, btnVoegToe;
	@FXML
	private TextField bedrijfsnaam, sector, iban, btwNummer, straat, nummer, stad, postcode, levgebruikersnaam,
			levemail, klantgebruikersnaam, klantemail, bedrmail, bedrtelefoonnummer;
	@FXML
	private CheckBox isActief;
	@FXML
	private PasswordField levwachtwoord, klantwachtwoord;
	@FXML
	private Tooltip tltipBedrijfsnaam, tltipSector, tltipBedrijfsmail, tltipTelefoonnr, tltipIBAN, tltipBTW,
			tltipStraat, tltipNummer, tltipStad, tltipPostcode, tltipLevUsername, tltipLevmail, tltipLevwachtw,
			tltipKlantUsername, tltipKlantmail, tltipKlantWachtw;
	@FXML
	private ListView<String> betaalMethodesUpdate;

	public NieuwBedrijfController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	public void initialize() {
		String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image = new Image(url);
		delawareLogo.setImage(image);
		betaalMethodesUpdate.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		betaalMethodesUpdate.getItems().addAll("CreditCard", "PayPal", "Bancontact", "Klarna");

		setTooltipDelay();
		setTooltipStyle();
		setStyleBack();
	}

	@FXML
	public void uitloggen(ActionEvent event) {
		controller.uitloggen();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		AanmeldenController aanmeldenController = new AanmeldenController(controller, new Stage());
		aanmeldenController.start();
	}

	@FXML
	public void switchGoedKeuring(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/BedrijfWijziging.fxml"));
			GoedKeuringController goedKeuringController = new GoedKeuringController(primaryStage);
			goedKeuringController.setController(controller);
			loader.setController(goedKeuringController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@FXML
	public void switchBedrijven(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bedrijven.fxml"));
			BedrijvenController bedrijvenController = new BedrijvenController(primaryStage);
			bedrijvenController.setController(controller);
			loader.setController(bedrijvenController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@FXML
	protected void voegBedrijfToe(ActionEvent event) {
		String txtBedrijfsnaam = bedrijfsnaam.getText();
		String txtSector = sector.getText();
		String txtBedrmail = bedrmail.getText();
		String txtBedrtelefoonnummer = bedrtelefoonnummer.getText();
		String txtIban = iban.getText();
		String txtBtwNummer = btwNummer.getText();
		String txtStraat = straat.getText();
		String txtNummer = nummer.getText();
		String txtStad = stad.getText();
		String txtPostcode = postcode.getText();
		boolean txtIsActief = isActief.isSelected();
		String txtLevgebruikersnaam = levgebruikersnaam.getText();
		String txtLevemail = levemail.getText();
		String txtLevwachtwoord = levwachtwoord.getText();
		String txtKlantgebruikersnaam = klantgebruikersnaam.getText();
		String txtKlantemail = klantemail.getText();
		String txtKlantwachtwoord = klantwachtwoord.getText();

		if (!validate(txtBedrijfsnaam, txtSector, txtBedrmail, txtBedrtelefoonnummer, txtIban, txtBtwNummer, txtStraat,
				txtNummer, txtStad, txtPostcode, txtLevgebruikersnaam, txtLevemail, txtLevwachtwoord,
				txtKlantgebruikersnaam, txtKlantemail, txtKlantwachtwoord)) {
			return;
		}
		;

		String betaalMethodes = betaalMethodesUpdate.getSelectionModel().getSelectedItems().toString();

		controller.maakBedrijf(
				txtBedrijfsnaam,
				txtSector,
				txtBedrmail,
				txtBedrtelefoonnummer,
				txtIban,
				txtBtwNummer,
				txtIsActief,
				txtStraat,
				txtNummer,
				txtStad,
				txtPostcode,
				txtLevgebruikersnaam,
				txtLevemail,
				txtLevwachtwoord,
				betaalMethodes,
				txtKlantgebruikersnaam,
				txtKlantemail,
				txtKlantwachtwoord);
		clearFields();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("bedrijf toevoegen");
		alert.setHeaderText("bedrijf toevoegen");
		alert.setContentText("Bedrijf is toegevoegd!");
		alert.showAndWait();
	}

	private void clearFields() {
		bedrijfsnaam.clear();
		sector.clear();
		bedrmail.clear();
		bedrtelefoonnummer.clear();
		iban.clear();
		btwNummer.clear();
		straat.clear();
		nummer.clear();
		stad.clear();
		postcode.clear();
		isActief.setSelected(false);
		levgebruikersnaam.clear();
		levemail.clear();
		levwachtwoord.clear();
		klantgebruikersnaam.clear();
		klantemail.clear();
		klantwachtwoord.clear();
		betaalMethodesUpdate.getSelectionModel().clearSelection();
	}

	private boolean validate(String txtBedrijfsnaam, String txtSector, String txtBedrmail, String txtBedrtelefoonnummer,
			String txtIban, String txtBtwNummer, String txtStraat, String txtNummer, String txtStad, String txtPostcode,
			String txtLevgebruikersnaam, String txtLevemail, String txtLevwachtwoord, String txtKlantgebruikersnaam,
			String txtKlantemail, String txtKlantwachtwoord) {
		boolean isValid = true;
		String ibanBtwRegex = "^[A-Za-z0-9]+$";
		String phoneRegex = "^[0-9]+$";
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

		if (txtBedrijfsnaam.isEmpty()) {
			tltipBedrijfsnaam.setText("Bedrijfsnaam vereist!");

			bedrijfsnaam.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtSector.isEmpty()) {
			tltipSector.setText("Sector vereist!");

			sector.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtBedrmail.isEmpty()) {
			tltipBedrijfsmail.setText("Bedrijfs e-mail vereist!");

			bedrmail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtBedrmail.matches(emailRegex)) {
			tltipBedrijfsmail.setText("Geef een geldige e-mail in!");

			bedrmail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtBedrtelefoonnummer.isEmpty()) {
			tltipTelefoonnr.setText("Telefoonnummer vereist!");

			bedrtelefoonnummer.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtBedrtelefoonnummer.matches(phoneRegex)) {
			tltipTelefoonnr.setText("Geef een geldig telefoonnummer in!");

			bedrtelefoonnummer.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtIban.isEmpty()) {
			tltipIBAN.setText("IBAN vereist!");

			iban.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtIban.matches(ibanBtwRegex)) {
			tltipIBAN.setText("Geef een geldige IBAN in!");

			iban.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtBtwNummer.isEmpty()) {
			tltipBTW.setText("BTW-nummer vereist!");

			btwNummer.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtBtwNummer.matches(ibanBtwRegex)) {
			tltipBTW.setText("geef een geldig BTW-nummer in!");

			btwNummer.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtStraat.isEmpty()) {
			tltipStraat.setText("Straat vereist!");

			straat.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtNummer.isEmpty()) {
			tltipNummer.setText("Nummer vereist!");

			nummer.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtStad.isEmpty()) {
			tltipStad.setText("Stad vereist!");

			stad.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtPostcode.isEmpty()) {
			tltipPostcode.setText("Postcode vereist!");

			postcode.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtLevgebruikersnaam.isEmpty()) {
			tltipLevUsername.setText("Leverancier gebruikersnaam vereist!");

			levgebruikersnaam.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtLevemail.isEmpty()) {
			tltipLevmail.setText("Leverancier e-mail vereist!");

			levemail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtLevemail.matches(emailRegex)) {
			tltipLevmail.setText("Geef een geldige e-mail in!");

			levemail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtLevwachtwoord.isEmpty()) {
			tltipLevwachtw.setText("wachtwoord vereist!");

			levwachtwoord.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtLevwachtwoord.length() < 8) {
			tltipLevwachtw.setText("Het wachtwoord moet minstens 8 karakters bevatten!");

			levwachtwoord.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}

		if (txtKlantgebruikersnaam.isEmpty()) {
			tltipKlantUsername.setText("Klant gebruikersnaam vereist!");

			klantgebruikersnaam.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtKlantemail.isEmpty()) {
			tltipKlantmail.setText("Klant e-mail vereist!");

			klantemail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (!txtKlantemail.matches(emailRegex)) {
			tltipKlantmail.setText("Geef een geldige e-mail in!");

			klantemail.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtKlantwachtwoord.isEmpty()) {
			tltipKlantWachtw.setText("Wachtwoord vereist!");

			klantwachtwoord.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		if (txtKlantwachtwoord.length() < 8) {
			tltipKlantWachtw.setText("Het wachtwoord moet minstens 8 karakters bevatten!");

			klantwachtwoord.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 2 0 ;");
			isValid = false;
		}
		return isValid;
	}

	private void setStyleBack() {
		bedrijfsnaam.setOnKeyTyped(event -> {
			tltipBedrijfsnaam.setText("");
			bedrijfsnaam.setStyle("");
		});
		sector.setOnKeyTyped(event -> {
			tltipSector.setText("");
			sector.setStyle("");
		});
		bedrmail.setOnKeyTyped(event -> {
			tltipBedrijfsmail.setText("");
			bedrmail.setStyle("");
		});
		bedrtelefoonnummer.setOnKeyTyped(event -> {
			tltipTelefoonnr.setText("");
			bedrtelefoonnummer.setStyle("");
		});
		iban.setOnKeyTyped(event -> {
			tltipIBAN.setText("");
			iban.setStyle("");
		});
		btwNummer.setOnKeyTyped(event -> {
			tltipBTW.setText("");
			btwNummer.setStyle("");
		});
		straat.setOnKeyTyped(event -> {
			tltipStraat.setText("");
			straat.setStyle("");
		});
		nummer.setOnKeyTyped(event -> {
			tltipNummer.setText("");
			nummer.setStyle("");
		});
		stad.setOnKeyTyped(event -> {
			tltipStad.setText("");
			stad.setStyle("");
		});
		postcode.setOnKeyTyped(event -> {
			tltipPostcode.setText("");
			postcode.setStyle("");
		});
		levgebruikersnaam.setOnKeyTyped(event -> {
			tltipLevUsername.setText("");
			levgebruikersnaam.setStyle("");
		});
		levemail.setOnKeyTyped(event -> {
			tltipLevmail.setText("");
			levemail.setStyle("");
		});
		levwachtwoord.setOnKeyTyped(event -> {
			tltipLevwachtw.setText("");
			levwachtwoord.setStyle("");
		});
		klantgebruikersnaam.setOnKeyTyped(event -> {
			tltipKlantUsername.setText("");
			klantgebruikersnaam.setStyle("");
		});
		klantemail.setOnKeyTyped(event -> {
			tltipKlantmail.setText("");
			klantemail.setStyle("");
		});
		klantwachtwoord.setOnKeyTyped(event -> {
			tltipKlantWachtw.setText("");
			klantwachtwoord.setStyle("");
		});
	}

	public void setTooltipDelay() {
		tltipBedrijfsnaam.setShowDelay(Duration.ZERO);
		tltipSector.setShowDelay(Duration.ZERO);
		tltipBedrijfsmail.setShowDelay(Duration.ZERO);
		tltipTelefoonnr.setShowDelay(Duration.ZERO);
		tltipIBAN.setShowDelay(Duration.ZERO);
		tltipBTW.setShowDelay(Duration.ZERO);
		tltipStraat.setShowDelay(Duration.ZERO);
		tltipNummer.setShowDelay(Duration.ZERO);
		tltipStad.setShowDelay(Duration.ZERO);
		tltipPostcode.setShowDelay(Duration.ZERO);
		tltipLevUsername.setShowDelay(Duration.ZERO);
		tltipLevmail.setShowDelay(Duration.ZERO);
		tltipLevwachtw.setShowDelay(Duration.ZERO);
		tltipKlantUsername.setShowDelay(Duration.ZERO);
		tltipKlantmail.setShowDelay(Duration.ZERO);
		tltipKlantWachtw.setShowDelay(Duration.ZERO);
	}

	public void setTooltipStyle() {
		tltipBedrijfsnaam
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipSector
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipBedrijfsmail
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipTelefoonnr
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipIBAN.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipBTW.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipStraat
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipNummer
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipStad.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipPostcode
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipLevUsername
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipLevmail
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipLevwachtw
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipKlantUsername
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipKlantmail
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
		tltipKlantWachtw
				.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-padding: 2; -fx-font-size: 12;");
	}

}