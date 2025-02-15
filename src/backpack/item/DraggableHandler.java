package backpack.item;

import application.Main;
import backpack.Slot;
import backpack.SlotPane;
import javafx.scene.input.MouseEvent;

public class DraggableHandler {
	private double startX, startY;
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

		int gridX = calcGridX();
		int gridY = calcGridY();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			SlotPane.getInstance().getSlots()[gridY][gridX].highlight();
		}
	}

	public void handleItemMouseRelease(MouseEvent event) {
		int gridX = calcGridX();
		int gridY = calcGridY();
		if (SlotPane.getInstance().isPlaceable(gridX, gridY, item)) {
			setTranslateNoOffScreenX(Slot.SIZE * gridX + SlotPane.getInstance().getSceneX());
			setTranslateNoOffScreenY(Slot.SIZE * gridY + SlotPane.getInstance().getSceneY());
			SlotPane.getInstance().placeItem(item, gridX, gridY);
			SlotPane.getInstance().render();
		}
	}

	private int calcGridX() {
		double diff = (item.getTranslateX() + Slot.SIZE / 2) - SlotPane.getInstance().getSceneX();
		return diff < 0 ? -1 : (int) diff / Slot.SIZE;
	}

	private int calcGridY() {
		double diff = (item.getTranslateY() + Slot.SIZE / 3) - SlotPane.getInstance().getSceneY();
		return diff < 0 ? -1 : (int) diff / Slot.SIZE;
	}

	private void setTranslateNoOffScreenX(double val) {
		double maxWidth = Main.root.getWidth() - Slot.SIZE;
		item.setTranslateX(val < 0 ? 0 : (val > maxWidth ? maxWidth : val));
	}

	private void setTranslateNoOffScreenY(double val) {
		double maxHeight = Main.root.getHeight() - Slot.SIZE;
		item.setTranslateY(val < 0 ? 0 : (val > maxHeight ? maxHeight : val));
	}
}
