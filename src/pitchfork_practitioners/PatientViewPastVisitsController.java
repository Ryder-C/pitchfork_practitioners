	package pitchfork_practitioners;

	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.ListView;
	import javafx.stage.Stage;

	import java.io.FileNotFoundException;
	import java.io.IOException;
	import javafx.event.ActionEvent;
	

	public class PatientViewPastVisitsController {
		
		Database db = Database.getInstance();
		Patient patient = new Patient();
		Patient patient2 = new Patient();
		Patient patient3 = new Patient();
		Patient patient4 = new Patient();
		Patient patient5 = new Patient();
		
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
	    
	    private String[] options = {"Visit 1", "Visit 2", "Visit 3", "Visit 4", "Visit 5"};
	    
	    
	     
	        
	    
	    @FXML
	    public void initialize() {
	    	pastVisitsListView.getItems().addAll(options);
	    }
	    
	    @FXML
	    private void pastVisitsExitButton(ActionEvent event) {
	        try {
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
	    
	    
	    
	    	
	
		
		
		


	    
	


