package game;

import java.util.ArrayList;
import java.util.Collections;

import game.item.Item;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Game extends VBox {
	private static Game instance;
	private StackPane gamePane;

	private ArrayList<Item> itemsInGame;

	public Game() {
		super();
		itemsInGame = new ArrayList<Item>();

		gamePane = new StackPane();
		gamePane.setAlignment(Pos.TOP_LEFT);
		setVgrow(gamePane, Priority.ALWAYS);

		VBox vBox = new VBox();
		vBox.setSpacing(16);
		setVgrow(GameBottom.getInstance(), Priority.ALWAYS);
		vBox.getChildren().setAll(GameTop.getInstance(), GameBottom.getInstance());

		gamePane.getChildren().setAll(vBox);
		getChildren().setAll(GameHeader.getInstance(), gamePane);
	}

	public StackPane getGamePane() {
		return gamePane;
	}

	public void addItem(Item... items) {
		Collections.addAll(itemsInGame, items);
		gamePane.getChildren().addAll(items);
	}

	public ArrayList<Item> getItemsInGame() {
		return itemsInGame;
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
