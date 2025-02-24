package game;

import java.util.ArrayList;
import java.util.Iterator;

import game.item.Item;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameLogic;

public class Game extends StackPane {
	private static Game instance;

	public Game() {
		super();
		setAlignment(Pos.TOP_LEFT);

		VBox vBox = new VBox();
		vBox.setSpacing(16);
		Region region = new Region();
		VBox.setVgrow(region, Priority.ALWAYS);
		vBox.getChildren().setAll(GameTop.getInstance(), region, GameBottom.getInstance());

		getChildren().setAll(vBox);
	}

	public ArrayList<Item> getItemsInGame() {
		ArrayList<Item> items = new ArrayList<Item>();
		for (Node node : getChildren()) {
			if (node instanceof Item) {
				items.add((Item) node);
			}
		}
		return items;
	}

	public void clearFloatingItem() {
		Iterator<Node> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof Item && !GameLogic.getInstance().getInventory().contains((Item) node)) {
				iterator.remove();
			}
		}
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
