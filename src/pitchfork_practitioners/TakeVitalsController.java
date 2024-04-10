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
    private Button saveVitalsButton;

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
    private void handleSaveVitalsButtonAction(ActionEvent event) {
        String height = heightTextField.getText();
        String weight = weightTextField.getText();
        String bloodPressure = bloodPressureTextField.getText();

        // This is where you would typically perform validation on the input data.
        if (validateInput(height, weight, bloodPressure)) {
            try {
                Database db = Database.getInstance();
                // Assuming you have a method to save vitals in your Database class.
                db.saveVitals(height, weight, bloodPressure);
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
    private void handleBackButtonAction(ActionEvent event) {
        // Logic to navigate back, similar to the login page navigation.
        navigateTo("PreviousView.fxml", event);
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
        // Logic to handle logout.
        navigateTo("LoginView.fxml", event);
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

    // Event handlers for other buttons if needed.
}