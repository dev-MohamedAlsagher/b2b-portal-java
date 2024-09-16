package gui;

import java.io.IOException;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AanmeldenController {

	private DomeinController controller;
	private Stage primaryStage;

	@FXML
	private Button Login;
	@FXML
	private TextField gebruikersnaam;
	@FXML
	private PasswordField wachtwoord;

	@FXML
	private Label warningMessage;
	@FXML
	private Hyperlink wachtwoordVergeten;
	@FXML
	private Text loginError;
	@FXML
	private CheckBox isAdmin;
	@FXML
	private ImageView delawareLogo;

	public AanmeldenController(DomeinController controller, Stage primaryStage) {
		this.controller = controller;
		this.primaryStage = primaryStage;
	}

	public void start() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Aanmelden.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("delaware B2B-Portaal");
			String url1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPBqAOQhpbb8mYBZiCv3WPum0MjflrbzxR-rOrjUVmKbtM6SVz7HBNZV6Wm_nzcgPqGU0&usqp=CAU";
			Image image1 = new Image(url1);
			primaryStage.getIcons().add(image1);
			primaryStage.show();
			String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Delaware-logo.svg/1200px-Delaware-logo.svg.png";
			Image image = new Image(url);
			delawareLogo.setImage(image);

			gebruikersnaam.setOnMouseClicked(event -> {
				warningMessage.setVisible(false);
			});

			wachtwoord.setOnMouseClicked(event -> {
				warningMessage.setVisible(false);
			});

			wachtwoord.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					handleLogin(new ActionEvent());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleLogin(ActionEvent event) {
		String username = gebruikersnaam.getText();
		String password = wachtwoord.getText();

		if (!isAdmin.isSelected()) {
			boolean loggedIn = controller.aanmelden(username, password);
			if (loggedIn) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bestellingen.fxml"));
					BestellingController bestellingController = new BestellingController(primaryStage);
					bestellingController.setController(controller);
					loader.setController(bestellingController);
					Parent root = loader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
				} catch (IOException e) {
					warningMessage.setVisible(true);
					warningMessage.setText("Email en wachtwoord combinatie is fout!!");
					e.printStackTrace();
				}
			} else {
				warningMessage.setVisible(true);
				warningMessage.setText("Email en wachtwoord combinatie is fout!!");
			}
		} else {
			boolean loggedIn = controller.aanmeldenAdmin(username, password);
			if (loggedIn) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bedrijven.fxml"));
					BedrijvenController bedrijvenController = new BedrijvenController(primaryStage);
					bedrijvenController.setController(controller);
					loader.setController(bedrijvenController);
					Parent root = loader.load();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.centerOnScreen();
					primaryStage.show();
				} catch (IOException e) {
					warningMessage.setVisible(true);
					warningMessage.setText("Email en wachtwoord combinatie is fout!!");
					e.printStackTrace();
				}
			} else {
				warningMessage.setVisible(true);
				warningMessage.setText("Email en wachtwoord combinatie is fout!!");
			}
		}
	}

}
