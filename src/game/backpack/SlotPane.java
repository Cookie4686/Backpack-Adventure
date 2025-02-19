package game.backpack;

import game.item.Item;
import game.item.ItemRotation;
import interfaces.ReRenderable;
import javafx.scene.layout.GridPane;

public class SlotPane extends GridPane implements ReRenderable {
	private static SlotPane instance;
	public static final int WIDTH = 7, HEIGHT = 5;

	private Slot[][] slots;

	public SlotPane() {
		super();
		slots = new Slot[HEIGHT][WIDTH];
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				slots[y][x] = new Slot(x, y);
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

	// might need to modify later to account for locked slots
	public boolean isPlaceable(int gridX, int gridY, Item item) {
		if (item.isDiagonal()) {
			return (item.getRotation() == ItemRotation.DIAGONAL_LEFT
					? 0 <= gridX && gridX + item.getItemHeight() <= WIDTH
					: -1 <= gridX - item.getItemHeight() && gridX < WIDTH)
					&& (0 <= gridY && gridY + item.getItemHeight() <= HEIGHT);
		}
		return 0 <= gridX && gridX + item.getItemWidth() <= WIDTH && 0 <= gridY
				&& gridY + item.getItemHeight() <= HEIGHT;
	}

	// might need to modify later to account for already placed slots
	public void placeItem(int gridX, int gridY, Item item) {
		if (isPlaceable(gridX, gridY, item)) {
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					slots[gridY + i][gridX + (isLeft ? i : -i)].setItem(item);
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						slots[y][x].setItem(item);
					}
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

	public Slot[][] getSlots() {
		return slots;
	}

	public static SlotPane getInstance() {
		if (instance == null) {
			instance = new SlotPane();
		}
		return instance;
	}
}
