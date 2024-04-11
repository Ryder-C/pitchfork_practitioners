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
import java.util.Scanner;


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
    private Button loadMessageButton;

    @FXML
    private Button logoutButton;
    
    @FXML
    private Button clearTextBox;

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
        }
            else if ("NurseView.fxml".equals(previousFXML)) {
                populateAccordionDoctorNurse();
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
             senderID = "Doctor"; 
        	 receiverID = patientReceiverID;
        	} else if ("PatientView.fxml".equals(this.previousFXML)) {
        		
        	 senderID = staffReceiverID; 
             receiverID = currentUserID;
             
        	}else if ("NurseView.fxml".equals(this.previousFXML)) {
        	 senderID = "Nurse"; 
             receiverID = patientReceiverID;
        	}
        

        Message message = new Message(messageText, senderID, receiverID);
        
        try {
            Database.getInstance().saveMessage(message); 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save message");
        }

    }
    
    @FXML
    private void handleLoadMessageButtonAction() {
        try {
            String filename = patientReceiverID + "_" + currentUserID + "_messages.txt";
            File file = new File("database", filename);
            Scanner scanner = new Scanner(file);

            chatTextArea.clear();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                chatTextArea.appendText(line + "\n");
            }

            scanner.close(); // Close the scanner
        } catch (FileNotFoundException e) {
            System.err.println("Failed to load messages");
        }
    }


    private String getOtherID() {
        if ("DoctorView.fxml".equals(previousFXML)) {
            return "Doctor";
        } else if ("PatientView.fxml".equals(previousFXML)) {
            return currentUserID;
        } else if ("NurseView.fxml".equals(previousFXML)) {
            return "Nurse";
        }
        return null;
    }

    @FXML
    private void clearTextBox() {
        chatTextArea.clear();

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
