package scene.popup;

import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import scene.GameScene;

public class CharacterPopup extends VBox {
	private static CharacterPopup instance;
	private Popup popup;

	public CharacterPopup() {
		super();
		popup = new Popup("Character Select");

		setAlignment(Pos.CENTER);
		setSpacing(20);
		Button characterButton = new Button("Character 1", 128, 32);
		characterButton.setOnAction(_ -> GameScene.use());
		getChildren().setAll(characterButton);
		popup.setCenter(this);

		Button closeButton = new Button("Back", 128, 32);
		closeButton.setOnAction(_ -> popup.hide());
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
