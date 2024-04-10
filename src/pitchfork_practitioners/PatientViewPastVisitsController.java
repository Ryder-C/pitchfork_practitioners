	package pitchfork_practitioners;

	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
	import java.io.IOException;
	import javafx.event.ActionEvent;
	

	public class PatientViewPastVisitsController {
		
		Database db = Database.getInstance();
		Patient patient = new Patient();
		
		
		
		private String patientIDString;
		
		@FXML
	    private ListView<String> pastVisitsListView;
		
		@FXML
	    private Label heightLabel;
		
		@FXML
	    private Label weightLabel;
		
		@FXML
	    private Label bloodPressureLabel;
		
		@FXML
	    private Button exitButton;
		
		@FXML
	    private TextArea concernsArea;
		
		@FXML
	    private TextArea pescriptionsArea;
	    
	    
	    
	    
	    
	     
	        
	    
	    @FXML
	    public void initialize() {
	    	concernsArea.setEditable(false);
	    	pescriptionsArea.setEditable(false);
	    	try {
	    		patientIDString = CurrentUser.getCurrentUser().getID();
	    		patient = db.loadRecord(patientIDString);
	    		displayInfo();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    public void displayInfo() {
	    	String weightString = String.format("%.2f", patient.getPatientWeight());
	    	weightLabel.setText(weightString);
	    	heightLabel.setText(patient.getPatientHeightString());
	    	String bloodpressString = String.format("%.2f", patient.getPatientBloodPressure());
	    	bloodPressureLabel.setText(bloodpressString);
	    	pescriptionsArea.setText(patient.getPatientPrescriptionString());
	    }
	    
	    @FXML
	    private void pastVisitsExitButton(ActionEvent event) {
	        try {
	        	if("doctor".equals(CurrentUser.getCurrentUser().getUsername())) {
	        		navigateTo("DoctorView.fxml", event);
	        	}else
					navigateTo("PatientView.fxml", event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
	    
	    
	    
	    	
	
		
		
		


	    
	


