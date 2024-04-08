package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Patient {
	private String patientID;
	private String phoneNumber;
    private String emailAddress;
    private String homeAddress;   
    
	public Patient(String patientID, String phoneNumber, String emailAddress, String homeAddress) {
		this.patientID = patientID;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
	}
    
    // Patient save method
    // Please change this method if you change the types of data stored in the patient file
	public void saveToFile(File patientRecord) throws IOException {
		FileWriter writer = new FileWriter(patientRecord);
		Database.saveValue(writer, "Patient ID", patientID);
		Database.saveValue(writer, "Phone Number", phoneNumber);
		Database.saveValue(writer, "Email Address", emailAddress);
		Database.saveValue(writer, "Home Address", homeAddress);
		writer.close();
	}
    
    // Patient loader method
    // Please change this method if you change the types of data stored in the patient file
	public static Patient loadFromFile(File patientRecord) throws FileNotFoundException {
		Scanner scanner = new Scanner(patientRecord);
		String patientID = Database.extractValue(scanner, "Patient ID");
		String phoneNumber = Database.extractValue(scanner, "Phone Number");
		String emailAddress = Database.extractValue(scanner, "Email Address");
		String homeAddress = Database.extractValue(scanner, "Home Address");
		scanner.close();
		
		
		return new Patient(patientID, phoneNumber, emailAddress, homeAddress);
	}
}
