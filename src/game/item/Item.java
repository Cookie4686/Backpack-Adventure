package game.item;

import game.backpack.Slot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item extends Pane {
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
