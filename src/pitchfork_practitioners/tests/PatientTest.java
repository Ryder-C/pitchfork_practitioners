package pitchfork_practitioners.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pitchfork_practitioners.Database;
import pitchfork_practitioners.Patient;
import pitchfork_practitioners.User;

class PatientTest {

	@Test
	void testPatient() {
		Database db = Database.getInstance();
		
		// Create user to test with
		User newUser = new User("TestUser", "TestPassword");
		assertDoesNotThrow(() -> db.createLogin(newUser));
		
		// Create a new patient with example inputs
		Patient testPatient = new Patient(newUser.getID(), "123-456-7890", "test@test.com", "123 Test St", "CVS",
				"Blue Cross Blue Shield", "Flu Shot", "None", "None", "Test Patient", "01/01/2000");
		assertDoesNotThrow(() -> db.saveRecord(testPatient));
		
		// Find the patient by ID
		Patient foundPatient = assertDoesNotThrow(() -> db.loadRecord(newUser.getID()));
		assertEquals(testPatient, foundPatient);
		
		// Update the patient record
		testPatient.setEmailAddress("test_new@test.com");
		assertDoesNotThrow(() -> db.saveRecord(testPatient));
		foundPatient = assertDoesNotThrow(() -> db.loadRecord(newUser.getID()));
		assertEquals(testPatient, foundPatient);
		
		// Find patient with incorrect name and birthday
		assertThrows(Exception.class, () -> db.loadRecordByNameAndBirthday("Incorrect Patient", "01/01/2000"));
		assertThrows(Exception.class, () -> db.loadRecordByNameAndBirthday("Test Patient", "01/01/2001"));
		
		// Find patient with incorrect ID
		assertThrows(Exception.class, () -> db.loadRecord("Definitely Not an ID"));
		
		db.clearDatabase();
	}

}
