package scene;

import javafx.scene.layout.StackPane;

public class Game {
	private static StackPane gamePane;

	public static StackPane getGamePane() {
		if (gamePane == null) {
			gamePane = new StackPane();
		}
		return gamePane;
	}
}
