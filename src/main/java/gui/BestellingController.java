package gui;

import domein.DomeinController;
import domein.Interface_Bestelling;
import domein.Interface_BestellingDetails;
import domein.Interface_Leverancier;
import domein.Interface_Notificatie;
import domein.Interface_Product;
import domein.ProductEnDetailsGecombineerd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class BestellingController {

	private DomeinController controller;
	private Stage primaryStage;

	@FXML
	private TableView<Interface_Bestelling> bestellingTable;

	@FXML
	private TableView<ProductEnDetailsGecombineerd> bestellingDetailsTable;

	@FXML
	private Label totaalProductenLabel;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderidColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> datumColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> orderstatusColumn;

	@FXML
	private TableColumn<Interface_Bestelling, String> betalingstatusColumn;

	@FXML
	private TableColumn<Interface_BestellingDetails, String> productNaamColumn;

	@FXML
	private TableColumn<Interface_BestellingDetails, Double> stukprijsColumn;

	@FXML
	private TableColumn<Interface_BestellingDetails, Double> btwPrijsColumn;

	@FXML
	private TableColumn<Interface_BestellingDetails, Integer> aantalColumn;

	@FXML
	private TableColumn<Interface_BestellingDetails, Double> totaalPrijsPerProductColumn;

	@FXML
	private ImageView delawareLogo;

	@FXML
	private Button notificaties;

	@FXML
	private Button bestellingen;

	@FXML
	private Button profiel;

	@FXML
	private Button klanten;

	@FXML
	private Button betalingsherinnering;

	@FXML
	private VBox BestellingenRechts;

	@FXML
	private FontAwesomeIconView btnRechts;

	@FXML
	private FontAwesomeIconView btnLinks;

	@FXML
	private Label lblPage;

	@FXML
	private Label datumHerinnering;

	@FXML
	private Label laatsteBetalingsherinnering;

	@FXML
	ComboBox<Integer> cbxAantal;

	private int begin = 0;
	private int pageCounter = 1;

	public BestellingController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void start() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/bestellingen.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			if (primaryStage != null) {
				primaryStage.setScene(scene);

			} else {
				System.err.println("PrimaryStage is null. Scene not set.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		String url1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
		Image image1 = new Image(url1);
		delawareLogo.setImage(image1);
		orderidColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		orderstatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		betalingstatusColumn.setCellValueFactory(new PropertyValueFactory<>("betalingStatus"));
		productNaamColumn.setCellValueFactory(new PropertyValueFactory<>("productNaam"));
		stukprijsColumn.setCellValueFactory(new PropertyValueFactory<>("eenheidsPrijs"));
		btwPrijsColumn.setCellValueFactory(new PropertyValueFactory<>("btw"));
		aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));
		totaalPrijsPerProductColumn.setCellValueFactory(new PropertyValueFactory<>("totaalPrijs"));

		lblPage.setText("" + pageCounter);
		cbxAantal.setValue(5);
		cbxAantal.getItems().addAll(5, 10, 20);

		bestellingTable.setItems(getBestellingen(cbxAantal.getValue(), begin));

		bestellingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				BestellingenRechts.setVisible(true);
				vulBestellingDetailsTable(newSelection);
				betalingsherinnering.setOnAction(stuurBetalingsherinnering(newSelection));
			}

		});

		betalingstatusColumn.setCellFactory(column -> {
			return new TableCell<Interface_Bestelling, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					setText(empty ? "" : getItem());
					setGraphic(null);

					if (!isEmpty()) {
						if (item.equals("Betaald"))
							setTextFill(Color.GREEN);
						else
							setTextFill(Color.RED);
					}
				}
			};
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

			bestellingTable.setItems(getBestellingen(aantal, begin));
			bestellingTable.refresh();

			if (bestellingTable.getItems().size() < cbxAantal.getValue()) {
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
			bestellingTable.setItems(getBestellingen(aantal, begin));
			bestellingTable.refresh();

		});

		cbxAantal.setOnAction(e -> {
			pageCounter = 1;
			begin = 0;

			Integer aantal = cbxAantal.getValue();
			bestellingTable.setItems(getBestellingen(aantal, begin));
			bestellingTable.refresh();

			btnLinks.setVisible(false);
			btnRechts.setVisible(true);
			if (bestellingTable.getItems().size() < cbxAantal.getValue()) {
				btnRechts.setVisible(false);
			}
		});

	}

	private void isbetaald(Interface_Bestelling newSelection) {
		if (newSelection.getBetalingStatus().equals("Betaald")) {
			betalingsherinnering.setVisible(false);
		} else {
			betalingsherinnering.setVisible(true);
		}
	}

	private ObservableList<Interface_Bestelling> getBestellingen(int aantal, int begin) {
		Interface_Leverancier leverancier = controller.getLeverancier();
		ObservableList<Interface_Bestelling> bestellingen = leverancier.getBestellingen(aantal, begin);
		return bestellingen;
	}

	public void setController(DomeinController controller) {
		this.controller = controller;
	}

	private void vulBestellingDetailsTable(Interface_Bestelling bestelling) {
		if (bestelling != null) {
			ObservableList<Interface_BestellingDetails> details = bestelling.getBestellingDetails();
			List<ProductEnDetailsGecombineerd> gecombineerdeData = new ArrayList<>();
			Interface_Notificatie notificatie = controller.getNotificatieByIdOrder(bestelling.getIdOrder());
			isbetaald(bestelling);
			if (notificatie != null) {
				if (bestelling.getBetalingStatus().equals("Niet Betaald")) {
					datumHerinnering.setText(notificatie.getDatum());
					laatsteBetalingsherinnering.setVisible(true);
					datumHerinnering.setVisible(true);
					if (notificatie.getHerinneringPeriode()) {
						betalingsherinnering.setVisible(false);
					}
				}
			} else {
				laatsteBetalingsherinnering.setVisible(false);
				datumHerinnering.setVisible(false);
			}

			for (Interface_BestellingDetails bestellingDetails : details) {
				Interface_Product product = controller.getProductByProductId(bestellingDetails);
				gecombineerdeData.add(new ProductEnDetailsGecombineerd(product.getNaam(),
						bestellingDetails.getEenheidsPrijs(), ((product.getBtwTarief() / 100) * product.getEenheidsprijs()),
						product.getAantal()));
			}

			bestellingDetailsTable.setItems(FXCollections.observableArrayList(gecombineerdeData));
			double totaalPrijs = gecombineerdeData.stream()
					.mapToDouble(data -> (data.getEenheidsPrijs() + data.getBtw()) * data.getAantal())
					.sum();
			totaalProductenLabel.setText(String.format("€ %.2f", totaalPrijs));

		}
	}

	public EventHandler<ActionEvent> stuurBetalingsherinnering(Interface_Bestelling bestelling) {
		return event -> {
			controller.maakNotificatie(bestelling);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Betallings herinnering");
			alert.setHeaderText("Betallings herinnering");
			alert.setContentText("Betallingsherinnering is verzonden");
			alert.showAndWait();
			betalingsherinnering.setVisible(false);

		};
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
	public void switchKlantenPagina(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Klanten.fxml"));
			KlantenController klantenController = new KlantenController(primaryStage);
			klantenController.setController(controller);
			loader.setController(klantenController);
			Parent root = loader.load();
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

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

}