package game.item.consumable;

import game.util.Effect;
import game.util.ItemTier;

public class FoodWithContainer extends Consumable {
	private String container;

	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability,
			Effect effect, int width, int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, width, height, tier);
		this.container = container;
	}

	@Override
	protected String getHeader() {
		return getName() + " is " + getTierName() + " Drink\n" + getDurability() + " use left" + "\nWhen click :\n";
	}

	@Override
	public String toString() {
		return getHeader() + getProvideMid() + "\nWhen out of uses. Will be replace by " + container + "\nCost "
				+ getCostActivate() + " ENERGY per click";
	}

	public String getContainer() {
		return container;
	}
}
