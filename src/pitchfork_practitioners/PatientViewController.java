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
		
		//declarations
		
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
	        if(patient.getPatientName() == null || patient.getPatientName().equals("")) {
	        	patientNameLabel.setText(CurrentUser.getCurrentUser().getUsername());
	        }
	        else {
	        	patientNameLabel.setText(patient.getPatientName());
	        }
	        
	        
	        //runs after scene has switched
	        Platform.runLater(() -> {
	            findThisPatient();
	            fillText();
	            fillOutNullFields();
	        });
	        
	    }
	    
	    //uses the id from the user to find patient information
	    private void findThisPatient() {
	    	
	    	try {
	    		getId();
				patient = db.loadRecord(patientIDString);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Utils.showMessageDialog("No patient information found. Please fill out your medical and personal information "
						+ "in the patient view using the edit and save buttons.",
						AlertType.INFORMATION);
			}
	    }
	    
	    //navigates to past visits page
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
	    	 try {
	             FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageCenterView.fxml"));
	             Parent root = loader.load();
	             MessageCenterController controller = loader.getController();
	             controller.setPreviousFXML("PatientView.fxml", patientIDString); 
	             Scene scene = new Scene(root);
	             Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	             stage.setScene(scene);
	             stage.show();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	        
	    }
	    
	    
	    //logs user and sends them to login page
	    @FXML
	    private void logoutButton(ActionEvent event) {
	        try {
				navigateTo("LoginPage.fxml", event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	    
	    
	    //allows information to become editable to the user
	    @FXML
	    private void editButton(ActionEvent event) {
	        makeEditable(uneditableFields);
	        
	    }

	    //saves information to the patient 
	    @FXML
	    private void saveButton(ActionEvent event) {
	        makeUneditable(uneditableFields);
	        saveInfo();
	        try {
				db.saveRecord(patient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utils.showMessageDialog("error", AlertType.ERROR);
			}
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
	    
	    //retrieves id from the current user
	    void getId() {	
	    		patientIDString = CurrentUser.getCurrentUser().getID();
				
		}
	    
	    //fills in all textfields with patient's information
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
	    
	    //saves all information entered to patient obj
	    void saveInfo() {
	    	patient.setVaccines(vaccineField.getText());
	    	patient.setPrevConditions(healthCondField.getText());
	    	patient.setPrevMeds(medicationsField.getText());
	    	patient.setPhoneNumber(phoneNumField.getText());
	    	patient.setEmailAddress(emailField.getText());
	    	patient.setHomeAddress(addressField.getText());
	    	patient.setPrefferedPharmacy(prefPharmacyField.getText());
	    	patient.setInsuranceInfo(insuranceInfoField.getText());
	    	patient.setPatientID(patientIDString);
	    }
	    
	    //navigates to different scenes 
	    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
	    }
	    
	    //changes null to blank
	    private void fillOutNullFields() {
	        for (TextField textField : uneditableFields) {
	            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
	                textField.setText("");
	            }
	        }
	    }
	    
	    	
	}
		
		
		


	    
	


