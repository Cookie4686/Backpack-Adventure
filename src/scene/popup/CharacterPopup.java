package scene.popup;

import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
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
		Button characterButton = new Button("Character 1", 128, 32);
		characterButton.setOnAction(_ -> {
			GameScene.use();
			SfxPlayer.play(Sfx.GAMESTART);
		});
		getChildren().setAll(characterButton);
		popup.setCenter(this);
		Button closeButton = new Button("Back", 128, 32);
		closeButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			popup.hide();
		});
		popup.getBottomBox().getChildren().setAll(closeButton);
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
