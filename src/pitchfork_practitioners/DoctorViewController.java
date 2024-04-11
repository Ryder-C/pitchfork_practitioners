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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;

public class DoctorViewController {

    Database db = Database.getInstance();
    Patient patient = new Patient();

    private String patientID;

    
    @FXML
    private TextField patientIDField;

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
    private TextArea patientHistoryTextArea;
    
    @FXML
    private TextField vaccineField;
    
    @FXML
    private TextField prevHealthIssuesField;
    
    @FXML
    private TextField prevMedicationsField;
    
    @FXML
    private TextField enterVaccineField;


    @FXML
    private void initialize() {
    }

    @FXML
    private void findPatientHistory(ActionEvent event) throws IOException {

        enterPatientDetails();
        try {
            patient = db.loadRecord(patientID);
            fillPatientHistory();
        } catch (FileNotFoundException e) {
        	
            Utils.showMessageDialog("Patient Not Found", AlertType.INFORMATION);
        }
    }


    @FXML
    private void sendPrescription(ActionEvent event) {
        String prescription = enterVaccineField.getText();
        String preferredPharmacy = patient.getPrefferedPharmacy();
        patient.setPatientPrescriptionString(prescription);

        if (preferredPharmacy != null) {
            String trimmedPharmacy = preferredPharmacy.replace("Preferred Pharmacy:", "").trim();
            Utils.showMessageDialog(prescription + " prescription sent to: " + trimmedPharmacy, AlertType.INFORMATION);
        } else {
            System.out.println("Patient's preferred pharmacy is not available. Please Enter Patient ID");
        }
    }

    @FXML
    private void enterHealthInfo(ActionEvent event) {
        String vaccines = " " + vaccineField.getText();
        String prevHealthIssues = " " + prevHealthIssuesField.getText();
        String prevMedications = " " + prevMedicationsField.getText();
        
        String Vaccines = "Vaccines";
        String PreviousConditions = "Previous Conditions";
        String PreviousMedications = "Previous Medications";

        try {
            if (!vaccines.isEmpty()) {
                db.appendHealthInfoToFile(patientID, Vaccines, vaccines);
            }
            if (!prevHealthIssues.isEmpty()) {
                db.appendHealthInfoToFile(patientID, PreviousConditions,  prevHealthIssues);
            }
            if (!prevMedications.isEmpty()) {
                db.appendHealthInfoToFile(patientID, PreviousMedications,  prevMedications);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        vaccineField.clear();
        prevHealthIssuesField.clear();
        prevMedicationsField.clear();
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
            controller.setPreviousFXML("DoctorView.fxml", "Doctor"); 
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
    

    void enterPatientDetails() {
        patientID = patientIDField.getText();
    }

    void fillPatientHistory() {
    	patientHistoryTextArea.setText("Previously Prescribed Medication: " + patient.getPrevMeds() + "\n"
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
