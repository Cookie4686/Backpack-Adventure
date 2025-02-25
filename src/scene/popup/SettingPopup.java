package scene.popup;

import application.Main;
import component.Button;
import component.ButtonSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SettingPopup {
	public static void show() {
		Popup popup = new Popup(Color.AQUA);

		Text headingText = new Text("Settings");
		Button closeButton = new Button("Close", ButtonSize.LARGE);
		closeButton.setOnAction(_ -> Main.root.getChildren().remove(popup));

		popup.getChildren().setAll(headingText, closeButton);
		Main.root.getChildren().add(popup);
	}
}
