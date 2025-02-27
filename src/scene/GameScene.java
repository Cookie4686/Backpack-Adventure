package scene;

import application.Main;
import game.Game;
import game.GameHeader;
import game.item.ResourceLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void use() {
		VBox root = new VBox();
		VBox.setVgrow(Game.getInstance(), Priority.ALWAYS);
		root.getChildren().setAll(GameHeader.getInstance(), Game.getInstance());
		Main.root.getChildren().setAll(root);

		Game.getInstance().addItemsToGame(ResourceLoader.newItem("apple"), 
				ResourceLoader.newItem("sword"), ResourceLoader.newItem("apple"));
	}
}
