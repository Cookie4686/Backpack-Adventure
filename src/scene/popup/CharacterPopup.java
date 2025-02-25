package scene.popup;

import application.Main;
import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import scene.GameScene;

public class CharacterPopup {
	public static void show() {
		Popup popup = new Popup("Character Select");

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		Button characterButton = new Button("Character 1", 128, 32);
		characterButton.setOnAction(_ -> GameScene.use());
		vBox.getChildren().setAll(characterButton);
		popup.setCenter(vBox);

		Button closeButton = new Button("Back", 128, 32);
		closeButton.setOnAction(_ -> Main.root.getChildren().remove(popup));
		popup.getBottomBox().getChildren().setAll(closeButton);

		Main.root.getChildren().add(popup);
	}
}
