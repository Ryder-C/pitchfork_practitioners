package pitchfork_practitioners;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
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
        healthInfoTextArea.setEditable(true);
    }

    @FXML
    private void sendPrescription(ActionEvent event) {
    	
    	 String prescription = prescriptionField.getText();
    	 String preferredPharmacy = patient.getPrefferedPharmacy();
    	 
    	  if (preferredPharmacy != null && !preferredPharmacy.isEmpty()) {
    	        // You can now send the prescription to the preferred pharmacy
    	        sendPrescriptionToPharmacy(prescription, preferredPharmacy);
    	    } else {
    	        // Handle case where preferred pharmacy is not available
    	        System.out.println("Patient's preferred pharmacy is not available.");
    	    }
    }
    private void sendPrescriptionToPharmacy(String prescription, String preferredPharmacy) {
    	// TODO need to actually send to pharmacy?
        System.out.println("Prescription: " + prescription);
        System.out.println("Preferred Pharmacy: " + preferredPharmacy);
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    

    @FXML
    private void save(ActionEvent event) {

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
}
