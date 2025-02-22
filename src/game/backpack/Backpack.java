package game.backpack;

import java.util.ArrayList;

import game.handler.ItemHandler;
import game.item.Item;
import game.util.ItemPostion;
import game.util.ItemRotation;
import interfaces.ReRenderable;
import javafx.scene.layout.GridPane;
import logic.GameLogic;

public class Backpack extends GridPane implements ReRenderable {
	private static Backpack instance;
	private static final int WIDTH = 7, HEIGHT = 5;
	private Slot[][] slots;

	public Backpack() {
		super();
		slots = new Slot[HEIGHT][WIDTH];
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				slots[y][x] = new Slot();
				this.add(slots[y][x], x, y);
			}
		}
		render();
	}

	@Override
	public void render() {
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				slot.render();
			}
		}
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
			ItemHandler.setRandomOffGridLocation(slot.getItem());
			removeItem(slot.getItem());
		}
		slot.setItem(item);
	}

	private void removeItem(Item item) {
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

	public void replaceItem(Item item) {
		removeItem(item);
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