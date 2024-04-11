package pitchfork_practitioners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
	private static Database instance = null;
	
	private String directory;
	
	private Database() {
		directory = "database";
	}
	
	// Assumed that if you are saving a message you are the sender
	public void saveMessage(Message message) throws IOException, FileNotFoundException {
		File messageRecord = new File(
				String.format("%s/%s_%s_messages.txt", directory, message.getSenderID(), message.getReceiverID()));
		if (!messageRecord.isFile()) {
			messageRecord = new File(String.format("%s/%s_%s_messages.txt", directory, message.getReceiverID(), message.getSenderID()));
			if (!messageRecord.isFile()) {
				messageRecord.createNewFile();
			}
		}
		
		message.saveMessage(messageRecord);
	}
	
	// Throws FileNotFound if the file does not exist
	// Throws IOException if there is an error reading the file
	public Message[] loadMessages(String viewerID, String otherID) throws IOException, FileNotFoundException {
		File messageRecord = new File(String.format("%s/%s_%s_messages.txt", directory, viewerID, otherID));
		if (!messageRecord.isFile()) {
			messageRecord = new File(String.format("%s/%s_%s_messages.txt", directory, otherID, viewerID));
			if (!messageRecord.isFile()) {
				throw new FileNotFoundException();
			}
		}
		
		return Message.loadAllMessages(messageRecord, viewerID, otherID);
	}
	
	 public List<String> getAllPatientIDs() {
	        List<String> patientIDs = new ArrayList<>();
	        File folder = new File(directory);
	        File[] listOfFiles = folder.listFiles();

	        if (listOfFiles != null) {
	            for (File file : listOfFiles) {
	                if (file.isFile()) {
	                    String fileName = file.getName();
	                    if (fileName.endsWith("_patient.txt")) {
	                        // Extract patient ID from the file name
	                        String patientID = fileName.replace("_patient.txt", "");
	                        patientIDs.add(patientID);
	                    }
	                }
	            }
	        }

	        return patientIDs;
	    }
	 
	
	public Patient loadRecord(String patientID) throws FileNotFoundException {
		File patientRecord = new File(String.format("%s/%s_patient.txt", directory, patientID));
		if (!patientRecord.isFile()) {
			throw new FileNotFoundException();
		}
		
		return Patient.loadFromFile(patientRecord);
	}
	
	public Patient loadRecordByNameAndBirthday(String patientName, String patientBirthday) throws FileNotFoundException {
	    // Search logic using name and birthday
	    File[] patientFiles = getPatientFiles(patientName, patientBirthday); // Implement getPatientFiles method

	    if (patientFiles.length == 0) {
	        throw new FileNotFoundException("Patient not found.");
	    }

	    // Loop through potential patient files
	    for (File patientFile : patientFiles) {
	        try {
	            Patient patient = Patient.loadFromFile(patientFile);
	            return patient; // Return the first matching patient
	        } catch (FileNotFoundException e) {
	            // Handle potential file not found exceptions during loop
	            System.out.println("Error reading file: " + patientFile.getName());
	        }
	    }

	    throw new FileNotFoundException("Patient not found.");
	}

	private File[] getPatientFiles(String patientName, String patientBirthday) throws FileNotFoundException {
	    List<File> potentialFiles = new ArrayList<>();
	    
	    // Get the project's base directory (assuming project structure)
	    URL resource = getClass().getResource("/");
	    File baseDir = new File(resource.getPath());
	    
	    // Search for files with matching names
	    for (File file : baseDir.listFiles()) {
	        if (file.isFile() && file.getName().toLowerCase().contains(patientName.toLowerCase())) {
	            potentialFiles.add(file);
	        }
	    }
	    
	    return potentialFiles.toArray(new File[potentialFiles.size()]);
	}
	
	public void saveRecord(Patient patient) throws IOException {
		String patientID = patient.getPatientID(); 
		File patientRecord = new File(String.format("%s/%s_patient.txt", directory, patientID));
		patientRecord.getParentFile().mkdirs();
		patientRecord.delete();
		patientRecord.createNewFile();
		
		patient.saveToFile(patientRecord);
	}
	
	// Create a login for a new user
	// Warning: This method will overwrite any existing login if another user with the same ID already exists
	public void createLogin(User user) throws IOException {
		File loginRecord = new File(String.format("%s/%s_login.txt", directory, user.getUsername()));
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
	  public Patient findPatient(String name, String birthday) throws FileNotFoundException {
	        File[] patientRecords = new File(directory).listFiles((dir, fileName) -> name.matches("\\d+_patient\\.txt")); 
	        for (File patientRecord : patientRecords) {
	            Patient patient = Patient.loadFromFile(patientRecord);
	            if (patient.getPatientName().equals(name) && patient.getPatientBirthday().equals(birthday)) {
	                return patient;
	            }
	        }
	        throw new FileNotFoundException("Patient not found.");
	    }
	
	public static void saveValue(FileWriter writer, String key, String value) throws IOException {
		writer.write(String.format("%s: %s\n", key, value));
	}
	
	public static String extractValue(Scanner scanner, String key) {
		scanner.findInLine(String.format("%s: ", key));
		if (scanner.hasNextLine()) {
			return scanner.nextLine();
		} else {
			return "";
		}
	}
	
	// Singleton pattern
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public void appendHealthInfoToFile(String patientID, String category, String newInfo) throws IOException {
	    File patientRecord = new File(String.format("%s/%s_patient.txt", directory, patientID));
	    if (!patientRecord.isFile()) {
	        throw new FileNotFoundException("Patient record not found.");
	    }

	    // Read the existing content from the file
	    StringBuilder fileContent = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new FileReader(patientRecord))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            fileContent.append(line).append("\n"); 
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new IOException("Failed to read existing health information from file.");
	    }

	    int index = fileContent.indexOf(category + ":");
	    if (index == -1) {
	        throw new IOException("Health information category not found in file.");
	    }

	    // Insert the new information after the line containing the category
	    String updatedContent = fileContent.insert(index + category.length() + 1, newInfo + ", ").toString();

	    // Write the updated content back to the file
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientRecord))) {
	        writer.write(updatedContent);
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new IOException("Failed to append health information to file.");
	    }
	}

}
