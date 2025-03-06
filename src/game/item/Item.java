package game.item;

import java.util.Random;

import game.Game;
import game.backpack.Backpack;
import game.backpack.Slot;
import game.util.ItemRotation;
import game.util.ItemTier;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import logic.FightLogic;
import logic.handler.ItemHandler;

public abstract class Item extends Pane {
	final private ItemTier tier;
	private String name, detail;
	protected int width, height;
	protected boolean isDiagonal;
	protected FadeTransition fadeIn;
	// used for dragging, rotating
	private double diffX, diffY;
	private ImageView imageView;
	private Timeline moveTimeline;
	private Timeline backToOriginTimeline;
	private double currentTranslateY;
	private Tooltip tooltip;

	public Item(String name, String detail, int height, ItemTier tier) {
		super();
		this.name = name;
		this.detail = detail;
		this.width = 1;
		this.height = height;
		isDiagonal = true;
		this.tier = tier;
	}

	public Item(String name, String detail, int width, int height, ItemTier tier) {
		super();
		this.name = name;
		this.detail = detail;
		this.width = width;
		this.height = height;
		isDiagonal = false;
		this.tier = tier;
	}

	public void initialize(Image image) {
		this.setOpacity(0.0);
		fadeIn = new FadeTransition(Duration.seconds(0.5), this);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);

		setMaxSize(Math.max(width, height) * Slot.SIZE, Math.max(width, height) * Slot.SIZE);

		imageView = new ImageView(image);
		imageView.setFitWidth(Slot.SIZE * width);
		imageView.setFitHeight(Slot.SIZE * height);
		calculateDiff();
		imageView.setX(diffX);
		imageView.setY(diffY);
		if (isDiagonal) {
			diffX = diffY = 0;
			imageView.setRotate(45);
		}

		this.setPickOnBounds(false);
		imageView.setPickOnBounds(true);
		imageView.setCursor(FightLogic.getInstance().isInFight() ? Cursor.HAND : Cursor.OPEN_HAND);
		imageView.setOnMousePressed(event -> ItemHandler.handleMousePress(event, this));
		imageView.setOnMouseDragged(event -> ItemHandler.handleMouseDrag(event));
		imageView.setOnMouseReleased(_ -> ItemHandler.handleMouseRelease());

		imageView.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY)
				ItemHandler.handleMousePress(event, this);
		});
		imageView.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY)
				ItemHandler.handleMouseDrag(event);
		});
		imageView.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY)
				ItemHandler.handleMouseRelease();
		});

		getChildren().setAll(imageView);

		tooltip = new Tooltip(toString());
		tooltip.setShowDelay(Duration.millis(200));
		Tooltip.install(imageView, tooltip);
	}

	public void updateTooltip() {
		tooltip.setText(toString());
	}

	public void delete() {
		Backpack.getInstance().removeItem(this);
		Game.getInstance().getChildren().remove(this);
	}

	private void calculateDiff() {
		diffX = height > width ? (Slot.SIZE * height - Slot.SIZE * width) / 2 : 0;
		diffY = width > height ? (Slot.SIZE * width - Slot.SIZE * height) / 2 : 0;
	}

	public void rotate(boolean right) {
		double val = imageView.getRotate() + (right ? 90 : -90);
		imageView.setRotate(val < 0 ? val + 360 : (val >= 360 ? val - 360 : val));
		if (!isDiagonal) {
			int temp = width;
			width = height;
			height = temp;
			double temp2 = diffX;
			diffX = diffY;
			diffY = temp2;
		}
	}

	public int getItemWidth() {
		return width;
	}

	public int getItemHeight() {
		return height;
	}

	public boolean isDiagonal() {
		return isDiagonal;
	}

	public double getDiffX() {
		return diffX;
	}

	public double getDiffY() {
		return diffY;
	}

	public ItemRotation getRotation() {
		if (isDiagonal) {
			return (imageView.getRotate() == 45 || imageView.getRotate() == 225) ? ItemRotation.DIAGONAL_RIGHT
					: ItemRotation.DIAGONAL_LEFT;
		}
		return width > height ? ItemRotation.HORIZONTAL : ItemRotation.VERTICAL;
	}

	public String getName() {
		return name;
	}

	public String getTierName() {
		return tier == null ? "" : tier.toString();
	}

	public ImageView getImageView() {
		return imageView;
	}

	public FadeTransition getFadeIn() {
		return fadeIn;
	}

	public void setFadeIn(FadeTransition fadeIn) {
		this.fadeIn = fadeIn;
	}

	public void moveUpAndDown() {
		moveTimeline = new Timeline();
		if (currentTranslateY == 0)
			currentTranslateY = imageView.getTranslateY();
		double moveDistance = 10;
		double targetY = currentTranslateY + moveDistance;
		Random rand = new Random();
		int dUb = 1500;
		int dLb = 1000;
		int duration = rand.nextInt((dUb - dLb) + 1) + dLb;
		moveTimeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(imageView.translateYProperty(), currentTranslateY)),
				new KeyFrame(Duration.millis(duration),
						new KeyValue(imageView.translateYProperty(), currentTranslateY + moveDistance)),
				new KeyFrame(Duration.millis(duration * 2),
						new KeyValue(imageView.translateYProperty(), currentTranslateY)));
		moveTimeline.setCycleCount(Timeline.INDEFINITE);
		moveTimeline.play();
	}

	public void moveBack() {
		backToOriginTimeline = new Timeline();
		double currentTranslateY = imageView.getTranslateY();
		backToOriginTimeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(imageView.translateYProperty(), currentTranslateY)),
				new KeyFrame(Duration.millis(200),
						new KeyValue(imageView.translateYProperty(), this.currentTranslateY)));
		backToOriginTimeline.setCycleCount(1);
		backToOriginTimeline.play();
	}

	public Timeline getMoveTimeline() {
		return moveTimeline;
	}

	public void setMoveTimeline(Timeline moveTimeline) {
		this.moveTimeline = moveTimeline;
	}

	public double getCurrentTranslateY() {
		return currentTranslateY;
	}

	public void setCurrentTranslateY(double currentTranslateY) {
		this.currentTranslateY = currentTranslateY;
	}

}
