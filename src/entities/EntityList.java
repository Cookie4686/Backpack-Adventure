package entities;

import java.util.HashMap;


import game.util.Effect;
import game.util.EffectType;

public class EntityList {
private static HashMap<String, Resource> entityMap;
	
	static {
		entityMap = new HashMap<String, Resource>();
		
		//Weapon(String name, String detail, int initdamage, int costActivate, int width, int height)
		entityMap.put("werewolf", new Resource(() -> new Entity(null, null, 10, 10, 1, null), "werewolf-idle1.png"));
		//Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width, int height)
		//entityMap.put("werewolf", new Resource(() -> new Entity("apple", "edible", 1, new Effect(2, EffectType.HEAL), 0, 1, 1, ItemTier.COMMON), "apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
//		itemMap.put("apple", new Resource(() -> new Consumable("apple", "edible", 1, null, 1, 1, 1), "apple.png"));
	}

	public static HashMap<String, Resource> getItemMap() {
		return entityMap;
	}
}
