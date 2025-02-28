package game.item.consumable;

import game.item.Item;
import game.util.Effect;
import game.util.ItemTier;

public class Potion extends FoodWithContainer{
	static private int limit=3;
	
	public Potion(String name, String detail, String container, int costActivate, int durability, Effect effect, int width, int height, ItemTier tier) {
		super(name, detail, container, costActivate, durability, effect, width, height, tier);
	}

	
	public boolean isStackable(Item item) {
		if (item.getName().equals(getName()) && getDurability()<limit) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getProvide()+"This item is Stackable! (At most "+limit+")."
				+ "\nWhen out of uses. Will be replace by "+container
				+ ".\nCost "+getCostActivate()+" ENERGY per click";
	}
	
	public int getLimit() {
		return limit;
	}
}
