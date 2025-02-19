package game.item;

import java.util.Random;

import game.Game;
import game.backpack.Slot;
import game.backpack.SlotPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class DraggableHandler {
	public static Item currentItem;
	private static double startX, startY;
	private static double diffX, diffY, maxWidth, maxHeight, slotPaneX, slotPaneY;

	private int gridX, gridY;
	private Item item;

	public DraggableHandler(Item item) {
		this.item = item;
	}

	public void handleItemMousePress(MouseEvent event) {
		currentItem = item;
		startX = event.getSceneX() - item.getTranslateX();
		startY = event.getSceneY() - item.getTranslateY();
		calcValues(currentItem);
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
			setTranslateNoOffScreenX(x + slotPaneX);
			setTranslateNoOffScreenY(y + slotPaneY);
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
		x -= slotPaneX;
		y -= slotPaneY;
		gridX = (int) (x < 0 ? -1 : x / Slot.SIZE);
		gridY = (int) (y < 0 ? -1 : y / Slot.SIZE);
	}

	public void setTranslateNoOffScreenX(double val) {
		item.setTranslateX(val < -diffX ? -diffX : (val > maxWidth ? maxWidth : val));
	}

	public void setTranslateNoOffScreenY(double val) {
		item.setTranslateY(val < -diffY ? -diffY : (val > maxHeight ? maxHeight : val));
	}

	public static void setRandomOffGridLocation(Item item) {
		calcValues(item);
		Random random = new Random();
		double x = 0, y = 0;
		for (int i = 0; i < 10; i++) {
			x = random.nextDouble(-SlotPane.getInstance().getWidth() / 2, SlotPane.getInstance().getWidth() / 2)
					+ item.getTranslateX();
			y = random.nextDouble(0, SlotPane.getInstance().getHeight() / 2) + item.getTranslateY();
			if (!(slotPaneX <= x && x <= slotPaneX + SlotPane.getInstance().getWidth() && slotPaneY <= y
					&& y <= slotPaneY + SlotPane.getInstance().getHeight())) {
				break;
			}
		}
		item.handler.setTranslateNoOffScreenX(x);
		item.handler.setTranslateNoOffScreenY(y);
	}

	private static void calcValues(Item item) {
		diffX = item.getRotation() == ItemRotation.VERTICAL ? diffX = item.getDiffX() : 0;
		maxWidth = Game.getGamePane().getWidth() - item.getWidth();
		maxWidth += diffX;
		diffY = item.getRotation() == ItemRotation.HORIZONTAL ? diffY = item.getDiffY() : 0;
		maxHeight = Game.getGamePane().getHeight() - item.getHeight();
		maxHeight += diffY;
		slotPaneX = Game.getX(SlotPane.getInstance());
		slotPaneY = Game.getY(SlotPane.getInstance());
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
