package pitchfork_practitioners.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pitchfork_practitioners.Database;
import pitchfork_practitioners.User;

class AccountTest {

	@Test
	void testCreateAccount() {
		Database db = Database.getInstance();
		
		// Test creating an account
		User newUser = new User("TestUser", "TestPassword");
		assertDoesNotThrow(() -> db.createLogin(newUser));
		
		// Test logging in
		User loggedUser = assertDoesNotThrow(() -> db.login(newUser.getUsername(), newUser.getPassword()));
		assertEquals(newUser.getUsername(), loggedUser.getUsername());
		assertEquals(newUser.getPassword(), loggedUser.getPassword());
		assertEquals(newUser.getID(), loggedUser.getID());
		
		// Test logging in with incorrect password
		assertThrows(Exception.class, () -> db.login(newUser.getUsername(), "IncorrectPassword"));
		
		// Test logging in with incorrect username
		assertThrows(Exception.class, () -> db.login("IncorrectUser", newUser.getPassword()));
		
		db.clearDatabase();
	}
}
