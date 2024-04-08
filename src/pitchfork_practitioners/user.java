package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	protected String firstName;
	protected String lastName;
	
	private String id;
    private String password;
    
	public User(String firstName, String lastName, String id, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.password = password;
	}
    
    public void saveLogin(File loginRecord) throws IOException {
    	FileWriter writer = new FileWriter(loginRecord);
    	Database.saveValue(writer, "First Name", firstName);
    	Database.saveValue(writer, "Last Name", lastName);
    	Database.saveValue(writer, "ID", id);
    	Database.saveValue(writer, "Password", password);
	}
    
	public static User loadLogin(File loginRecord) throws FileNotFoundException {
		Scanner scanner = new Scanner(loginRecord);
    	String firstName = Database.extractValue(scanner, "First Name");
    	String lastName = Database.extractValue(scanner, "Last Name");
    	String id = Database.extractValue(scanner, "ID");
    	String password = Database.extractValue(scanner, "Password");
    	
    	return new User(firstName, lastName, id, password);
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String getID() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
}
