package pitchfork_practitioners;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NursePreExaminationController {
	Database db = Database.getInstance();
	
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
	private Label title;
	
	
	
	@FXML
	private void initialize() {
		// Initialization code if needed
	}


	@FXML
	private void messageCenterButton(ActionEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageCenterView.fxml"));
            Parent root = loader.load();
            MessageCenterController controller = loader.getController();
            controller.setPreviousFXML("NurseView.fxml", "Staff"); 
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void onActionBack(ActionEvent event) {
	     try {
	            navigateTo("NurseView.fxml", event);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
		
		String concerns = patientConcernsTextArea.getText();
		
		String patID = Utils.showInputDialog("Enter patient ID:");
		
		try {
			Patient patient = db.loadRecord(patID);
			patient.setPatientConcernString(concerns);
			db.saveRecord(patient);
		} catch (IOException e) {
			e.printStackTrace();
			Utils.showMessageDialog("error", AlertType.ERROR);
		} 
		
		patientInfoTextArea.clear();
		patientAllergiesTextArea.clear();
		patientConcernsTextArea.clear();
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

