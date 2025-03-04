package scene.popup;

import application.Fader;
import component.Button;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import scene.CharacterScene;
import scene.GameScene;
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

		Button characterButton = new Button("JOURNEY ON!", 128, 32);
		characterButton.setOnAction(_ -> {
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				GameScene.use();
				SfxPlayer.play(Sfx.GAMESTART);
			});
			pause.play();
		});
		Button closeButton = new Button("Back", 128, 32);
		closeButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
		});

		popup.getBottomBox().getChildren().setAll(characterButton, closeButton);
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
