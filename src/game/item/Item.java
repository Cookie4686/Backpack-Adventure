package game.item;

import game.backpack.Slot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class Item extends Pane {
	protected String name;
	protected int width, height;
	protected boolean isDiagonal;

	// used for dragging, rotating
	protected DraggableHandler handler;
	private ImageView imageView;
	private double diffX, diffY;

	// Non-Diagonal Constructor
	public Item(String name, int width, int height) {
		super();
		isDiagonal = false;
		this.name = name;
		this.width = width;
		this.height = height;
	}

	// Diagonal Constructor
	public Item(String name, int height) {
		super();
		isDiagonal = true;
		this.name = name;
		this.width = 1;
		this.height = height;
	}

	public void initialize(Image image) {
		setMaxSize(Math.max(width, height) * Slot.SIZE, Math.max(width, height) * Slot.SIZE);
		setPickOnBounds(false);
		setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		imageView = new ImageView(image);
		imageView.setFitWidth(Slot.SIZE * width);
		imageView.setFitHeight(Slot.SIZE * height);
		imageView.setPickOnBounds(true);
		calculateDiff();
		imageView.setX(diffX);
		imageView.setY(diffY);
		if (isDiagonal) {
			diffX = diffY = 0;
			imageView.setRotate(45);
		}

		handler = new DraggableHandler(this);
		imageView.setOnMousePressed(event -> handler.handleItemMousePress(event));
		imageView.setOnMouseDragged(event -> handler.handleItemMouseDrag(event));
		imageView.setOnMouseReleased(event -> handler.handleItemMouseRelease(event));
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

	public ItemRotation getRotation() {
		if (isDiagonal) {
			return (imageView.getRotate() == 45 || imageView.getRotate() == 225) ? ItemRotation.DIAGONAL_RIGHT
					: ItemRotation.DIAGONAL_LEFT;
		}
		return width > height ? ItemRotation.HORIZONTAL : ItemRotation.VERTICAL;
	}

	public double getDiffX() {
		return diffX;
	}

	public double getDiffY() {
		return diffY;
	}
}
