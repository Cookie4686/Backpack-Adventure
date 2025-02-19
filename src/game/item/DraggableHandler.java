package game.item;

import game.Game;
import game.backpack.Slot;
import game.backpack.SlotPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class DraggableHandler {
	public static Item currentItem;

	private double startX, startY;
	private int gridX, gridY;
	private Item item;

	public DraggableHandler(Item item) {
		this.item = item;
	}

	public void handleItemMousePress(MouseEvent event) {
		currentItem = item;
		startX = event.getSceneX() - item.getTranslateX();
		startY = event.getSceneY() - item.getTranslateY();
	}

	public void handleItemMouseDrag(MouseEvent event) {
		setTranslateNoOffScreenX(event.getSceneX() - startX, item);
		setTranslateNoOffScreenY(event.getSceneY() - startY, item);
		SlotPane.getInstance().render();

		calcGrid();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			SlotPane.getInstance().getSlots()[gridY][gridX].highlight();
		}
	}

	public void handleItemMouseRelease(MouseEvent event) {
		currentItem = null;
		calcGrid();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			setTranslateNoOffScreenX(Slot.SIZE * gridX + Game.getX(SlotPane.getInstance()), item);
			setTranslateNoOffScreenY(Slot.SIZE * gridY + Game.getY(SlotPane.getInstance()), item);
			SlotPane.getInstance().placeItem(item, gridX, gridY);
			SlotPane.getInstance().render();
		}
	}

	public static void handleSceneKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.R) {
			if (currentItem != null) {
				currentItem.rotate(true);
				DraggableHandler.setTranslateNoOffScreenX(currentItem.getTranslateX(), currentItem);
				DraggableHandler.setTranslateNoOffScreenY(currentItem.getTranslateY(), currentItem);
			}
		}
	}

	private void calcGrid() {
		gridX = (int) (item.getTranslateX() - Game.getX(SlotPane.getInstance())) / Slot.SIZE;
		gridY = (int) (item.getTranslateY() - Game.getY(SlotPane.getInstance())) / Slot.SIZE;
	}

	public static void setTranslateNoOffScreenX(double val, Item item) {
		double maxWidth = Game.getGamePane().getWidth() - item.getWidth();
		double diff = 0;
		if (item.getRotation() == ItemRotation.VERTICAL) {
			diff = item.getDiff();
			maxWidth += diff;
		}
		item.setTranslateX(val < -diff ? -diff : (val > maxWidth ? maxWidth : val));
	}

	public static void setTranslateNoOffScreenY(double val, Item item) {
		double maxHeight = Game.getGamePane().getHeight() - item.getHeight();
		double diff = 0;
		if (item.getRotation() == ItemRotation.HORIZONTAL) {
			diff = item.getDiff();
			maxHeight += diff;
		}
		item.setTranslateY(val < -diff ? -diff : (val > maxHeight ? maxHeight : val));
	}
}
