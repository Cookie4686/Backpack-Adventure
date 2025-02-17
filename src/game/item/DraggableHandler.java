package game.item;

import game.Game;
import game.backpack.Slot;
import game.backpack.SlotPane;
import javafx.scene.input.MouseEvent;

public class DraggableHandler {
	private double startX, startY;
	private int gridX, gridY;
	private Item item;

	public DraggableHandler(Item item) {
		this.item = item;
	}

	public void handleItemMousePress(MouseEvent event) {
		startX = event.getSceneX() - item.getTranslateX();
		startY = event.getSceneY() - item.getTranslateY();
	}

	public void handleItemMouseDrag(MouseEvent event) {
		setTranslateNoOffScreenX(event.getSceneX() - startX);
		setTranslateNoOffScreenY(event.getSceneY() - startY);
		SlotPane.getInstance().render();

		calcGrid();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			SlotPane.getInstance().getSlots()[gridY][gridX].highlight();
		}
	}

	public void handleItemMouseRelease(MouseEvent event) {
		calcGrid();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			setTranslateNoOffScreenX(Slot.SIZE * gridX + SlotPane.getInstance().getGameX());
			setTranslateNoOffScreenY(Slot.SIZE * gridY + SlotPane.getInstance().getGameY());
			SlotPane.getInstance().placeItem(item, gridX, gridY);
			SlotPane.getInstance().render();
		}
	}

	private void calcGrid() {
		gridX = (int) ((item.getTranslateX() + Slot.SIZE / 2) - SlotPane.getInstance().getGameX()) / Slot.SIZE;
		gridY = (int) ((item.getTranslateY() + Slot.SIZE / 2.5) - SlotPane.getInstance().getGameY()) / Slot.SIZE;
	}

	private void setTranslateNoOffScreenX(double val) {
		double maxWidth = Game.getGamePane().getWidth() - Slot.SIZE;
		item.setTranslateX(val < 0 ? 0 : (val > maxWidth ? maxWidth : val));
	}

	private void setTranslateNoOffScreenY(double val) {
		double maxHeight = Game.getGamePane().getHeight() - Slot.SIZE;
		item.setTranslateY(val < 0 ? 0 : (val > maxHeight ? maxHeight : val));
	}
}
