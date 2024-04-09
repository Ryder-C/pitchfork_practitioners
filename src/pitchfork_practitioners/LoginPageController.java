package pitchfork_practitioners;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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
                navigateTo("DoctorView.fxml", event);
            } else if ("nurse".equals(username)) {
                navigateTo("NurseView.fxml", event);
            } else {
            	// normal user login
            	Database db = Database.getInstance();
				User user = db.login(usernameField.getText(), passwordField.getText());
				CurrentUser.setCurrentUser(user);
				
				navigateTo("PatientView.fxml", event);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Utils.showMessageDialog("Login Failed.", AlertType.ERROR);
		}
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        Database db = Database.getInstance();
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();
        
        User newUser = new User(username, password);
        try {
			db.createLogin(newUser);
			Utils.showMessageDialog("Account created successfully.", AlertType.INFORMATION);
		} catch (IOException e) {
			Utils.showMessageDialog("Failed to create account.", AlertType.ERROR);
			e.printStackTrace();
		}
    }

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
