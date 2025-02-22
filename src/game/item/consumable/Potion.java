package game.item.consumable;

import game.item.Item;
import game.util.Effect;

public class Potion extends Consumable{
	private int limit;
	
	public Potion(String name, String detail, int durability, Effect effect, int costActivate, int limit, int width, int height) {
		super(name, detail, durability, effect, costActivate, width, height);
		setLimit(limit);
	}

	public Potion(String name, String detail, int durability, Effect effect, int costActivate, int limit, int height) {
		super(name, detail, durability, effect, costActivate, height);
		setLimit(limit);
	}
	
	public boolean isStackable(Item item) {
		if (item.getName().equals(getName()) && getDurability()<limit) {
			return true;
		}
		return false;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit < 1 ? 1 : limit;
	}
}
