package scene.popup;


import application.Main;
import component.VolumeSlider;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.MenuScene;
import sound.BackgroundSongPlayer;
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
		add(createText("Music Volume"), 0, 0);
		add(createText("SFX Volume"), 0, 1);
		add(musicSlider = new VolumeSlider(24, false, () -> BackgroundSongPlayer.pause(),
				() -> BackgroundSongPlayer.play()), 1, 0);
		add(sfxSlider = new VolumeSlider(24), 1, 1);

		ImageView menuButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/menuButton1.png").toString()));
		menuButton.setFitHeight(70);
		menuButton.setFitWidth(159);
		
		StackPane menuButtonPane = new StackPane(menuButton);
		menuButtonPane.setMaxWidth(159);
		menuButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			menuButton.setImage(new Image(ClassLoader.getSystemResource("picture/menuButton2.png").toString()));
		});
		menuButtonPane.setOnMouseExited(_ -> menuButton.setImage(new Image(ClassLoader.getSystemResource("picture/menuButton1.png").toString())));
		menuButtonPane.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
			if (!MenuScene.isInMenuScene()) {
				Main.root.getChildren().clear();
				MenuScene.use();
			}
		});
		
		ImageView closeButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/closeButton1.png").toString()));
		closeButton.setFitHeight(70);
		closeButton.setFitWidth(159);
		
		StackPane closeButtonPane = new StackPane(closeButton);
		closeButtonPane.setMaxWidth(159);
		closeButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			closeButton.setImage(new Image(ClassLoader.getSystemResource("picture/closeButton2.png").toString()));
		});
		closeButtonPane.setOnMouseExited(_ -> closeButton.setImage(new Image(ClassLoader.getSystemResource("picture/closeButton1.png").toString())));
		closeButtonPane.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
		});
		
		add(menuButtonPane, 1 ,3);
		
		popup.setCenter(this);
		
		popup.getBottomBox().getChildren().setAll(closeButtonPane);
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
