package game.item;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.image.Image;

public class ResourceLoader {
	private static HashMap<String, Resource> itemMap;

	static {
		itemMap = new HashMap<String, Resource>();
		itemMap.put("apple", new Resource(() -> new Weapon("apple", 1, 1), "apple.png"));
	}

	public static Item newItem(String name) {
		return itemMap.get(name).newItem();
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
			System.out.println("New image");
		}
		Item item = supplier.get();
		item.initialize(image);
		return item;
	}
}
