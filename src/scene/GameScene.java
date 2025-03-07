package scene;

import application.Fader;
import application.Main;
import game.Game;
import game.GameHeader;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import logic.GameLogic;
import sound.BackgroundSongPlayer;

public class GameScene {
	public static void use() {
		VBox root = new VBox();
		VBox.setVgrow(Game.getInstance(), Priority.ALWAYS);
		root.getChildren().setAll(GameHeader.getInstance(), Game.getInstance());
		
		ImageView biome;
		if(GameLogic.getInstance().getCurrentFloor() == 0) {
			biome = new ImageView(new Image(ClassLoader.getSystemResource("theme/biome1.png").toString()));
		} else if (GameLogic.getInstance().getCurrentFloor() == 1) {
			biome = new ImageView(new Image(ClassLoader.getSystemResource("theme/biome2.png").toString()));
		} else {
			biome = new ImageView(new Image(ClassLoader.getSystemResource("theme/biome3.png").toString()));
		}
		
		Main.root.getChildren().setAll(biome, root);
		if (!Main.root.getChildren().contains(Fader.getBlackout())) {
	        Main.root.getChildren().add(Fader.getBlackout());
	        Fader.getBlackout().toBack();
	    }
		

		if (!MenuScene.hasGameStarted() && GameLogic.getInstance().getCurrentFloor() == 0) {
			Item[] items = new Item[9];
			for (int i=0 ; i<6 ; i++) {
				items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
			}
			items[6]= ResourceLoader.newItem("Damage Relic II");
			items[7]= ResourceLoader.newItem("Well Made Shield");
			items[8]= ResourceLoader.newItem("Excalibur");
			Game.getInstance().addItemsToGame(items);
		}
		
        Fader.getBlackout().toFront();
        MenuScene.setInMenuScene(false);
        MenuScene.setGameStarted(true);
		BackgroundSongPlayer.floor(GameLogic.getInstance().getCurrentFloor());
	}
}
