package pitchfork_practitioners;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NursePreExaminationController {
	Database db = Database.getInstance();
	Patient patient = new Patient();
	
	@FXML
	private TextArea patientInfoTextArea;

	@FXML
	private TextArea patientAllergiesTextArea;

	@FXML
	private TextArea patientConcernsTextArea;
	
	@FXML
	private Button logoutButton;

	@FXML
	private Button saveButton;
	
	
	@FXML
	private void initialize() {
		// Initialization code if needed
	}

	@FXML
	private void messageCenterButton(ActionEvent event) {

	}

	@FXML
	private void logoutButton(ActionEvent event) {
		try {
			navigateTo("LoginPage.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void saveButton(ActionEvent event) {
		patientInfoTextArea.setEditable(false);
		patientAllergiesTextArea.setEditable(false);
		patientConcernsTextArea.setEditable(false);
	}

	private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}

