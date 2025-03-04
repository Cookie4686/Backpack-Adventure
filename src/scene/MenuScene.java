package scene;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MenuScene {
	private static Media videomedia = new Media(ClassLoader.getSystemResource(String.format("theme/menuBackground.mp4")).toString());
	private static boolean isGameRunning;
	private static boolean isInMenuScene;
	
	public static void use() {
		BackgroundSongPlayer.menu();
		isInMenuScene=true;
		System.out.println(MenuScene.isInMenuScene());
		
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
		

		Text titleText = new Text("Cool Game");
		titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 64));
		
		ImageView newButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/newGameButton1.png").toString()));
		newButton.setFitHeight(90);
		newButton.setFitWidth(204);
		
		StackPane newButtonPane = new StackPane(newButton);
		newButtonPane.setMaxWidth(204);
		newButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			newButton.setImage(new Image(ClassLoader.getSystemResource("picture/newGameButton2.png").toString()));
		});
		newButtonPane.setOnMouseExited(_ -> newButton.setImage(new Image(ClassLoader.getSystemResource("picture/newGameButton1.png").toString())));
		newButtonPane.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			CharacterPopup.getInstance().getPopup().show();
		});
		
		ImageView settingButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/SettingButton1.png").toString()));
		settingButton.setFitHeight(90);
		settingButton.setFitWidth(204);
		
		StackPane settingButtonPane = new StackPane(settingButton);
		settingButtonPane.setMaxWidth(204);
		settingButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			settingButton.setImage(new Image(ClassLoader.getSystemResource("picture/SettingButton2.png").toString()));
		});
		settingButtonPane.setOnMouseExited(_ -> settingButton.setImage(new Image(ClassLoader.getSystemResource("picture/SettingButton1.png").toString())));
		settingButtonPane.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			SettingPopup.getInstance().getPopup().show();
		});
		
		actionBox.getChildren().addAll(newButtonPane, settingButtonPane);
		
		root.getChildren().addAll(titleText, actionBox);
		Main.root.getChildren().addAll(viewmedia, root);
	}

	public static boolean isGameRunning() {
		return isGameRunning;
	}

	public static void setGameRunning(boolean isGameRunning) {
		MenuScene.isGameRunning = isGameRunning;
	}

	public static boolean isInMenuScene() {
		return isInMenuScene;
	}

	public static void setInMenuScene(boolean isInMenuScene) {
		MenuScene.isInMenuScene = isInMenuScene;
	}
}