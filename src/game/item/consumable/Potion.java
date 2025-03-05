package game.item.consumable;

import game.item.Item;
import game.util.Effect;
import game.util.ItemTier;

public class Potion extends FoodWithContainer {
	static private int limit = 3;

	public Potion(String name, String detail, String container, int costActivate, int durability, Effect effect,
			int width, int height, ItemTier tier) {
		super(name, detail, container, costActivate, durability, effect, width, height, tier);
	}

	public boolean isStackable(Item item) {
		return item.getName().equals(getName()) && getDurability() < limit;
	}

	@Override
	protected String getHeader() {
		return getName() + " is " + getTierName() + " Potion\n" + getDurability() + " use left" + "\nWhen click :\n";
	}

	@Override
	public String toString() {
		return getHeader() + getProvideMid() + "\nThis item is Stackable! (At most " + limit + ")."
				+ "\nWhen out of uses. Will be replace by " + getContainer() + ".\nCost " + getCostActivate()
				+ " ENERGY per click";
	}

	public int getLimit() {
		return limit;
	}
}
