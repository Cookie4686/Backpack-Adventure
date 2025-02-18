package scene.popup;

import application.Main;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scene.GameScene;

public class CharacterPopup {
	public static void show() {
		Text headingText = new Text("Choose Character");
		Button characterButton = new Button("Character 1");
		Button closeButton = new Button("Back");
		Popup popup = new Popup(Color.LIGHTGREEN, headingText, characterButton, closeButton);
		characterButton.setOnAction(event -> GameScene.use());
		closeButton.setOnAction(event -> Main.root.getChildren().remove(popup));
		Main.root.getChildren().add(popup);
	}
}
