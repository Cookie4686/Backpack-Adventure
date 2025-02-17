package game.item;

import game.backpack.Slot;
import interfaces.ReRenderable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class Item extends Pane implements ReRenderable {
	protected String name;
	protected int width, height;
	// rotation {0, 90, 180, 270} or {45, 135, 225, 315}
	boolean isDiagonal;

	public Item(String name, int width, int height, boolean isDiagonal) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.isDiagonal = isDiagonal;
		setRotate(isDiagonal ? 45 : 0);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setMaxSize(width * Slot.SIZE, height * Slot.SIZE);
		DraggableHandler handler = new DraggableHandler(this);
		setOnMousePressed(event -> handler.handleItemMousePress(event));
		setOnMouseDragged(event -> handler.handleItemMouseDrag(event));
		setOnMouseReleased(event -> handler.handleItemMouseRelease(event));
	}

	public void initialize(Image image) {
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(Slot.SIZE * width);
		imageView.setFitHeight(Slot.SIZE * height);
		getChildren().setAll(imageView);

		// TODO: show toolTip on hover
		// imageView.setOnMouseEntered(event -> {});
	}

	@Override
	public void render() {
	}

	public void rotate(boolean right) {
		double val = getRotate() + (right ? 90 : -90);
		setRotate(val < 0 ? 360 - val : (val > 360 ? val - 360 : val));
	}

	public int getItemWidth() {
		return width;
	}

	public int getItemHeight() {
		return height;
	}

}
