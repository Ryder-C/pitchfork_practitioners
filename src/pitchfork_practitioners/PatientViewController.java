	package pitchfork_practitioners;

	import javafx.fxml.FXML;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.control.Button;
	import javafx.scene.image.ImageView;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import javafx.application.Platform;
	import javafx.event.ActionEvent;
	

	public class PatientViewController {
		
		Database db = Database.getInstance();
		Patient patient = new Patient();
		
		private String patientIDString;
		
	    @FXML
	    private ImageView logoImageView;
	    @FXML
	    private TextField patientIdField;
	    @FXML
	    private TextField vaccineField;
	    @FXML
	    private TextField healthCondField;
	    @FXML
	    private TextField medicationsField;
	    @FXML
	    private TextField phoneNumField;
	    @FXML
	    private TextField emailField;
	    @FXML
	    private TextField addressField;
	    @FXML
	    private TextField prefPharmacyField;
	    @FXML
	    private TextField insuranceInfoField;
	    @FXML
	    private Button editButton;
	    @FXML
	    private Button saveButton;
	    @FXML
	    private Button findButton;

	    @FXML
	    private TextField[] uneditableFields;

	    @FXML
	    private void initialize() {
	        uneditableFields = new TextField[]{vaccineField, healthCondField, medicationsField, phoneNumField, emailField,
	                addressField, prefPharmacyField, insuranceInfoField};
	        makeUneditable(uneditableFields);
	     
	        
	        
	    }
	    
	    @FXML
	    private void findButton(ActionEvent event) {
	        enterId();
	        try {
				patient = db.loadRecord(patientIDString);
				fillText();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }

	    @FXML
	    private void editButton(ActionEvent event) {
	        makeEditable(uneditableFields);
	        
	    }

	    @FXML
	    private void saveButton(ActionEvent event) {
	        makeUneditable(uneditableFields);
	    }

	    // makes text field uneditable
	    void makeUneditable(TextField[] arr) {
	        for (TextField textField : arr) {
	            textField.setEditable(false);
	            textField.setStyle("-fx-control-inner-background: #DCDCDC;");
	        }
	    }

	    // makes text boxes editable
	    void makeEditable(TextField[] arr) {
	        for (TextField textField : arr) {
	            textField.setEditable(true);
	            textField.setStyle("-fx-control-inner-background: #FFFFFF;");
	        }
	    }
	    
	    void enterId() {	
	    		patientIDString = patientIdField.getText();
				
		}
	    
	    void fillText() {
			vaccineField.setText(patient.getVaccines());
			healthCondField.setText(patient.getPrevConditions());
			medicationsField.setText(patient.getPrevMeds());
			phoneNumField.setText(patient.getPhoneNumber());
			emailField.setText(patient.getEmailAddress());
			addressField.setText(patient.getHomeAddress());
			prefPharmacyField.setText(patient.getPrefferedPharmacy());
			insuranceInfoField.setText(patient.getInsuranceInfo());
			
		}
	    
	    	
	}
		
		
		


	    
	


