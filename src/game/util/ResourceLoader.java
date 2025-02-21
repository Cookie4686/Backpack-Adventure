package game.util;

import java.util.HashMap;
import java.util.function.Supplier;

import game.item.Item;
import game.item.consumable.Food;
import game.item.wareable.Armor;
import game.item.weapon.ElementalSword;
import game.item.weapon.Weapon;
import javafx.scene.image.Image;

public class ResourceLoader {
	private static HashMap<String, Resource> itemMap;

	static {
		itemMap = new HashMap<String, Resource>();
		itemMap.put("apple", new Resource(() -> new Food("apple", "edible", 1, null, 0, 1, 1), "apple.png"));
		itemMap.put("temp", new Resource(() -> new Armor("Armor", "Strong!", 2, 2, null, 0, 1, 2), "debug.png"));
		itemMap.put("temp2", new Resource(
				() -> new ElementalSword("ElementalSword", "So COOL!!!", EffectType.FIRE, 2, 2, 2, 2), "debug.png"));
		itemMap.put("temp3", new Resource(() -> new Weapon("temp", "temp", 1, 1, 1, 2), "debug.png"));
		itemMap.put("temp4", new Resource(() -> new Weapon("temp", "temp", 1, 1, 1, 3), "debug.png"));
		itemMap.put("temp5", new Resource(() -> new Weapon("temp", "temp", 1, 1, 2, 1), "debug.png"));
		itemMap.put("temp6", new Resource(() -> new Weapon("temp", "temp", 1, 1, 3, 1), "debug.png"));
		itemMap.put("temp7", new Resource(() -> new Weapon("temp", "temp", 1, 1, 2, 3), "debug.png"));
		itemMap.put("temp8", new Resource(() -> new Weapon("temp", "temp", 1, 1, 3, 2), "debug.png"));
	}

	public static Item newItem(String name) {
		Resource resource = itemMap.get(name);
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
