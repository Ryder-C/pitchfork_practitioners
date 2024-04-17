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
    private String patientName;
    private String patientBirthday;
    private String patientConcernString;
    private String patientPrescriptionString;
    
    // Vitals
    private String weight;
    private String height;
    private String bloodPressure;
    
    
	
    //default constructor
    public Patient() {
		
	}

    

	public Patient(String patientID, String phoneNumber, String emailAddress, String homeAddress,
			String prefferedPharmacy, String insuranceInfo, String vaccines, String prevConditions, String prevMeds, String patientName, String patientBirthday) {
		this.patientID = patientID;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.prefferedPharmacy = prefferedPharmacy;
		this.insuranceInfo = insuranceInfo;
		this.vaccines = vaccines;
		this.prevConditions = prevConditions;
		this.prevMeds = prevMeds;
		this.patientName = patientName;
		this.patientBirthday = patientBirthday;

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
		Database.saveValue(writer, "Name", patientName);
		Database.saveValue(writer, "Birthday", patientBirthday);
		Database.saveValue(writer, "Concerns", patientConcernString);
		Database.saveValue(writer, "Prescriptions", patientPrescriptionString);
		
		// Vitals
		Database.saveValue(writer, "Weight", String.valueOf(weight));
		Database.saveValue(writer, "Height", String.valueOf(height));
		Database.saveValue(writer, "Blood Pressure", String.valueOf(bloodPressure));

		
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
		String pharmacy = Database.extractValue(scanner, "Preferred Pharmacy:");
		String insurance = Database.extractValue(scanner, "Insurance Information");
		String vaccines = Database.extractValue(scanner, "Vaccines");
		String prevConditions = Database.extractValue(scanner, "Previous Conditions");
		String prevMeds = Database.extractValue(scanner, "Previous Medications");
		String name = Database.extractValue(scanner, "Name");
		String birthday = Database.extractValue(scanner, "Birthday");
		String concerns = Database.extractValue(scanner, "Concerns");
		String prescriptions = Database.extractValue(scanner, "Prescriptions");
		
		// Vitals
		String weight = Database.extractValue(scanner, "Weight");
		String height = Database.extractValue(scanner, "Height");
		String bloodPressure = Database.extractValue(scanner, "Blood Pressure");


		
		scanner.close();
		
		
		Patient p = new Patient(patientID, phoneNumber, emailAddress, homeAddress, pharmacy, insurance, vaccines, prevConditions, prevMeds, name, birthday);
		p.setPatientConcernString(concerns);
		p.setPatientPrescriptionString(prescriptions);
		p.setVitals(weight, height, bloodPressure);
		
		return p;
	}
	
	// Override equals method for testing
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Patient) {
			Patient p = (Patient) obj;
			return p.patientID.equals(this.patientID);
		}
		return false;
	}
	
	public void setVitals(String weight, String height, String bloodPressure) {
		this.weight = weight;
		this.height = height;
		this.bloodPressure = bloodPressure;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getPatientBirthday() {
		return patientBirthday;
	}
	
	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
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


	public String getPatientWeight() {
		return weight;
	}



	public void setPatientWeight(String weight) {
		this.weight = weight;
	}



	public String getPatientHeight() {
		return height;
	}



	public String getPatientBloodPressure() {
		return bloodPressure;
	}



	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}



	public void setHeight(String height) {
		this.height = height;
	}



	public String getPatientConcernString() {
		return patientConcernString;
	}



	public void setPatientConcernString(String patientConcernString) {
		this.patientConcernString = patientConcernString;
	}



	public String getPatientPrescriptionString() {
		return patientPrescriptionString;
	}



	public void setPatientPrescriptionString(String patientPrescriptionString) {
		this.patientPrescriptionString = patientPrescriptionString;
	}
	
	public String getHealthInfo() {
		 return "Vaccines: " + vaccines + "\n" +
                 "Previous Health Issues: " + prevConditions + "\n" +
                 "Previous Medications: " + prevMeds;
	}
	
}
