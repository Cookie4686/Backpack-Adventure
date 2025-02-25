package scene.popup;

import application.Main;
import component.Button;
import component.ButtonSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scene.GameScene;

public class CharacterPopup {
	public static void show() {
		Popup popup = new Popup(Color.LIGHTGREEN);

		Text headingText = new Text("Choose Character");
		Button characterButton = new Button("Character 1", ButtonSize.LARGE);
		characterButton.setOnAction(_ -> GameScene.use());
		Button closeButton = new Button("Back", ButtonSize.LARGE);
		closeButton.setOnAction(_ -> Main.root.getChildren().remove(popup));

		popup.getChildren().setAll(headingText, characterButton, closeButton);
		Main.root.getChildren().add(popup);
	}
}
