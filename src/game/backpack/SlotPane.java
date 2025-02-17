package game.backpack;

import game.item.Item;
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

	public void placeItem(Item item, int gridX, int gridY) {
		if (item.getRotation() == 0 || item.getRotation() == 4) {
			System.out.println("vertical");
			System.out.println("vertical");
		}
	}

	public boolean isPlaceable(int gridX, int gridY, Item item) {
		return 0 <= gridX && gridX < WIDTH && 0 <= gridY && gridY < HEIGHT;
	}

	public double getGameX() {
		return localToParent(getBoundsInLocal()).getMinX();
	}

	public double getGameY() {
		return localToParent(getBoundsInLocal()).getMinY();
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
