package game.itemGenerator;

import java.util.HashMap;

import game.item.consumable.Consumable;
import game.item.weapon.Weapon;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;

public class ItemList {
	private static HashMap<String, Resource> itemMap;
	
	static {
		itemMap = new HashMap<String, Resource>();
		
		//Weapon(String name, String detail, int initdamage, int costActivate, int width, int height)
		itemMap.put("sword", new Resource(() -> new Weapon("sword", "Sharp!", 10, 1, 1, 2, ItemTier.COMMON), "Items/AzuriteSword.png"));
		//Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width, int height)
		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, new Effect(2, EffectType.HEAL), 0, 1, 1, ItemTier.COMMON), "Items/apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
	}

	public static HashMap<String, Resource> getItemMap() {
		return itemMap;
	}
}
