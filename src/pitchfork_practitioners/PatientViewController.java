	package pitchfork_practitioners;

	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
	import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
	    private Button pastVisitsButton;
	    @FXML
	    private Button messageCenterButton;
	    @FXML
	    private Button logOut;
	    @FXML
	    private Label patientNameLabel;
	    

	    @FXML
	    private TextField[] uneditableFields;

	    @FXML
	    private void initialize() {
	        uneditableFields = new TextField[]{vaccineField, healthCondField, medicationsField, phoneNumField, emailField,
	                addressField, prefPharmacyField, insuranceInfoField};
	        makeUneditable(uneditableFields);
	        
	        findPatient();
	        
	     
	        
	        
	    }
	    
	    private void findPatient() {
	    	getId();
	    	try {
				patient = db.loadRecord(patientIDString);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	        
	        
	    }
	    
	    @FXML
	    private void logoutButton(ActionEvent event) {
	        try {
				navigateTo("LoginPage.fxml", event);
			} catch (IOException e) {
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
	    
	    void getId() {	
	    		patientIDString = CurrentUser.getCurrentUser().getID();
				
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
	    
	    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    
	   
	    
	    	
	}
		
		
		


	    
	


