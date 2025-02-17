package game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class Game {
	private static StackPane gamePane;

	public static StackPane getGamePane() {
		if (gamePane == null) {
			gamePane = new StackPane();
			gamePane.setAlignment(Pos.TOP_LEFT);
		}
		return gamePane;
	}
}
