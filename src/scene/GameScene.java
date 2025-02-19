package scene;

import application.Main;
import game.Game;
import game.backpack.Backpack;
import game.header.Header;
import game.item.ResourceLoader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameScene {
	public static void use() {
		VBox root = new VBox();
		Game.getGamePane().getChildren().addAll(Backpack.getInstance(), ResourceLoader.newItem("apple"),
				ResourceLoader.newItem("temp"), ResourceLoader.newItem("temp2"), ResourceLoader.newItem("temp3"),
				ResourceLoader.newItem("temp4"), ResourceLoader.newItem("temp5"), ResourceLoader.newItem("temp6"),
				ResourceLoader.newItem("temp7"), ResourceLoader.newItem("temp8"));
		VBox.setVgrow(Game.getGamePane(), Priority.ALWAYS);
		root.getChildren().addAll(Header.getInstance(), Game.getGamePane());
		Main.root.getChildren().setAll(root);
	}
}
