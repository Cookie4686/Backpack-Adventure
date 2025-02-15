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

public class SettingPopup {
	public static void show() {
		VBox vBox = new VBox();
		vBox.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
		Text headingText = new Text("Settings");
		Button closeButton = new Button("Close");
		closeButton.setOnAction(event -> Main.root.getChildren().remove(vBox));
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(headingText, closeButton);
		Main.root.getChildren().add(vBox);
	}
}
