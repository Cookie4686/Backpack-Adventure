package scene;

import application.Main;
import game.Game;
import game.GameHeader;
import game.GameLogic;
import game.GameTop;
import game.util.ResourceLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void use() {
		GameLogic.initialize();

		VBox root = new VBox();
		VBox.setVgrow(Game.getInstance(), Priority.ALWAYS);
		root.getChildren().setAll(GameHeader.getInstance(), Game.getInstance());
		Main.root.getChildren().setAll(root);

		GameTop.getInstance().useBackpack();
		Game.getInstance().addItem(ResourceLoader.newItem("apple"), ResourceLoader.newItem("temp"),
				ResourceLoader.newItem("temp2"), ResourceLoader.newItem("temp3"), ResourceLoader.newItem("temp4"),
				ResourceLoader.newItem("temp5"), ResourceLoader.newItem("temp6"), ResourceLoader.newItem("temp7"),
				ResourceLoader.newItem("temp8"));
	}
}
