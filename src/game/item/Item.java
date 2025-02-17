package game.item;

import game.backpack.Slot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item extends ImageView {
	protected String name;
	protected int width, height;
	// rotation ranges from 0-7
	protected int rotation;

	public Item(String name, int width, int height) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		rotation = 0;
	}

	public void initialize(Image image) {
		setImage(image);
		setFitWidth(Slot.SIZE * width);
		setFitHeight(Slot.SIZE * height);

		DraggableHandler handler = new DraggableHandler(this);
		setOnMousePressed(event -> handler.handleItemMousePress(event));
		setOnMouseDragged(event -> handler.handleItemMouseDrag(event));
		setOnMouseReleased(event -> handler.handleItemMouseRelease(event));
		// TODO: show toolTip on hover
		// imageView.setOnMouseEntered(event -> {});
	}

	public int getItemWidth() {
		return width;
	}

	public int getItemHeight() {
		return height;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
}
