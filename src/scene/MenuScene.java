package scene;

import application.Main;
import image.GifPlayer;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MenuScene {
	private static boolean isGameRunning;
	private static boolean isInMenuScene;
	
	public static void use() {
		BackgroundSongPlayer.menu();
		isInMenuScene=true;
		
		
		VBox root = new VBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);

		VBox actionBox = new VBox();
		actionBox.setSpacing(20);
		actionBox.setAlignment(Pos.CENTER);
		
		
		
		Text titleText = new Text("Cool Game");
		titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 64));
		
		ImageView continueButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/continueGameButton1.png").toString()));
		continueButton.setFitHeight(90);
		continueButton.setFitWidth(204);
		
		StackPane continueButtonPane = new StackPane(continueButton);
		continueButtonPane.setMaxWidth(204);
		continueButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			continueButton.setImage(new Image(ClassLoader.getSystemResource("picture/continueGameButton2.png").toString()));
		});
		continueButtonPane.setOnMouseExited(_ -> continueButton.setImage(new Image(ClassLoader.getSystemResource("picture/continueGameButton1.png").toString())));
		continueButtonPane.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
//			TODO: go to last game
		});
		
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
		
		ImageView menuBackground = new ImageView();
		menuBackground.setPreserveRatio(true);
		menuBackground.setFitWidth(1280);
		Timeline menuTimeline = GifPlayer.createAnimation(menuBackground, GifPlayer.getMenuBackground(), 0.1);
		menuTimeline.setCycleCount(Timeline.INDEFINITE);
		menuTimeline.play();
		
		if (isGameRunning) actionBox.getChildren().add(continueButtonPane);
		actionBox.getChildren().addAll(newButtonPane, settingButtonPane);
		
		root.getChildren().addAll(titleText, actionBox);
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