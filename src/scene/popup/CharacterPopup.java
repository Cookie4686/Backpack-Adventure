package scene.popup;

import application.Fader;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import scene.CharacterScene;
import scene.GameScene;
import scene.MenuScene;
import sound.Sfx;
import sound.SfxPlayer;

public class CharacterPopup extends VBox {
	private static CharacterPopup instance;
	private Popup popup;

	public CharacterPopup() {
		super();
		popup = new Popup("Character Select");
		
		setAlignment(Pos.CENTER);
		setSpacing(20);
		
		getChildren().add(CharacterScene.use());
		popup.setCenter(this);
		
		ImageView journeyButton = new ImageView(new Image(ClassLoader.getSystemResource("picture/journeyButton1.png").toString()));
		journeyButton.setFitHeight(70);
		journeyButton.setFitWidth(159);
		
		StackPane journeyButtonPane = new StackPane(journeyButton);
		journeyButtonPane.setMaxWidth(159);
		journeyButtonPane.setOnMouseEntered(_ -> {
			SfxPlayer.play(Sfx.CLICK);
			journeyButton.setImage(new Image(ClassLoader.getSystemResource("picture/journeyButton2.png").toString()));
		});
		journeyButtonPane.setOnMouseExited(_ -> journeyButton.setImage(new Image(ClassLoader.getSystemResource("picture/journeyButton1.png").toString())));
		journeyButtonPane.setOnMouseClicked(_ -> {
			MenuScene.setGameRunning(true);
			MenuScene.setInMenuScene(false);
			SfxPlayer.play(Sfx.GAMESTART);
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {				
				GameScene.use();
			});
//			TODO: Create new game after already played
			pause.play();
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
		
		popup.setCenter(this);
		
		popup.getBottomBox().getChildren().setAll(closeButtonPane, journeyButtonPane);
		popup.getBottomBox().setSpacing(30);
	}

	public Popup getPopup() {
		return popup;
	}

	public static CharacterPopup getInstance() {
		if (instance == null) {
			instance = new CharacterPopup();
		}
		return instance;
	}
}
