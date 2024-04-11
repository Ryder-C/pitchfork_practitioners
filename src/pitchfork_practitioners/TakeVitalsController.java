package pitchfork_practitioners;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TakeVitalsController {

    @FXML
    private Button pastVisitsButton;

    @FXML
    private Button messageCenterButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField bloodPressureTextField;

    @FXML
    private void initialize() {
        // Initialization code, if any.
    }

    @FXML
    private void pastVisitsButton(ActionEvent event) {
        try {
			navigateTo("PatientViewPastVisits.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
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
    private void logoutButton(ActionEvent event) {
        try {
			navigateTo("LoginPage.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
}