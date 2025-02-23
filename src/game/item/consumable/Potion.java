package game.item.consumable;

import game.item.Item;
import game.util.Effect;
import game.util.ItemTier;

public class Potion extends Consumable{
	private int limit;
	
	public Potion(String name, String detail, int durability, Effect effect, int costActivate, int limit, int width, int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, width, height, tier);
		setLimit(limit);
	}

	public Potion(String name, String detail, int durability, Effect effect, int costActivate, int limit, int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, height, tier);
		setLimit(limit);
	}
	
	public boolean isStackable(Item item) {
		if (item.getName().equals(getName()) && getDurability()<limit) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getProvide()+"This item is Stackable! (At most "+limit+")"
				+ "\nCost "+getCostActivate()+" ENERGY per click";
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit < 1 ? 1 : limit;
	}
}
