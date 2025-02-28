package scene;

import application.Main;
import game.Game;
import game.GameHeader;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void use() {
		VBox root = new VBox();
		VBox.setVgrow(Game.getInstance(), Priority.ALWAYS);
		root.getChildren().setAll(GameHeader.getInstance(), Game.getInstance());
		Main.root.getChildren().setAll(root);
		
		Item[] items = new Item[6];
		for (int i=0 ; i<6 ; i++) {
			items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
		}
		Game.getInstance().addItemsToGame(items);
	}
}
