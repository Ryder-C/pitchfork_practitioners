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
    
    private void handleSaveVitalsButtonAction(ActionEvent event) {
        String height = heightTextField.getText();
        String weight = weightTextField.getText();
        String bloodPressure = bloodPressureTextField.getText();

        // This is where you would typically perform validation on the input data.
        if (validateInput(height, weight, bloodPressure)) {
            try {
            	// Get current user
            	
                Database db = Database.getInstance();
                Patient p = db.loadRecord(Utils.showInputDialog("Enter patient ID"));
                
                // Save vitals to patient record
                p.setVitals(weight, height, bloodPressure);
                db.saveRecord(p);
                
                Utils.showMessageDialog("Vitals saved successfully.", AlertType.INFORMATION);
            } catch (Exception e) {
                Utils.showMessageDialog("Failed to save vitals.", AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            Utils.showMessageDialog("Invalid input. Please enter correct vitals.", AlertType.ERROR);
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
    private void handleBackButtonAction(ActionEvent event) {
        // Logic to navigate back, similar to the login page navigation.
        try {
			navigateTo("NurseView.fxml", event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
        // Logic to handle logout.
        try {
			navigateTo("LoginView.fxml", event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean validateInput(String height, String weight, String bloodPressure) {
        // Implement validation logic here
        // For simplicity, let's just check they are not empty.
        return !height.isEmpty() && !weight.isEmpty() && !bloodPressure.isEmpty();
    }
}