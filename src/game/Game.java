package game;

import java.util.Collections;
import java.util.HashSet;

import game.backpack.Backpack;
import game.item.Item;
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
	private boolean isBackpack;
	private HashSet<Item> itemsInGame;

	public Game() {
		super();
		itemsInGame = new HashSet<Item>();

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
		for (Item item : itemsInGame) {
			item.setVisible(true);
		}
		TopHBox.getChildren().setAll(Backpack.getInstance());
	}

	public void useMap() {
		isBackpack = false;
		for (Item item : itemsInGame) {
			item.setVisible(false);
		}
		TopHBox.getChildren().setAll(Map.getInstance());
	}

	public void addItem(Item... items) {
		Collections.addAll(itemsInGame, items);
		gamePane.getChildren().addAll(items);
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
