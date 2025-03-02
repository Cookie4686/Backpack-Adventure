package scene;

import application.Main;
import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scene.popup.CharacterPopup;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MenuScene {
	public static void use() {
		BackgroundSongPlayer.menu();
		VBox root = new VBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);

		VBox actionBox = new VBox();
		actionBox.setSpacing(20);
		actionBox.setAlignment(Pos.CENTER);

		Text titleText = new Text("Cool Game");
		titleText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 64));
		Button startButton = new Button("Start", 128, 32);
		Button settingButton = new Button("Settings", 128, 32);
		startButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			CharacterPopup.getInstance().getPopup().show();
		});
		settingButton.setOnAction(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			SettingPopup.getInstance().getPopup().show();
		});
		actionBox.getChildren().addAll(startButton, settingButton);

		root.getChildren().addAll(titleText, actionBox);
		Main.root.getChildren().setAll(root);
	}
}