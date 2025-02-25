package scene.popup;

import application.Main;
import component.Button;

public class SettingPopup {
	public static void show() {
		Popup popup = new Popup("Settings");

		Button closeButton = new Button("Close", 128, 32);
		closeButton.setOnAction(_ -> Main.root.getChildren().remove(popup));

		popup.getBottomBox().getChildren().setAll(closeButton);
		Main.root.getChildren().add(popup);
	}
}
