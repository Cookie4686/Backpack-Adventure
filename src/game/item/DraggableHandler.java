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
			setTranslateNoOffScreenX(Slot.SIZE * gridX + Game.getX(SlotPane.getInstance()));
			setTranslateNoOffScreenY(Slot.SIZE * gridY + Game.getY(SlotPane.getInstance()));
			SlotPane.getInstance().placeItem(item, gridX, gridY);
			SlotPane.getInstance().render();
		}
	}

	public static void handleSceneKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.R) {
			if (currentItem != null) {
				currentItem.rotate(true);
			}
		}
	}

	private void calcGrid() {
		// TODO : Fix this calculating wrong grid
		gridX = (int) ((item.getTranslateX() + Slot.SIZE / 2) - Game.getX(SlotPane.getInstance())) / Slot.SIZE;
		gridY = (int) ((item.getTranslateY() + Slot.SIZE / 2.5) - Game.getY(SlotPane.getInstance())) / Slot.SIZE;
		System.out.println();
		System.out.println(gridX + " : " + gridY);
	}

	// TODO : Might still be buggy after fixing calcGrid
	private void setTranslateNoOffScreenX(double val) {
		double maxWidth = Game.getGamePane().getWidth() - item.getItemWidth() * Slot.SIZE;
		item.setTranslateX(val < 0 ? 0 : (val > maxWidth ? maxWidth : val));
	}

	private void setTranslateNoOffScreenY(double val) {
		double maxHeight = Game.getGamePane().getHeight() - item.getItemHeight() * Slot.SIZE;
		item.setTranslateY(val < 0 ? 0 : (val > maxHeight ? maxHeight : val));
	}
}
