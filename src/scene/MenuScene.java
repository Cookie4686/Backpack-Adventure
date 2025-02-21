package scene;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;

public class MenuScene {
	public static void use() {
		VBox root = new VBox();
		Text titleText = new Text("Cool game");

		VBox actionBox = new VBox();
		Button startButton = new Button("Start");
		startButton.setOnAction(event -> CharacterPopup.show());
		Button settingButton = new Button("Settings");
		settingButton.setOnAction(event -> SettingPopup.show());
		actionBox.setSpacing(20);
		actionBox.setAlignment(Pos.CENTER);
		actionBox.getChildren().addAll(startButton, settingButton);

		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(titleText, actionBox);

		Main.root.getChildren().setAll(root);
	}
}