package game.backpack;

import java.util.ArrayList;

import component.Button;
import game.Game;
import game.item.Item;
import game.item.consumable.Potion;
import game.util.ItemPosition;
import game.util.ItemRotation;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.FightLogic;
import logic.GameLogic;
import logic.handler.ButtonHandler;
import logic.handler.ItemHandler;

public class Backpack extends VBox implements ReRenderable {
	private static Backpack instance;
	public static final int WIDTH = 7, HEIGHT = 5;
	private Slot[][] slots;

	private GridPane gridPane;
	private Button endTurnButton;

	public Backpack() {
		super();
		slots = new Slot[HEIGHT][WIDTH];
		gridPane = new GridPane();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				gridPane.add(slots[y][x] = new Slot(), x, y);
			}
		}
		endTurnButton = new Button("End Turn", 64, 16);
		endTurnButton.setOnAction(_ -> ButtonHandler.handleEndTurnButtonOnAction());
		setAlignment(Pos.CENTER);
		getChildren().setAll(gridPane, endTurnButton);
		setSpacing(8);
		render();
	}

	@Override
	public void render() {
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				slot.render();
			}
		}
		endTurnButton.setVisible(FightLogic.getInstance().isInFight());
	}

	public boolean isPlaceable(int gridX, int gridY, Item item) {
		try {
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					if (!slots[gridY + i][gridX + (isLeft ? i : -i)].isUnlocked())
						return false;
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						if (!slots[y][x].isUnlocked())
							return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean placeItem(int gridX, int gridY, Item item) {
		removeItem(item);
		boolean isPlaceable = isPlaceable(gridX, gridY, item);
		if (isPlaceable) {
			GameLogic.getInstance().getInventory().add(item);
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					placeItem(slots[gridY + i][gridX + (isLeft ? i : -i)], item);
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						placeItem(slots[y][x], item);
					}
				}
			}
		} else {
			item.moveUpAndDown();
		}
		render();
		return isPlaceable;
	}

	public void replaceItem(Item oldItem, Item newItem) {
		newItem.setTranslateX(oldItem.getTranslateX());
		newItem.setTranslateY(oldItem.getTranslateY());
		newItem.getImageView().setRotate(oldItem.getImageView().getRotate());
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				if (slot.getItem() == oldItem) {
					slot.setItem(newItem);
				}
			}
		}
		GameLogic.getInstance().getInventory().remove(oldItem);
		Game.getInstance().getChildren().remove(oldItem);
		GameLogic.getInstance().getInventory().add(newItem);
		Game.getInstance().getChildren().add(newItem);
	}

	private void placeItem(Slot slot, Item item) {
		if (slot.getItem() != null) {
			if (slot.getItem() instanceof Potion) {
				if (((Potion) item).isStackable(slot.getItem())) {
					((Potion) item)
							.setDurability(((Potion) item).getDurability() + ((Potion) slot.getItem()).getDurability());

					Game.getInstance().getChildren().remove(slot.getItem());
				}
			}

			ItemHandler.setRandomOffGridLocation(slot.getItem());
			removeItem(slot.getItem());
		}
		slot.setItem(item);
	}

	public void removeItem(Item item) {
		GameLogic.getInstance().getInventory().remove(item);
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				if (slot.getItem() == item) {
					slot.setItem(null);
				}
			}
		}
	}

	public void hightlight(int gridX, int gridY, Item item) {
		if (isPlaceable(gridX, gridY, item)) {
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					slots[gridY + i][gridX + (isLeft ? i : -i)].highlight();
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						slots[y][x].highlight();
					}
				}
			}
		}
	}

	public ArrayList<ItemPosition> getItemPosition(Item item) {
		ArrayList<ItemPosition> itemPositions = new ArrayList<ItemPosition>();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (slots[y][x].getItem() == item)
					itemPositions.add(new ItemPosition(x, y));
			}
		}
		return itemPositions;
	}

	public Slot[][] getSlots() {
		return slots;
	}

	public static Backpack getInstance() {
		if (instance == null) {
			instance = new Backpack();
		}
		return instance;
	}
}