package game.item;

import game.backpack.Slot;
import game.util.ItemHandler;
import game.util.ItemRotation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item extends Pane {
	protected String name, detail;
	protected int width, height;
	protected boolean isDiagonal;

	// used for dragging, rotating
	private double diffX, diffY;
	private ImageView imageView;

	private boolean isInBackpack;

	public Item(String name, String detail, int height) {
		super();
		this.name = name;
		this.detail = detail;
		this.width = 1;
		this.height = height;
		isDiagonal = true;
	}

	public Item(String name, String detail, int width, int height) {
		super();
		this.name = name;
		this.detail = detail;
		this.width = width;
		this.height = height;
		isDiagonal = false;
	}

	public void initialize(Image image) {
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
		imageView.setOnMousePressed(event -> ItemHandler.handleMousePress(event, this));
		imageView.setOnMouseDragged(event -> ItemHandler.handleMouseDrag(event));
		imageView.setOnMouseReleased(event -> ItemHandler.handleMouseRelease());
		getChildren().setAll(imageView);

		// TODO: show toolTip on hover
		// imageView.setOnMouseEntered(event -> {});
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

	public boolean isInBackpack() {
		return isInBackpack;
	}

	public void setInBackpack(boolean isInBackpack) {
		this.isInBackpack = isInBackpack;
	}
}
