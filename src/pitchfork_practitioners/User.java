package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	private String username;
    private String password;
    
	public User(String id, String password) {
		this.username = id;
		this.password = password;
	}
    
    public void saveLogin(File loginRecord) throws IOException {
    	FileWriter writer = new FileWriter(loginRecord);
    	Database.saveValue(writer, "ID", username);
    	Database.saveValue(writer, "Password", password);
    	writer.close();
	}
    
	public static User loadLogin(File loginRecord) throws FileNotFoundException {
		Scanner scanner = new Scanner(loginRecord);
    	String username = Database.extractValue(scanner, "ID");
    	String password = Database.extractValue(scanner, "Password");
    	scanner.close();
    	
    	return new User(username, password);
	}
	
//	public String getName() {
//		return firstName + " " + lastName;
//	}
	
	public String getID() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
