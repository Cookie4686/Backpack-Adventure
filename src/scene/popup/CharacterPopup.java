package scene.popup;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import scene.GameScene;

public class CharacterPopup {
	public static void show() {
		VBox vBox = new VBox();
		vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		Text headingText = new Text("Choose Character");
		Button characterButton = new Button("Character 1");
		characterButton.setOnAction(event -> GameScene.useScene());
		Button closeButton = new Button("Back");
		closeButton.setOnAction(event -> Main.root.getChildren().remove(vBox));
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(headingText, characterButton, closeButton);
		Main.root.getChildren().add(vBox);
	}
}
