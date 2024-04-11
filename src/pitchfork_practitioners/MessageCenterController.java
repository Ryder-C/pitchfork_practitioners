package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageCenterController {

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private Button sendMessageButton;

    @FXML
    private Button backButton;

    @FXML
    private Accordion patientsAccordion;

    @FXML
    private Button logoutButton;

    private String previousFXML;
    
    String staffReceiverID;
    String patientReceiverID;
    private String currentUserID;


    public void setPreviousFXML(String previousFXML, String currentUserID) {
        this.previousFXML = previousFXML;
        this.currentUserID = currentUserID;
        
        if("DoctorView.fxml".equals(this.previousFXML)) {
            populateAccordionDoctorNurse();
        	}
            
        else if ("PatientView.fxml".equals(previousFXML)) {
            populateAccordionPatient();
        	} else {
                populateAccordionDoctorNurse();
        	}
    }

    @FXML
    private void initialize() {
       
    }

    private void populateAccordionPatient() {
    	patientsAccordion.getPanes().clear(); 
        
        TitledPane doctorPane = new TitledPane("Doctor", createButton("Doctor"));
        
        TitledPane nursePane = new TitledPane("Nurse", createButton("Nurse"));
        
        patientsAccordion.getPanes().addAll(doctorPane, nursePane);
		
	}
    
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setOnAction(event -> handleSelection(text));
        return button;
    }

 

	private void populateAccordionDoctorNurse() {
        TitledPane selectRecipientPane = (TitledPane) patientsAccordion.getPanes().get(0);

        VBox patientsList = new VBox();

        Database database = Database.getInstance();

        List<String> patientIDs = database.getAllPatientIDs();

        for (String patientId : patientIDs) {
            Button patientButton = new Button(patientId);
            patientButton.setOnAction(event -> handlePatientSelection(patientId));
            patientsList.getChildren().add(patientButton);
        }

        selectRecipientPane.setContent(patientsList);
    }
	
	private void handleSelection(String selectedID) {
	        staffReceiverID = selectedID;
    }
	
    private void handlePatientSelection(String patientId) {
            patientReceiverID = patientId;
    }

    @FXML
    private void handleSendMessageButtonAction() {
    	String senderID = null;
    	String receiverID = null;
    	
        String messageText = chatTextArea.getText();
        
        if("DoctorView.fxml".equals(this.previousFXML)) {
             senderID = "Staff"; 
        	 receiverID = patientReceiverID;
        	} else if ("PatientView.fxml".equals(this.previousFXML)) {
        	 senderID = currentUserID; 
             receiverID = staffReceiverID;
        	}
        
        System.out.println(messageText);
        System.out.println(senderID);
        System.out.println(receiverID);

        Message message = new Message(messageText, senderID, receiverID);
        
        try {
            Database.getInstance().saveMessage(message); // Call saveMessage method from Database
            System.out.println("Message saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save message");
        }

    }


    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            navigateTo(previousFXML, event);
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

    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
