package game.backpack;

import java.util.ArrayList;

import game.Game;
import game.item.Item;
import game.item.consumable.Potion;
import game.util.ItemPostion;
import game.util.ItemRotation;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import logic.FightLogic;
import logic.GameLogic;
import logic.handler.ButtonHandler;
import logic.handler.ItemHandler;

public class Backpack extends BorderPane implements ReRenderable {
	private static Backpack instance;
	private static final int WIDTH = 7, HEIGHT = 5;
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
		setCenter(gridPane);
		endTurnButton = new Button("End Turn");
		endTurnButton.setOnAction(event -> ButtonHandler.handleEndTurnButtonOnAction());
		setBottom(endTurnButton);
		setAlignment(endTurnButton, Pos.CENTER);
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
		}
		render();
		return isPlaceable;
	}

	public void replaceItem(Item oldItem, Item newItem) {
		newItem.setTranslateX(oldItem.getTranslateX());
		newItem.setTranslateY(oldItem.getTranslateY());
		newItem.setRotate(oldItem.getRotate());
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				if (slot.getItem() == oldItem) {
					slot.setItem(newItem);
				}
			}
		}
		GameLogic.getInstance().getInventory().remove(oldItem);
		GameLogic.getInstance().getInventory().add(newItem);
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

	public ArrayList<ItemPostion> getItemPostion(Item item) {
		ArrayList<ItemPostion> itemPostions = new ArrayList<ItemPostion>();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (slots[y][x].getItem() == item)
					itemPostions.add(new ItemPostion(x, y));
			}
		}
		return itemPostions;
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