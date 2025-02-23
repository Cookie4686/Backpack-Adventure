package game.item;

import entities.Player;
import game.util.ItemTier;
import interfaces.StatUpdatable;

public class Coins extends Item implements StatUpdatable{
	private int amount;

	public Coins(String name, String detail, int amount, ItemTier tier) {
		super(name, detail, 1, 1, tier);
	}
	
	@Override
	public void statUpdate() {
		Player.getInstance().setMoney(amount);
	}
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
}
