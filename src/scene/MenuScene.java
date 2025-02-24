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
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);

		VBox actionBox = new VBox();
		actionBox.setSpacing(20);
		actionBox.setAlignment(Pos.CENTER);

		Text titleText = new Text("Cool game");
		Button startButton = new Button("Start");
		startButton.setOnAction(event -> CharacterPopup.show());
		Button settingButton = new Button("Settings");
		settingButton.setOnAction(event -> SettingPopup.show());
		actionBox.getChildren().addAll(startButton, settingButton);

		root.getChildren().addAll(titleText, actionBox);
		Main.root.getChildren().setAll(root);
	}
}