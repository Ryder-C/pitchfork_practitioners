package pitchfork_practitioners;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;

public class DoctorViewController {

    Database db = Database.getInstance();
    Patient patient = new Patient();

    private String patientName;
    private String patientBirthday;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField patientBirthdayField;

    @FXML
    private TextArea healthInfoTextArea;

    @FXML
    private Button enterHealthInfoButton;

    @FXML
    private Button findPatientHistoryButton;

    @FXML
    private Button sendPrescriptionButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button saveButton;
    
    @FXML
    private TextField prescriptionField;


    @FXML
    private void initialize() {
        // Initialization code if needed
    }

    @FXML
    private void findPatientHistory(ActionEvent event) {
        System.out.println("patient history");

        enterPatientDetails();
        try {
            patient = db.loadRecordByNameAndBirthday(patientName, patientBirthday);
            fillPatientHistory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle file not found exception
        }
    }

    @FXML
    private void enterHealthInfo(ActionEvent event) {
        System.out.println("health info");

        healthInfoTextArea.setEditable(true);
    }

    @FXML
    private void sendPrescription(ActionEvent event) {
        String prescription = prescriptionField.getText();
        String preferredPharmacy = patient.getPrefferedPharmacy();

        if (preferredPharmacy != null) {
            Utils.showMessageDialog("Prescription sent to: " + preferredPharmacy, AlertType.INFORMATION);

        } else {
            System.out.println("Patient's preferred pharmacy is not available.");
        }
    }

    @FXML
    private void pastVisitsButton(ActionEvent event) {
        try {
			navigateTo("PatientViewPastVisits.fxml", event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @FXML
    private void messageCenterButton(ActionEvent event) {
        
        System.out.println("Message Center");

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
    private void save(ActionEvent event) {
    	
        System.out.println("Saved");

    	healthInfoTextArea.setEditable(false);
    }

    void enterPatientDetails() {

        patientName = patientNameField.getText();
        patientBirthday = patientBirthdayField.getText();
    }

    void fillPatientHistory() {
        // Populate patient's history into the healthInfoTextArea
        healthInfoTextArea.setText("Previously Prescribed Medication: " + patient.getPrevMeds() + "\n"
                + "History of Immunization: " + patient.getVaccines() + "\n"
                + "Previous Health Issues: " + patient.getPrevConditions());
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
