package logic.handler;

import java.util.Random;

import application.Main;
import entities.Player;
import game.Game;
import game.GameBottom;
import game.backpack.Backpack;
import game.backpack.Slot;
import game.item.Item;
import game.util.ItemRotation;
import interfaces.Clickable;
import interfaces.ReStatable;
import interfaces.StatUpdatable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import logic.FightLogic;
import logic.GameLogic;

public class ItemHandler {
	public static Item currentItem;
	private static double startX, startY;
	private static double diffX, diffY, maxWidth, maxHeight, slotPaneX, slotPaneY;
	private static int gridX, gridY;

	public static void handleMousePress(MouseEvent event, Item item) {
		currentItem = item;
		if (!FightLogic.getInstance().isInFight()) {
			calcValues();
			startX = event.getSceneX() - item.getTranslateX();
			startY = event.getSceneY() - item.getTranslateY();
		} else {
			if (item instanceof Clickable) {
				((Clickable) item).activatePerClick();
			}
			Backpack.getInstance().render();
			GameBottom.getInstance().render();
		}
	}

	public static void handleMouseDrag(MouseEvent event) {
		if (!FightLogic.getInstance().isInFight()) {
			setTranslateNoOffScreenX(event.getSceneX() - startX);
			setTranslateNoOffScreenY(event.getSceneY() - startY);
			calcGrid();
			hightlightGrid();
		}
	}

	public static void handleMouseRelease() {
		if (!FightLogic.getInstance().isInFight()) {
			calcGrid();
			placeItem();
			currentItem = null;
		}
	}

	public static void handleSceneKeyPress(KeyEvent event) {
		if (!FightLogic.getInstance().isInFight()) {
			if (event.getCode() == KeyCode.R) {
				if (currentItem != null) {
					rotateItem();
				}
			}
		}
	}

	private static void hightlightGrid() {
		Backpack.getInstance().render();
		Backpack.getInstance().hightlight(gridX, gridY, currentItem);
	}

	private static void placeItem() {
		if (Backpack.getInstance().placeItem(gridX, gridY, currentItem)) {
			setPlaceItemPostion();
		}

		Player.getInstance().reStatBeforeUpdate();
		for (Item item : GameLogic.getInstance().getInventory()) {
			if (item instanceof ReStatable) {
				((ReStatable) item).reStatBeforeUpdate();
			}
		}

		for (Item item : GameLogic.getInstance().getInventory()) {
			if (item instanceof StatUpdatable) {
				((StatUpdatable) item).statUpdate();
				;
			}
		}
	}

	private static void rotateItem() {
		currentItem.rotate(true);
		calcValues();
		setTranslateNoOffScreenX(currentItem.getTranslateX());
		setTranslateNoOffScreenY(currentItem.getTranslateY());
		hightlightGrid();
	}

	public static void setRandomOffGridLocation(Item item) {
		Item temp = currentItem;
		currentItem = item;
		calcValues();
		Random random = new Random();
		double x = 0, y = 0;
		for (int i = 0; i < 10; i++) {
			x = random.nextDouble(-Backpack.getInstance().getWidth() / 2, Backpack.getInstance().getWidth() / 2)
					+ item.getTranslateX();
			y = random.nextDouble(0, Backpack.getInstance().getHeight() / 2) + item.getTranslateY();
			if (!(slotPaneX <= x && x <= slotPaneX + Backpack.getInstance().getWidth() && slotPaneY <= y
					&& y <= slotPaneY + Backpack.getInstance().getHeight())) {
				break;
			}
		}
		setTranslateNoOffScreenX(x);
		setTranslateNoOffScreenY(y);
		currentItem = temp;
		calcValues();
	}

	private static void setPlaceItemPostion() {
		double x = Slot.SIZE * gridX - currentItem.getDiffX();
		double y = Slot.SIZE * gridY - currentItem.getDiffY();
		if (currentItem.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
			x -= currentItem.getWidth() - Slot.SIZE;
		}
		setTranslateNoOffScreenX(x + slotPaneX);
		setTranslateNoOffScreenY(y + slotPaneY);
	}

	private static void calcGrid() {
		double x = currentItem.getTranslateX() + currentItem.getDiffX() + Slot.SIZE / 2;
		double y = currentItem.getTranslateY() + currentItem.getDiffY() + Slot.SIZE / 2;
		if (currentItem.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
			x += currentItem.getWidth() - Slot.SIZE;
		}
		x -= slotPaneX;
		y -= slotPaneY;
		gridX = (int) (x < 0 ? -1 : x / Slot.SIZE);
		gridY = (int) (y < 0 ? -1 : y / Slot.SIZE);
	}

	private static void calcValues() {
		if(currentItem != null) {			
		diffX = currentItem.getRotation() == ItemRotation.VERTICAL ? currentItem.getDiffX() : 0;
		maxWidth = Game.getInstance().getWidth() - currentItem.getWidth() + diffX;
		diffY = currentItem.getRotation() == ItemRotation.HORIZONTAL ? currentItem.getDiffY() : 0;
		maxHeight = Game.getInstance().getHeight() - currentItem.getHeight() + diffY;
		}
		slotPaneX = Game.getX(Backpack.getInstance());
		slotPaneY = Game.getY(Backpack.getInstance());
	}

	public static void addItemsToGame(Item... items) {
		Item temp = currentItem;
		// length = 5, row = 2 itemPerRow = 2
		final int row = 2, itemPerRow = items.length / row, spacingX = 8, spacingY = 4;
		double prevHeight = Backpack.getInstance().getHeight();
		for (int i = 0; i < row; i++) {
			double width = 0, height = 0;
			int limit = itemPerRow + (i + 1 == row ? items.length % row : 0);
			// calculate the total width
			for (int j = 0; j < limit; j++) {
				Item item = items[i * itemPerRow + j];
				width += item.getItemWidth() * Slot.SIZE + spacingX;
				height = Math.max(height, item.getItemHeight() * Slot.SIZE);
			}
			double diffX = Main.root.getWidth() / 2 - width / 2;
			// place item
			double accumulator = 0;
			for (int j = 0; j < limit; j++) {
				currentItem = items[i * itemPerRow + j];
				calcValues();
				setTranslateNoOffScreenX(diffX + accumulator - currentItem.getDiffX());
				double diffY = height - currentItem.getItemHeight() * Slot.SIZE;
				setTranslateNoOffScreenY(prevHeight + (diffY == 0 ? 0 : diffY / 2));
				accumulator += currentItem.getItemWidth() * Slot.SIZE + spacingX;
			}
			prevHeight += height + spacingY;
		}
		currentItem = temp;
		calcValues();
	}

	private static void setTranslateNoOffScreenX(double val) {
		currentItem.setTranslateX(val < -diffX ? -diffX : (val > maxWidth ? maxWidth : val));
	}

	private static void setTranslateNoOffScreenY(double val) {
		currentItem.setTranslateY(val < -diffY ? -diffY : (val > maxHeight ? maxHeight : val));
	}
}
