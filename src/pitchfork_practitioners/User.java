package pitchfork_practitioners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class User {
	private String username;
    private String password;
    private String id;
    
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		
		// Generate random 5 digit ID
		Random rand = new Random();
		this.id = String.valueOf(rand.nextInt(100000));
	}
	
	User(String username, String password, String id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}
    
    public void saveLogin(File loginRecord) throws IOException {
    	FileWriter writer = new FileWriter(loginRecord);
    	Database.saveValue(writer, "Username", username);
    	Database.saveValue(writer, "Password", password);
    	Database.saveValue(writer, "ID", id);
    	writer.close();
	}
    
	public static User loadLogin(File loginRecord) throws FileNotFoundException {
		Scanner scanner = new Scanner(loginRecord);
    	String username = Database.extractValue(scanner, "Username");
    	String password = Database.extractValue(scanner, "Password");
    	String id = Database.extractValue(scanner, "ID");
    	scanner.close();
    	
    	return new User(username, password, id);
	}
	
	
	public String getID() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
}
