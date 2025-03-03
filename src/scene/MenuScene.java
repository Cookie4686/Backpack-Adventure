package scene;

import application.Main;
import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MenuScene {
	private static Media videomedia = new Media(ClassLoader.getSystemResource(String.format("theme/menuBackground.mp4")).toString());
	
	public static void use() {
		BackgroundSongPlayer.menu();
		VBox root = new VBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);

		VBox actionBox = new VBox();
		actionBox.setSpacing(20);
		actionBox.setAlignment(Pos.CENTER);
		
		MediaPlayer mdplayer = new MediaPlayer(videomedia);
		MediaView viewmedia = new MediaView(mdplayer);
		mdplayer.setMute(true);
		mdplayer.setRate(1.2);
		mdplayer.setCycleCount(MediaPlayer.INDEFINITE);
		viewmedia.setFitHeight(720);
		mdplayer.play();
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Text titleText = new Text("Cool Game");
		titleText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 64));
		Button startButton = new Button("Start", 128, 32);
		Button settingButton = new Button("Settings", 128, 32);
		startButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			CharacterPopup.getInstance().getPopup().show();
		});
		settingButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			SettingPopup.getInstance().getPopup().show();
		});
		actionBox.getChildren().addAll(startButton, settingButton);
		
		root.getChildren().addAll(titleText, actionBox);
		Main.root.getChildren().addAll(viewmedia, root);
	}
}