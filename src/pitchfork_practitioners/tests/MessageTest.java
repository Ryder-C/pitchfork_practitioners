package pitchfork_practitioners.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pitchfork_practitioners.Database;
import pitchfork_practitioners.Message;
import pitchfork_practitioners.User;

class MessageTest {

	@Test
	void testMessageCenter() {
		Database db = Database.getInstance();
		
		// Crate user to test with
		User newUser = new User("TestUser", "TestPassword");
		assertDoesNotThrow(() -> db.createLogin(newUser));
		
		// Test sending a message from patient to doctor
		Message testMessage = new Message(newUser.getID(), "Doctor", "Message from patient to doctor");
		assertDoesNotThrow(() -> db.saveMessage(testMessage));
		// Test loading the message
		assertEquals(testMessage.getText(), assertDoesNotThrow(() -> db.loadMessages(newUser.getID(), "Doctor"))[0].getText());
		
		// Test sending a message from doctor to patient
		Message testMessage2 = new Message("Doctor", newUser.getID(), "Message from doctor to patient");
		assertDoesNotThrow(() -> db.saveMessage(testMessage2));
		// Test loading the message
		assertEquals(testMessage2.getText(), assertDoesNotThrow(() -> db.loadMessages("Doctor", newUser.getID()))[1].getText());
		
		db.clearDatabase();
	}

}
