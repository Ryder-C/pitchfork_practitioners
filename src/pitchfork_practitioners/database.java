package pitchfork_practitioners;

public class database {
	private static database instance = null;
	
	private database() {
		
	}
	
	public static database getInstance() {
		if (instance == null) {
            instance = new database();
        }
        return instance;
    }
}
