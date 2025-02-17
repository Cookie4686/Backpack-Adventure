package scene.popup;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SettingPopup {
	public static void show() {
		Text headingText = new Text("Settings");
		Button closeButton = new Button("Close");
		Popup popup = new Popup(Color.AQUA, headingText, closeButton);
		closeButton.setOnAction(event -> Main.root.getChildren().remove(popup));
		Main.root.getChildren().add(popup);
	}
}
