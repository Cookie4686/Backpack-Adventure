package scene;

import application.Main;
import game.Game;
import game.util.ResourceLoader;

public class GameScene {
	public static void use() {
		Game.getInstance().useBackpack();
		Game.getInstance().getGamePane().getChildren().addAll(ResourceLoader.newItem("apple"),
				ResourceLoader.newItem("temp"), ResourceLoader.newItem("temp2"), ResourceLoader.newItem("temp3"),
				ResourceLoader.newItem("temp4"), ResourceLoader.newItem("temp5"), ResourceLoader.newItem("temp6"),
				ResourceLoader.newItem("temp7"), ResourceLoader.newItem("temp8"));
		Main.root.getChildren().setAll(Game.getInstance());
	}
}
