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
		setTranslateNoOffScreenX(event.getSceneX() - startX);
		setTranslateNoOffScreenY(event.getSceneY() - startY);
		hightlightGrid();
	}

	public void handleItemMouseRelease(MouseEvent event) {
		currentItem = null;
		placeItem();
	}

	private void hightlightGrid() {
		calcGrid();
		SlotPane.getInstance().render();
		SlotPane.getInstance().hightlight(gridX, gridY, currentItem);
	}

	private void placeItem() {
		calcGrid();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			double x = Slot.SIZE * gridX - item.getDiffX();
			double y = Slot.SIZE * gridY - item.getDiffY();
			if (item.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
				x -= item.getWidth() - Slot.SIZE;
			}
			setTranslateNoOffScreenX(x + Game.getX(SlotPane.getInstance()));
			setTranslateNoOffScreenY(y + Game.getY(SlotPane.getInstance()));
			SlotPane.getInstance().placeItem(gridX, gridY, item);
			SlotPane.getInstance().render();
		}
	}

	private void calcGrid() {
		double x = item.getTranslateX() + item.getDiffX() + Slot.SIZE / 2;
		double y = item.getTranslateY() + item.getDiffY() + Slot.SIZE / 2;
		if (item.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
			x += item.getWidth() - Slot.SIZE;
		}
		x -= Game.getX(SlotPane.getInstance());
		y -= Game.getY(SlotPane.getInstance());
		gridX = (int) (x < 0 ? -1 : x / Slot.SIZE);
		gridY = (int) (y < 0 ? -1 : y / Slot.SIZE);
	}

	public void setTranslateNoOffScreenX(double val) {
		double maxWidth = Game.getGamePane().getWidth() - item.getWidth();
		double diff = 0;
		if (item.getRotation() == ItemRotation.VERTICAL) {
			diff = item.getDiffX();
			maxWidth += diff;
		}
		item.setTranslateX(val < -diff ? -diff : (val > maxWidth ? maxWidth : val));
	}

	public void setTranslateNoOffScreenY(double val) {
		double maxHeight = Game.getGamePane().getHeight() - item.getHeight();
		double diff = 0;
		if (item.getRotation() == ItemRotation.HORIZONTAL) {
			diff = item.getDiffY();
			maxHeight += diff;
		}
		item.setTranslateY(val < -diff ? -diff : (val > maxHeight ? maxHeight : val));
	}

	public static void handleSceneKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.R) {
			if (currentItem != null) {
				currentItem.rotate(true);
				currentItem.handler.setTranslateNoOffScreenX(currentItem.getTranslateX());
				currentItem.handler.setTranslateNoOffScreenY(currentItem.getTranslateY());
				currentItem.handler.hightlightGrid();
			}
		}
	}
}
