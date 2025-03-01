package scene.popup;

import component.Button;
import component.VolumeSlider;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sound.BackgroundSongPlayer;

public class SettingPopup extends GridPane {
	private static SettingPopup instance;
	private Popup popup;
	private VolumeSlider musicSlider, themeSlider, sfxSlider;

	public SettingPopup() {
		super();
		popup = new Popup("Settings");

		setAlignment(Pos.TOP_CENTER);
		setHgap(16);
		setVgap(16);
		add(createText("Music Volume"), 0, 0);
		add(createText("Theme Volume"), 0, 1);
		add(createText("SFX Volume"), 0, 2);
		add(musicSlider = new VolumeSlider(24, false, () -> BackgroundSongPlayer.pause(),
				() -> BackgroundSongPlayer.play()), 1, 0);
		add(themeSlider = new VolumeSlider(24), 1, 1);
		add(sfxSlider = new VolumeSlider(24), 1, 2);

		popup.setCenter(this);

		Button closeButton = new Button("Close", 128, 32);
		closeButton.setOnAction(_ -> popup.hide());
		popup.getBottomBox().getChildren().setAll(closeButton);
	}

	private Text createText(String name) {
		Text text = new Text(name);
		text.setFont(Font.font("Consolas", FontWeight.BOLD, 24));
		return text;
	}

	public Popup getPopup() {
		return popup;
	}

	public Slider getMusicSlider() {
		return musicSlider.getSlider();
	}

	public Slider getThemeSlider() {
		return themeSlider.getSlider();
	}

	public Slider getSfxSlider() {
		return sfxSlider.getSlider();
	}

	public static SettingPopup getInstance() {
		if (instance == null) {
			instance = new SettingPopup();
		}
		return instance;
	}
}
