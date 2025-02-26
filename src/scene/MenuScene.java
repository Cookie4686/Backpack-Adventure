package scene;

import application.Main;
import component.Button;
import javafx.geometry.Pos;
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
		Button startButton = new Button("Start", 128, 32);
		Button settingButton = new Button("Settings", 128, 32);
		startButton.setOnAction(_ -> CharacterPopup.getInstance().getPopup().show());
		settingButton.setOnAction(_ -> SettingPopup.getInstance().getPopup().show());
		actionBox.getChildren().addAll(startButton, settingButton);

		root.getChildren().addAll(titleText, actionBox);
		Main.root.getChildren().setAll(root);
	}
}