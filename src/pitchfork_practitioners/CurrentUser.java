package pitchfork_practitioners;

public class CurrentUser {
	private static User currentUser = null;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static void logout() {
		currentUser = null;
	}
}
