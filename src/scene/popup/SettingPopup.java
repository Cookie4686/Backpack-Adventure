package scene.popup;

import application.Main;
import component.GameButton;
import component.GameButtonType;
import component.VolumeSlider;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.MenuScene;
import sound.Sfx;
import sound.SfxPlayer;

public class SettingPopup extends GridPane {
	private static SettingPopup instance;
	private Popup popup;
	private VolumeSlider musicSlider, sfxSlider;

	public SettingPopup() {
		super();
		getChildren().clear();
		popup = new Popup("Settings");
		setAlignment(Pos.TOP_CENTER);
		setHgap(16);
		setVgap(16);

		GameButton menuButton = new GameButton(159, 70, GameButtonType.MENU);
		menuButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
			if (!MenuScene.isInMenuScene()) {
				Main.root.getChildren().clear();
				MenuScene.use();
			}
		});
		add(createText("Music Volume"), 0, 0);
		add(createText("SFX Volume"), 0, 1);
		add(musicSlider = new VolumeSlider(24), 1, 0);
		add(sfxSlider = new VolumeSlider(24), 1, 1);
		add(menuButton, 0, 2, 2, 1);
		setHalignment(menuButton, HPos.CENTER);
		popup.setCenter(this);

		GameButton closeButton = new GameButton(159, 70, GameButtonType.CLOSE);
		closeButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
		});
		popup.getBottomBox().getChildren().setAll(closeButton);
	}

	private Text createText(String name) {
		Text text = new Text(name);
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 24));
		return text;
	}

	public Popup getPopup() {
		return popup;
	}

	public Slider getMusicSlider() {
		return musicSlider.getSlider();
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
