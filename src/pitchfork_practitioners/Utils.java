package pitchfork_practitioners;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class Utils {
	// Pop up window helper methods for reporting data to user
	public static String showInputDialog(String text) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setHeaderText(text);
		return dialog.showAndWait().get();
	}
	
	public static void showMessageDialog(String text, AlertType type) {
		Alert alert = new Alert(type);
		alert.setContentText(text);
		alert.showAndWait();
	}
}
