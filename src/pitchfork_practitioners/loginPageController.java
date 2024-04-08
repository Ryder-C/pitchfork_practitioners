package pitchfork_practitioners;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button createAccountButton;

    @FXML
    private void initialize() {
        // Initialization code
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText().toLowerCase();
        try {
            if ("doctor".equals(username)) {
                navigateTo("doctorView.fxml", event);
            } else if ("nurse".equals(username)) {
                navigateTo("nurseView.fxml", event);
            } else if ("patient".equals(username)) {
                navigateTo("patientView.fxml", event);
            } else {
                // wrong username
                System.out.println("User role is not recognized.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Handle the exception properly
        }
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        //Code for account creation
    }

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
