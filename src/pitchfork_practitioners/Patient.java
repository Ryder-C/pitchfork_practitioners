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
    private String prefferedPharmacy;  
    private String insuranceInfo;  
    private String vaccines; 
    private String prevConditions; 
    private String prevMeds;  
    
	public Patient(String patientID, String phoneNumber, String emailAddress, String homeAddress) {
		this.patientID = patientID;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
	}
	
	
    //default constructor
    public Patient() {
		
	}



	// Patient save method
    // Please change this method if you change the types of data stored in the patient file
	public void saveToFile(File patientRecord) throws IOException {
		FileWriter writer = new FileWriter(patientRecord);
		Database.saveValue(writer, "Patient ID", patientID);
		Database.saveValue(writer, "Phone Number", phoneNumber);
		Database.saveValue(writer, "Email Address", emailAddress);
		Database.saveValue(writer, "Home Address", homeAddress);
		Database.saveValue(writer, "Preferred Pharmacy", prefferedPharmacy);
		Database.saveValue(writer, "Insurance Information", insuranceInfo);
		Database.saveValue(writer, "Vaccines", vaccines);
		Database.saveValue(writer, "Previous Conditions", prevConditions);
		Database.saveValue(writer, "Previous Medications", prevMeds);
		
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

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getPrefferedPharmacy() {
		return prefferedPharmacy;
	}

	public void setPrefferedPharmacy(String prefferedPharmacy) {
		this.prefferedPharmacy = prefferedPharmacy;
	}

	public String getInsuranceInfo() {
		return insuranceInfo;
	}

	public void setInsuranceInfo(String insuranceInfo) {
		this.insuranceInfo = insuranceInfo;
	}

	public String getVaccines() {
		return vaccines;
	}

	public void setVaccines(String vaccines) {
		this.vaccines = vaccines;
	}

	public String getPrevConditions() {
		return prevConditions;
	}

	public void setPrevConditions(String prevConditions) {
		this.prevConditions = prevConditions;
	}

	public String getPrevMeds() {
		return prevMeds;
	}

	public void setPrevMeds(String prevMeds) {
		this.prevMeds = prevMeds;
	}
}
