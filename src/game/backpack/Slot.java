package game.backpack;

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

	public Slot(double x, double y) {
		super();
		setMinSize(SIZE, SIZE);
		setMaxSize(SIZE, SIZE);
		isUnlocked = false;
		item = null;
	}

	@Override
	public void render() {
		if (isUnlocked) {
			setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		if (isUnlocked) {
			this.item = item;
		}
	}
}
