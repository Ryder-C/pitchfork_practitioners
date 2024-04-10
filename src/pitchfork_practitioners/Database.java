package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
	private static Database instance = null;
	
	private String directory;
	
	private Database() {
		directory = "database";
	}
	
	public Patient loadRecord(String patientID) throws FileNotFoundException {
		File patientRecord = new File(String.format("%s/%s_patient.txt", directory, patientID));
		if (!patientRecord.isFile()) {
			throw new FileNotFoundException();
		}
		
		return Patient.loadFromFile(patientRecord);
	}
	
	public void saveRecord(Patient patient) throws IOException {
		String patientID = ""; // Placeholder until patient.getPatientID is implemented
		File patientRecord = new File(String.format("%s/%s_patient.txt", directory, patientID));
		patientRecord.getParentFile().mkdirs();
		patientRecord.delete();
		patientRecord.createNewFile();
		
		patient.saveToFile(patientRecord);
	}
	
	// Crate a login for a new user
	// Warning: This method will overwrite any existing login if another user with the same ID already exists
	public void createLogin(User user) throws IOException {
		File loginRecord = new File(String.format("%s/%s_login.txt", directory, user.getID()));
		loginRecord.getParentFile().mkdirs();
		loginRecord.delete();
		loginRecord.createNewFile();
		
		user.saveLogin(loginRecord);
	}
	
	public User login(String id, String password) throws FileNotFoundException {
		File userRecord = new File(String.format("%s/%s_login.txt", directory, id));
		if (!userRecord.isFile()) {
			throw new FileNotFoundException();
		}
		
		// Check for existing login and verify password
		User user = User.loadLogin(userRecord);
		if (!user.getPassword().equals(password)) {
			throw new FileNotFoundException();
		}
		
		return user;
	}
	
	public static void saveValue(FileWriter writer, String key, String value) throws IOException {
		writer.write(String.format("%s: %s\n", key, value));
	}
	
	public static String extractValue(Scanner scanner, String key) {
		scanner.findInLine(String.format("%s: ", key));
		return scanner.nextLine();
	}
	
	// Singleton pattern
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
}
