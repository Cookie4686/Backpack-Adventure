package game.item;

import entities.Player;
import game.util.ItemTier;
import interfaces.TurnActivable;

public class ManaOrb extends Item implements TurnActivable{
	private int amount;
	
	public ManaOrb(String name, String detail, int amount, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		setAmount(amount);
	}

	public ManaOrb(String name, String detail, int amount, int height, ItemTier tier) {
		super(name, detail, height, tier);
		setAmount(amount);
	}
	
	@Override
	public void activatePerTurn() {
		Player.getInstance().setMaxMana(Player.getInstance().getMaxMana() + amount);
	}
	
	
	//Getter & Setter
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
	
	
}
