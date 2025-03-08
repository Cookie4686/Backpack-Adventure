package game;

import java.util.ArrayList;
import java.util.Iterator;

import application.Main;
import game.backpack.Slot;
import game.item.Item;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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

		VBox root = new VBox();
		root.setSpacing(16);
		Region region = new Region();
		VBox.setVgrow(region, Priority.ALWAYS);
		root.getChildren().setAll(GameTop.getInstance(), region, GameBottom.getInstance());

		getChildren().setAll(root);

		// important for calculating height
		this.layout();
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

	public void addItemsToGame(Item... items) {
		Platform.runLater(() -> {
			getChildren().addAll(items);
			
		});
		for (Item item : items) {
			item.getFadeIn().play();
			item.moveUpAndDown();
		}
		final int row = 2, itemPerRow = items.length / row, spacingX = 8, spacingY = 4;
		double prevHeight = GameTop.getInstance().getHeight();
		for (int i = 0; i < row; i++) {
			double width = 0, height = 0;
			int limit = itemPerRow + (i + 1 == row ? items.length % row : 0);
			// calculate the total width
			for (int j = 0; j < limit; j++) {
				Item item = items[i * itemPerRow + j];
				width += item.getItemWidth() * Slot.getSize() + spacingX;
				height = Math.max(height, item.getItemHeight() * Slot.getSize());
			}
			double diffX = Main.root.getWidth() / 2 - width / 2;
			// place item
			double accumulator = 0;
			for (int j = 0; j < limit; j++) {
				Item item = items[i * itemPerRow + j];
				item.setTranslateX(diffX + accumulator - item.getDiffX());
				double diffY = height - item.getItemHeight() * Slot.getSize();
				item.setTranslateY(prevHeight + (diffY == 0 ? 0 : diffY / 2));
				accumulator += item.getItemWidth() * Slot.getSize() + spacingX;
			}
			prevHeight += height + spacingY;
		}
	}

	public void initializeFight() {
		GameTop.getInstance().useBackpack();
		Iterator<Node> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof Item) {
				if (GameLogic.getInstance().getInventory().contains((Item) node)) {
					((Item) node).getImageView().setCursor(Cursor.HAND);
				} else {
					iterator.remove();
				}
			}
		}
		for (Item item:GameLogic.getInstance().getInventory()) {
			item.setNewItem(false);
		}
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public static void setInstance(Game instance) {
		Game.instance = instance;
	}
}
