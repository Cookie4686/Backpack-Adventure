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
	// rotation {0, 90, 180, 270} or {45, 135, 225, 315}
	private ImageView imageView;
	private double diff;

	public Item(String name, int height) {
		super();
		this.name = name;
		this.width = 1;
		this.height = height;
		isDiagonal = true;
		setPickOnBounds(false);
		setMaxSize(height * Slot.SIZE, height * Slot.SIZE);
		setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	public Item(String name, int width, int height) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		isDiagonal = false;
		setPickOnBounds(false);
		setMaxSize(Math.max(width, height) * Slot.SIZE, Math.max(width, height) * Slot.SIZE);
		setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	public void initialize(Image image) {
		imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(Slot.SIZE * width);
		imageView.setFitHeight(Slot.SIZE * height);
		imageView.setPickOnBounds(true);
		diff = (Slot.SIZE * height - Slot.SIZE) / 2;
		imageView.setX(diff);
		imageView.setRotate(isDiagonal ? 45 : 0);
		DraggableHandler handler = new DraggableHandler(this);
		imageView.setOnMousePressed(event -> handler.handleItemMousePress(event));
		imageView.setOnMouseDragged(event -> handler.handleItemMouseDrag(event));
		imageView.setOnMouseReleased(event -> handler.handleItemMouseRelease(event));
		getChildren().setAll(imageView);

		// TODO: show toolTip on hover
		// imageView.setOnMouseEntered(event -> {});
	}

	public void rotate(boolean right) {
		double val = imageView.getRotate() + (right ? 90 : -90);
		imageView.setRotate(val < 0 ? 360 + val : (val >= 360 ? val - 360 : val));
	}

	public int getItemWidth() {
		return width;
	}

	public int getItemHeight() {
		return height;
	}

	public ItemRotation getRotation() {
		return switch ((int) imageView.getRotate()) {
		case 0 -> ItemRotation.VERTICAL;
		case 180 -> ItemRotation.VERTICAL;
		case 90 -> ItemRotation.HORIZONTAL;
		case 270 -> ItemRotation.HORIZONTAL;
		case 45 -> ItemRotation.DIAGONAL_RIGHT;
		case 225 -> ItemRotation.DIAGONAL_RIGHT;
		case 135 -> ItemRotation.DIAGONAL_LEFT;
		case 315 -> ItemRotation.DIAGONAL_LEFT;
		default -> null;
		};
	}

	public double getDiff() {
		return diff;
	}
}
