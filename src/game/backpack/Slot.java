package game.backpack;

import game.item.Item;
import image.GifPlayer;
import interfaces.ReRenderable;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.handler.ButtonHandler;

public class Slot extends StackPane implements ReRenderable {
	private final static int SIZE = 48;
	private ImageView slotBackground;
	private ImageView selectAnimation;
	private ImageView upgradeAnimation;
	private Timeline select;
	private Timeline upgrade;
	private boolean isUnlocked;
	private boolean unlockAble;
	private Item item;

	public Slot() {
		super();
		setMinSize(SIZE, SIZE);
		setMaxSize(SIZE, SIZE);
		isUnlocked = false;
		unlockAble = false;
		item = null;
		
//		highlight = new ImageView(new Image(ClassLoader.getSystemResource("picture/highlight1.png").toString()));
//		highlight.setFitHeight(SIZE);
//		highlight.setFitWidth(SIZE);
		
		selectAnimation = new ImageView();
		select = GifPlayer.createAnimation(selectAnimation, GifPlayer.getSelectIcons(), 0.15);
		select.setCycleCount(Timeline.INDEFINITE);
		selectAnimation.setFitHeight(SIZE);
		selectAnimation.setFitWidth(SIZE);
		
		upgradeAnimation = new ImageView();
		upgrade = GifPlayer.createAnimation(upgradeAnimation, GifPlayer.getHighlightIcons(), 0.2);
		upgrade.setCycleCount(Timeline.INDEFINITE);
		upgradeAnimation.setFitHeight(SIZE);
		upgradeAnimation.setFitWidth(SIZE);
		
		slotBackground = new ImageView();
		slotBackground.setFitHeight(SIZE);
		slotBackground.setFitWidth(SIZE);
		getChildren().add(slotBackground);
		setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (Backpack.getInstance().isLevelup()) {
					ButtonHandler.handleSlotOnClicked(this);
				}
			}
		});
	}
	
	@Override
	public void render() {
		if (isUnlocked) {
			if (item != null) {
				getChildren().removeAll(selectAnimation, upgradeAnimation);
				select.stop();
				setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
				slotBackground.setImage(new Image(ClassLoader.getSystemResource("picture/usedSlot.png").toString()));
			} else {
				getChildren().removeAll(selectAnimation, upgradeAnimation);
				select.stop();
				setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
				slotBackground.setImage(new Image(ClassLoader.getSystemResource("picture/emptySlot.png").toString()));
			}
		}
	}
	
	public void highlightUpgrade() {
		upgrade.stop();
		if (!getChildren().contains(upgradeAnimation)) {
			unlockAble = true;
			getChildren().add(upgradeAnimation);
		}
		upgrade.play();
	}
	
	public void removeUpgradeAnimation() {
		upgrade.stop();
		getChildren().remove(upgradeAnimation);
	}
	
	public void highlightRelic() {
		setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void highlight() {
		if (!getChildren().contains(selectAnimation)) {
			getChildren().add(selectAnimation);
			select.play();
		}
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

	public static int getSize() {
		return SIZE;
	}

	public boolean isUnlockAble() {
		return unlockAble;
	}

	public void setUnlockAble(boolean unlockAble) {
		this.unlockAble = unlockAble;
	}
}
