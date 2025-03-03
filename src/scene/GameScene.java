package scene;

import application.Fader;
import application.Main;
import game.Game;
import game.GameHeader;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import logic.GameLogic;
import sound.BackgroundSongPlayer;

public class GameScene {
	public static void use() {
		VBox root = new VBox();
		VBox.setVgrow(Game.getInstance(), Priority.ALWAYS);
		root.getChildren().setAll(GameHeader.getInstance(), Game.getInstance());
		Main.root.getChildren().setAll(root);
		if (!Main.root.getChildren().contains(Fader.getBlackout())) {
	        Main.root.getChildren().add(Fader.getBlackout());
	        Fader.getBlackout().toBack();
	    }
		Item[] items = new Item[9];
		for (int i=0 ; i<6 ; i++) {
			items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
		}
		items[6]= ResourceLoader.newItem("Mana Stone I");
		items[7]= ResourceLoader.newItem("Staff of Flame");
		items[8]= ResourceLoader.newItem("Blood  Drain Shield");
		Game.getInstance().addItemsToGame(items);
        Fader.getBlackout().toFront();
		BackgroundSongPlayer.floor(GameLogic.getInstance().getCurrentFloor());
	}
}
