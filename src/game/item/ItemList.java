package game.item;

import java.util.HashMap;

import game.item.consumable.Consumable;
import game.item.wareable.Armor;
import game.item.weapon.Weapon;
import game.util.Effect;
import game.util.EffectType;

public class ItemList {
	private static HashMap<String, Resource> itemMap;
	
	static {
		itemMap = new HashMap<String, Resource>();
		
		//Weapon(String name, String detail, int initdamage, int costActivate, int width, int height)
		itemMap.put("sword", new Resource(() -> new Weapon("sword", "Sharp!", 10, 0, 1, 1), "debug.png"));
		//Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width, int height)
		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 2, new Effect(2, EffectType.HEAL), 0, 1, 1), "apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
//		itemMap.put("temp", new Resource(() -> new Armor("Armor", "Strong!", 2, 2, null, 1, 2), "debug.png"));
	}

	public static HashMap<String, Resource> getItemMap() {
		return itemMap;
	}
}
