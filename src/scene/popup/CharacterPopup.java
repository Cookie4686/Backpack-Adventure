package scene.popup;

import application.Fader;
import component.GameButton;
import component.GameButtonType;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
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
		setAlignment(Pos.TOP_CENTER);
		setSpacing(20);
		getChildren().add(CharacterScene.use());

		popup.setCenter(this);

		GameButton journeyButton = new GameButton(159, 70, GameButtonType.JOURNEY_ON);
		journeyButton.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY) {				
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
			}
		});
		GameButton closeButton = new GameButton(159, 70, GameButtonType.CLOSE);
		closeButton.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY) {				
				SfxPlayer.play(Sfx.SELECT);
				popup.hide();
			}
		});

		popup.getBottomBox().getChildren().setAll(closeButton, journeyButton);
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
