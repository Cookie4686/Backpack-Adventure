package scene;

import application.Main;
import backpack.Backpack;
import backpack.item.Weapon;
import header.Header;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void useScene() {
		VBox root = new VBox();
		Game.getGamePane().getChildren().addAll(new Backpack(), new Weapon("apple"));
		VBox.setVgrow(Game.getGamePane(), Priority.ALWAYS);
		root.getChildren().addAll(Header.getInstance(), Game.getGamePane());
		Main.root.getChildren().setAll(root);
	}
}
