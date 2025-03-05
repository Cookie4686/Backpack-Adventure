package scene;

import application.Main;
import component.GameButton;
import component.GameButtonType;
import image.GifPlayer;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MenuScene {
	private static boolean isGameRunning = false;
	private static boolean isInMenuScene = false;

	public static void use() {
		BackgroundSongPlayer.menu();
		isInMenuScene = true;

		VBox root = new VBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);

		Text titleText = new Text("Cool Game");
		titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 64));
		VBox buttonBox = new VBox();
		buttonBox.setSpacing(20);
		buttonBox.setAlignment(Pos.CENTER);

		GameButton continueButton = new GameButton(170, 70, GameButtonType.CONTINUE);
		continueButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
//			TODO: go to last game
		});
		GameButton newButton = new GameButton(170, 70, GameButtonType.NEW_GAME);
		newButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			CharacterPopup.getInstance().getPopup().show();
		});

		GameButton settingButton = new GameButton(170, 70, GameButtonType.SETTING);
		settingButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			SettingPopup.getInstance().getPopup().show();
		});

		GameButton exitButton = new GameButton(170, 70, GameButtonType.EXIT);
		exitButton.setOnMouseClicked(_ -> {
			Platform.exit();
		});
		
		if (isGameRunning) {
			buttonBox.getChildren().add(continueButton);
		}
		buttonBox.getChildren().addAll(newButton, settingButton, exitButton);

		root.getChildren().addAll(titleText, buttonBox);

		ImageView menuBackground = new ImageView();
		menuBackground.setPreserveRatio(true);
		menuBackground.setFitWidth(1280);
		Timeline menuTimeline = GifPlayer.createAnimation(menuBackground, GifPlayer.getMenuBackground(), 0.1);
		menuTimeline.setCycleCount(Timeline.INDEFINITE);
		menuTimeline.play();

		Main.root.getChildren().addAll(menuBackground, root);
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