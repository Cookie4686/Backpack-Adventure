package scene;

import application.Main;
import backpack.Backpack;
import backpack.item.Weapon;
import header.Header;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void useScene() {
		VBox root = new VBox();
		root.getChildren().addAll(Header.getInstance(), new Backpack());
		Main.root.getChildren().setAll(root, new Weapon("apple"));
	}
}
