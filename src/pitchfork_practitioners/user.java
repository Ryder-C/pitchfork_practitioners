package pitchfork_practitioners;

public class User {
	protected String firstName;
	protected String lastName;
	
	private String id;
    private String password;
    
	
	public String getName() {
		return firstName + " " + lastName;
	}
}
