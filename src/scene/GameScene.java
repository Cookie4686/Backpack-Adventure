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
		
		Item[] items = new Item[9];
		for (int i=0 ; i<6 ; i++) {
			items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
		}
		items[6]= ResourceLoader.newItem("Almighty ProgMeth");
		items[7]= ResourceLoader.newItem("Apple");
		items[8]= ResourceLoader.newItem("Armet");
		Game.getInstance().addItemsToGame(items);
	}
}
