package game.item;

import entities.Player;
import game.util.ItemTier;
import interfaces.StatUpdatable;

public class Coins extends Item implements StatUpdatable {
	private int amount;

	public Coins(String name, String detail, int amount, ItemTier tier) {
		super(name, detail, 1, 1, tier);
	}

	@Override
	public void statUpdate() {
		Player.getInstance().setCoins(Player.getInstance().getCoins() + amount);
	}

	public boolean isStackable(Item item) {
		return item.getName().equals(getName());
	}

	@Override
	public String toString() {
		return getName() + " is " + getTierName() + " ManaOrb\n"
				+ "Provide :\n"
				+ "Add " + amount + " COINS to Player\n"
				+ "This item is Stackable!\n"
				+ "\nActivate when in backpack";
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
}
