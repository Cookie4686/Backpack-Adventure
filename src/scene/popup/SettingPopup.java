package scene.popup;

import component.Button;
import javafx.scene.layout.VBox;

public class SettingPopup extends VBox {
	private static SettingPopup instance;
	private Popup popup;

	public SettingPopup() {
		super();
		popup = new Popup("Settings");

		popup.setCenter(this);

		Button closeButton = new Button("Close", 128, 32);
		closeButton.setOnAction(_ -> popup.hide());
		popup.getBottomBox().getChildren().setAll(closeButton);
	}

	public Popup getPopup() {
		return popup;
	}

	public static SettingPopup getInstance() {
		if (instance == null) {
			instance = new SettingPopup();
		}
		return instance;
	}
}
