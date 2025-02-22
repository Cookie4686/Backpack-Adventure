package game.item;

import java.util.function.Supplier;

import javafx.scene.image.Image;

public class ResourceLoader {
	
	
	public static Item newItem(String name) {
		Resource resource = ItemList.getItemMap().get(name);
		return resource == null ? null : resource.newItem();
	}
}

class Resource {
	private Supplier<Item> supplier;
	private String path;
	private Image image;

	public Resource(Supplier<Item> supplier, String path) {
		this.supplier = supplier;
		this.path = path;
	}

	public Item newItem() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(path).toString());
		}
		Item item = supplier.get();
		item.initialize(image);
		return item;
	}
}
