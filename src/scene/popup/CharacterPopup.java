package scene.popup;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scene.GameScene;

public class CharacterPopup {
	public static void show() {
		Popup popup = new Popup(Color.LIGHTGREEN);

		Text headingText = new Text("Choose Character");
		Button characterButton = new Button("Character 1");
		characterButton.setOnAction(event -> GameScene.use());
		Button closeButton = new Button("Back");
		closeButton.setOnAction(event -> Main.root.getChildren().remove(popup));

		popup.getChildren().setAll(headingText, characterButton, closeButton);
		Main.root.getChildren().add(popup);
	}
}
