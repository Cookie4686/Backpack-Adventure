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
import logic.FightLogic;
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
			Item[] items = new Item[3];
			items[0]= ResourceLoader.newItem("Azurite Sword");
			items[1]= ResourceLoader.newItem("Cloth Armor");
			items[2]= ResourceLoader.newItem("Bread Loaf");
			Game.getInstance().addItemsToGame(items);
		}
		
        Fader.getBlackout().toFront();
        MenuScene.setInMenuScene(false);
        MenuScene.setGameStarted(true);
        if (FightLogic.getInstance().isInFight()) {
        	BackgroundSongPlayer.fight(GameLogic.getInstance().getCurrentFloor());
		}
        else {
        	BackgroundSongPlayer.floor(GameLogic.getInstance().getCurrentFloor());
        }
	}
}
