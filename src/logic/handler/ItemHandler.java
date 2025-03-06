package logic.handler;

import java.util.Random;

import entities.Player;
import game.Game;
import game.GameHeader;
import game.backpack.Backpack;
import game.backpack.Slot;
import game.item.Item;
import game.util.ItemRotation;
import interfaces.Clickable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import logic.FightLogic;
import logic.GameLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class ItemHandler {
	public static Item currentItem;
	private static double startX, startY;
	private static double diffX, diffY, maxWidth, maxHeight, slotPaneX, slotPaneY;
	private static int gridX, gridY;

	public static void handleMousePress(MouseEvent event, Item item) {
		if (!FightLogic.getInstance().isInFight()) {
			currentItem = item;
			currentItem.getImageView().setCursor(Cursor.CLOSED_HAND);
			SfxPlayer.play(Sfx.DRAG);
			currentItem.getMoveTimeline().stop();
			currentItem.moveBack();
			calcValues();
			startX = event.getSceneX() - item.getTranslateX();
			startY = event.getSceneY() - item.getTranslateY();
		} else if (FightLogic.getInstance().isPTurn()) {
			if (item instanceof Clickable) {
				((Clickable) item).activatePerClick();
				Player.getInstance().render();
			}
		} else {
			SfxPlayer.play(Sfx.DENY);
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
		if (!FightLogic.getInstance().isInFight() && currentItem != null) {
			currentItem.getImageView().setCursor(Cursor.OPEN_HAND);
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
		GameLogic.updateBackpackItems();
		Player.getInstance().render();
	}

	private static void rotateItem() {
		currentItem.rotate(true);
		calcValues();
		setTranslateNoOffScreenX(currentItem.getTranslateX());
		setTranslateNoOffScreenY(currentItem.getTranslateY());
		hightlightGrid();
	}

	public static void setRandomOffGridLocation(Item item) {
		item.moveUpAndDown();
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
		// setTranslateNoOffScreenX(x);
		// setTranslateNoOffScreenY(y);
		Timeline moveTimeline = new Timeline();
		moveTimeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(currentItem.translateXProperty(), currentItem.getTranslateX()),
						new KeyValue(currentItem.translateYProperty(), currentItem.getTranslateY())),
				new KeyFrame(Duration.millis(200),
						new KeyValue(currentItem.translateXProperty(),
								x < -diffX ? -diffX : (x > maxWidth ? maxWidth : x)),
						new KeyValue(currentItem.translateYProperty(),
								y < -diffY ? -diffY : (y > maxHeight ? maxHeight : y))));
		moveTimeline.play();
		currentItem = temp;
		calcValues();
	}

	private static void setPlaceItemPostion() {
		double x = Slot.getSize() * gridX - currentItem.getDiffX();
		double y = Slot.getSize() * gridY - currentItem.getDiffY();
		if (currentItem.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
			x -= currentItem.getWidth() - Slot.getSize();
		}
		setTranslateNoOffScreenX(x + slotPaneX);
		setTranslateNoOffScreenY(y + slotPaneY);

	}

	private static void calcGrid() {
		double x = currentItem.getTranslateX() + currentItem.getDiffX() + Slot.getSize() / 2;
		double y = currentItem.getTranslateY() + currentItem.getDiffY() + Slot.getSize() / 2;
		if (currentItem.getRotation() == ItemRotation.DIAGONAL_RIGHT) {
			x += currentItem.getWidth() - Slot.getSize();
		}
		x -= slotPaneX;
		y -= slotPaneY;
		gridX = (int) (x < 0 ? -1 : x / Slot.getSize());
		gridY = (int) (y < 0 ? -1 : y / Slot.getSize());
	}

	private static void calcValues() {
		if (currentItem != null) {
			diffX = currentItem.getRotation() == ItemRotation.VERTICAL ? currentItem.getDiffX() : 0;
			maxWidth = Game.getInstance().getWidth() - currentItem.getWidth() + diffX;
			diffY = currentItem.getRotation() == ItemRotation.HORIZONTAL ? currentItem.getDiffY() : 0;
			maxHeight = Game.getInstance().getHeight() - currentItem.getHeight() + diffY;
		}
		slotPaneX = Backpack.getInstance().getGridPane().localToScene(Backpack.getInstance().getGridPane().getBoundsInLocal()).getMinX();
		slotPaneY = Backpack.getInstance().getGridPane().localToScene(Backpack.getInstance().getGridPane().getBoundsInLocal()).getMinY() - GameHeader.getInstance().getHeight();
	}

	private static void setTranslateNoOffScreenX(double val) {
		currentItem.setTranslateX(val < -diffX ? -diffX : (val > maxWidth ? maxWidth : val));
	}

	private static void setTranslateNoOffScreenY(double val) {
		currentItem.setTranslateY(val < -diffY ? -diffY : (val > maxHeight ? maxHeight : val));
	}
}
