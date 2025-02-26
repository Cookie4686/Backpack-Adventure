package scene.popup;

import component.Button;
import component.SettingSlider;
import javafx.scene.layout.VBox;

public class SettingPopup extends VBox {
	private static SettingPopup instance;
	private Popup popup;
	private SettingSlider musicSlider, themeSlider;

	public SettingPopup() {
		super();
		popup = new Popup("Settings");

		musicSlider = new SettingSlider("Music Volume");
		themeSlider = new SettingSlider("Theme Volume");
		getChildren().setAll(musicSlider, themeSlider);
		popup.setCenter(this);

		Button closeButton = new Button("Close", 128, 32);
		closeButton.setOnAction(_ -> popup.hide());
		popup.getBottomBox().getChildren().setAll(closeButton);
	}

	public Popup getPopup() {
		return popup;
	}

	public SettingSlider getMusicSlider() {
		return musicSlider;
	}

	public SettingSlider getThemeSlider() {
		return themeSlider;
	}

	public static SettingPopup getInstance() {
		if (instance == null) {
			instance = new SettingPopup();
		}
		return instance;
	}
}
