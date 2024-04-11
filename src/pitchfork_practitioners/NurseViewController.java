package pitchfork_practitioners;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class NurseViewController {

    
    @FXML
    private Button messageCenterButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button takeVitalsButton;

    @FXML
    private Button preExaminationButton;

    @FXML
    private Label patientsLabel;

    @FXML
    private Label nurseViewPt1Label;

    @FXML
    private Accordion patientsAccordion;

    @FXML
    private TitledPane selectPatientTitledPane;

    @FXML
    public void initialize() {
        // Initialization code
    }

    @FXML
    private void handleMessageCenterButtonAction(ActionEvent event) {
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
    private void handleLogoutButtonAction(ActionEvent event) {
    	try {
			navigateTo("LoginPage.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void handleTakeVitalsButtonAction() {
        // Handle the take vitals button click
    }

    @FXML
    private void handlePastVisitsButtonAction() {
    	
    }
    @FXML
    private void handlePreExaminationButtonAction(ActionEvent event) {
    	try {
			navigateTo("NursePreExamination.fxml", event);
		} catch (IOException e) {
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
}

    
