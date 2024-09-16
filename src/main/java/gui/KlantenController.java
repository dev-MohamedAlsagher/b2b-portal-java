package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import domein.DomeinController;
import domein.Interface_Adres;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_GoedKeuringLeverancier;
import domein.Interface_Klant;
import domein.Interface_Leverancier;
import domein.Klant;
import domein.Observer;

public class KlantenController implements Observer {

	@FXML
	private Label klantNaam;

	@FXML
	private Label emailKlant;

	@FXML
	private Label telefoonNummer;

	@FXML
	private Label straat;

	@FXML
	private Label stad;

	@FXML
	private Button bedrijven;

	@FXML
	private Button notificaties;

	@FXML
	private Button betalingStatus;

	@FXML
	private Button bestellingen;

	@FXML
	private Button profiel;

	@FXML
	private Button klanten;

	@FXML
	private Button logout;

	@FXML
	private Button klantSwitch;

	@FXML
	private Button bestellingenSwitch;

	@FXML
	private FontAwesomeIconView BELL;

	@FXML
	private FontAwesomeIconView USER;

	@FXML
	private FontAwesomeIconView USERS;

	@FXML
	private FontAwesomeIconView LIST;

	@FXML
	private TableView<Interface_Klant> KlantenTable;

	@FXML
	private TableView<Interface_Bestelling> bestellingenView;

	@FXML
	private TableColumn<Interface_Klant, String> klantNaamColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> aantalBestellingenColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderIDColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> datumColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> bedragColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderStatusColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> betalingStatusColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> totaleBestellingen;

	@FXML
	private GridPane KlantView;

	private Stage primaryStage;

	@FXML
	private VBox KlantRechts;
	@FXML
	private VBox informatieVBox;
	@FXML
	private ImageView logo;
	@FXML
	private ImageView delawareLogo;

	@FXML
	private FontAwesomeIconView btnRechts;

	@FXML
	private FontAwesomeIconView btnLinks;

	@FXML
	private Label lblPage;

	private int begin = 0;
	private int pageCounter = 1;

	@FXML
	ComboBox<Integer> cbxAantal;

	private DomeinController controller;

	private ObservableList<Interface_Bestelling> bestellingenList = FXCollections.observableArrayList();

	public static String convertToList(String input) {
		String[] tokens = input.replaceAll("\\[|\\]", "").split(", ");

		List<String> list = new ArrayList<>();

		for (String token : tokens) {
			list.add(token);
		}

		String result = String.join(", ", list);
		return result;
	}

	public KlantenController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void start() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Klanten.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			if (primaryStage != null) {
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} else {
				System.err.println("PrimaryStage is null. Scene not set.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	public void initialize() {
		String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image1 = new Image(url1);
		delawareLogo.setImage(image1);

		lblPage.setText("" + pageCounter);
		cbxAantal.setValue(5);
		cbxAantal.getItems().addAll(5, 10, 20);

		klantNaamColumn.setCellValueFactory(new PropertyValueFactory<>("gebruikersnaam"));

		KlantenTable.setItems(getKlanten(cbxAantal.getValue(), begin));

		aantalBestellingenColumn.setCellValueFactory(new PropertyValueFactory<>("aantalBestellingen"));

		totaleBestellingen.setCellValueFactory(new PropertyValueFactory<>("totaalBestellingen"));

		KlantenTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				KlantRechts.setVisible(true);
				bestellingenList.clear();
				bestellingenList.addAll(getBestellingen(newSelection));
				bestellingenView.setItems(bestellingenList);
				bestellingenView.getItems().forEach(item -> {
					item.addObserver(this);
				});
				vulKlantDetailsTable(newSelection);

				betalingStatus.setVisible(false);

			}
		});

		bestellingenView.setOnMouseClicked(event -> {
			if (bestellingenView.getSelectionModel().getSelectedItem() != null) {
				Interface_Bestelling selectedOrder = bestellingenView.getSelectionModel().getSelectedItem();
				isbetaald(selectedOrder);
			}
		});

		betalingStatus.setOnAction(event -> {
			if (bestellingenView.getSelectionModel().getSelectedItem() != null) {
				Interface_Bestelling selectedOrder = bestellingenView.getSelectionModel().getSelectedItem();
				String idOrder = selectedOrder.getIdOrder();

				Interface_Klant selectedKlant = KlantenTable.getSelectionModel().getSelectedItem();

				controller.veranderStatusOrder(idOrder, selectedKlant);
				betalingStatus.setVisible(false);

			}
		});

		btnRechts.setOnMouseClicked(e -> {
			pageCounter++;
			lblPage.setText("" + pageCounter);

			begin += cbxAantal.getValue();
			Integer aantal = cbxAantal.getValue();
			if (begin > 0) {
				btnLinks.setVisible(true);
			} else {
				btnLinks.setVisible(false);
			}

			KlantenTable.setItems(getKlanten(aantal, begin));
			KlantenTable.refresh();

			if (KlantenTable.getItems().size() < cbxAantal.getValue()) {
				btnRechts.setVisible(false);
			}

		});

		btnLinks.setOnMouseClicked(e -> {
			pageCounter--;
			lblPage.setText("" + pageCounter);

			begin -= cbxAantal.getValue();
			Integer aantal = cbxAantal.getValue();
			if (begin <= 0) {
				btnLinks.setVisible(false);
				begin = 0;
			}
			btnRechts.setVisible(true);
			KlantenTable.setItems(getKlanten(aantal, begin));
			KlantenTable.refresh();

		});

		cbxAantal.setOnAction(e -> {
			pageCounter = 1;
			begin = 0;

			Integer aantal = cbxAantal.getValue();
			KlantenTable.setItems(getKlanten(aantal, begin));
			KlantenTable.refresh();

			btnLinks.setVisible(false);
			btnRechts.setVisible(true);
			if (KlantenTable.getItems().size() < cbxAantal.getValue()) {
				btnRechts.setVisible(false);
			}
		});

	}

	@Override
	public void update(Interface_Bestelling updatedBestelling) {
		bestellingenView.refresh();
	}

	@FXML
	private void switchToKlant() {
		KlantView.setVisible(true);
		bestellingenView.setVisible(false);
		switchInformatiePosition();
	}

	@FXML
	private void switchToBestellingen() {
		bestellingenView.setVisible(true);
		KlantView.setVisible(false);
		switchInformatiePosition();
	}

	private void switchInformatiePosition() {
		informatieVBox.getChildren().remove(KlantView);
		informatieVBox.getChildren().remove(bestellingenView);

		if (KlantView.isVisible()) {
			informatieVBox.getChildren().add(0, KlantView);
		} else if (bestellingenView.isVisible()) {
			informatieVBox.getChildren().add(0, bestellingenView);
		}
	}

	private ObservableList<Interface_Klant> getKlanten(int aantal, int begin) {
		ObservableList<Interface_Klant> klanten = controller.getKlantenByLeverancierId(aantal, begin);
		for (Interface_Klant klant : klanten) {
			klant.addObserver(this);
		}
		return klanten;
	}

	private void isbetaald(Interface_Bestelling newSelection) {
		if (newSelection.getBetalingStatus().equals("Betaald")) {
			betalingStatus.setVisible(false);
		} else {
			betalingStatus.setVisible(true);
		}
	}

	private void vulKlantDetailsTable(Interface_Klant klant) {
		Interface_Bedrijf bedrijf = controller.getBedrijfByKlant(klant);
		String telNr = String.valueOf(bedrijf.getTelefoonnummer());
		Interface_Adres adres = controller.getAdresByIdAdres(bedrijf.getIdAdres());

		if (klant != null) {
			String url = bedrijf.getLogo();
			Image image = new Image(url);
			logo.setImage(image);
			klantNaam.setText(klant.getGebruikersnaam());
			emailKlant.setText(klant.getEmail());
			telefoonNummer.setText(telNr);
			straat.setText(adres.getStraat());
			stad.setText(adres.getStad());

			setupKlantBestellingen(klant);
		}
	}

	public void setupKlantBestellingen(Interface_Klant klant) {
		orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("IdOrder"));
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("Datum"));
		bedragColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));
		orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));
		betalingStatusColumn.setCellValueFactory(new PropertyValueFactory<>("betalingStatus"));

		betalingStatusColumn.setCellFactory(column -> {
			return new TableCell<Interface_Bestelling, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						if (item.equals("Betaald")) {
							setTextFill(Color.GREEN);
						} else if (item.equals("Niet Betaald")) {
							setTextFill(Color.RED);
						} else {
							setTextFill(Color.BLACK);
						}
					}
				}
			};
		});

		bestellingenView.setItems(bestellingenList);

	}

	private ObservableList<Interface_Bestelling> getBestellingen(Interface_Klant klant) {
		Interface_Leverancier leverancier = controller.getLeverancier();
		return leverancier.getBestellingenByKlant(klant.getIdKlant());
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
	public void switchProfielPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profiel.fxml"));
			ProfielController profielController = new ProfielController(primaryStage);
			profielController.setController(controller);
			loader.setController(profielController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchBestellingPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bestellingen.fxml"));
			BestellingController bestellingController = new BestellingController(primaryStage);
			bestellingController.setController(controller);
			loader.setController(bestellingController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Interface_Leverancier leverancier) {
	}

	@Override
	public void update(Interface_GoedKeuringLeverancier goedKeuringLeverancier) {

	}

	@Override
	public void update(Interface_Bedrijf bedrijf) {
	}

	@Override
	public void update(Klant klant) {
		KlantenTable.refresh();
	}

}