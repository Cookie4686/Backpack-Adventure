package backpack.item;

import backpack.Slot;
import interfaces.ReRenderable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item extends Pane implements ReRenderable {
	private Resource resource;
	// rotation ranges from 0-7
	protected int rotation;

	public Item(String name) {
		super();
		resource = ResourceLoader.getResource(name);
		rotation = 0;
		render();
	}

	@Override
	public void render() {
		ImageView imageView = new ImageView(resource.getImage());
		imageView.setFitWidth(Slot.SIZE * resource.getWidth());
		imageView.setFitHeight(Slot.SIZE * resource.getHeight());
		getChildren().setAll(imageView);

		DraggableHandler handler = new DraggableHandler(this);
		imageView.setOnMousePressed(event -> handler.handleItemMousePress(event));
		imageView.setOnMouseDragged(event -> handler.handleItemMouseDrag(event));
		imageView.setOnMouseReleased(event -> handler.handleItemMouseRelease(event));
		// TODO: show toolTip on hover
		// imageView.setOnMouseEntered(event -> {});
	}

	public int getItemWidth() {
		return resource.getWidth();
	}

	public int getItemHeight() {
		return resource.getHeight();
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

}
