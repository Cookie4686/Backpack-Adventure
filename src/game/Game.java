package game;

import game.backpack.Backpack;
import game.map.Map;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Game extends VBox {
	private static Game instance;

	private StackPane gamePane;
	private HBox TopHBox, bottomHBox;
	boolean isBackpack;

	public Game() {
		super();
		gamePane = new StackPane();
		gamePane.setAlignment(Pos.TOP_LEFT);
		setVgrow(gamePane, Priority.ALWAYS);

		VBox vBox = new VBox();
		TopHBox = new HBox();
		TopHBox.setAlignment(Pos.TOP_CENTER);

		bottomHBox = new HBox();
		vBox.getChildren().setAll(TopHBox, bottomHBox);

		gamePane.getChildren().setAll(vBox);
		getChildren().setAll(GameHeader.getInstance(), gamePane);
	}

	public StackPane getGamePane() {
		return gamePane;
	}

	public boolean isBackpack() {
		return isBackpack;
	}

	public void useBackpack() {
		isBackpack = true;
		TopHBox.getChildren().setAll(Backpack.getInstance());
	}

	public void useMap() {
		isBackpack = false;
		TopHBox.getChildren().setAll(Map.getInstance());
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public static double getX(Node node) {
		return node.localToParent(node.getBoundsInLocal()).getMinX();
	}

	public static double getY(Node node) {
		return node.localToParent(node.getBoundsInLocal()).getMinY();
	}
}
