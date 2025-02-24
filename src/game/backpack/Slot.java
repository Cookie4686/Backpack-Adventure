package game.backpack;

import game.handler.ButtonHandler;
import game.item.Item;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Slot extends Pane implements ReRenderable {
	public final static int SIZE = 48;
	private boolean isUnlocked;
	private Item item;

	public Slot() {
		super();
		setMinSize(SIZE, SIZE);
		setMaxSize(SIZE, SIZE);
		isUnlocked = false;
		item = null;
		setOnMouseClicked(event -> ButtonHandler.handleSlotOnClicked(this));
	}

	@Override
	public void render() {
		if (isUnlocked) {
			if (item != null) {
				setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			} else {
				setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		} else {
			setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}

	public void highlight() {
		setBackground(new Background(new BackgroundFill(Color.GOLDENROD, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public boolean isUnlocked() {
		return isUnlocked;
	}

	public void setUnlocked(boolean isUnlocked) {
		this.isUnlocked = isUnlocked;
		render();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
