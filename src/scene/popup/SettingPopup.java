package scene.popup;

import application.Main;
import component.GameButton;
import component.GameButtonType;
import component.VolumeSlider;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.MenuScene;
import sound.Sfx;
import sound.SfxPlayer;

public class SettingPopup extends Popup {
	private static SettingPopup instance;
	private VolumeSlider musicSlider, sfxSlider;

	public SettingPopup() {
		super("Settings");
		GridPane root = new GridPane();
		root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(16);
		root.setVgap(16);

		GameButton menuButton = new GameButton(159, 70, GameButtonType.MENU);
		menuButton.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				SfxPlayer.play(Sfx.SELECT);
				hide();
				if (!MenuScene.isInMenuScene()) {
					Main.root.getChildren().clear();
					MenuScene.use();
				}
			}
		});
		root.add(createText("Music Volume"), 0, 0);
		root.add(createText("SFX Volume"), 0, 1);
		root.add(musicSlider = new VolumeSlider(24), 1, 0);
		root.add(sfxSlider = new VolumeSlider(24), 1, 1);
		root.add(menuButton, 0, 2, 2, 1);
		GridPane.setHalignment(menuButton, HPos.CENTER);
		setCenter(root);

		GameButton closeButton = new GameButton(159, 70, GameButtonType.CLOSE);
		closeButton.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				SfxPlayer.play(Sfx.SELECT);
				hide();
			}
		});
		bottomBox.getChildren().setAll(closeButton);
	}

	private Text createText(String name) {
		Text text = new Text(name);
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 24));
		return text;
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
