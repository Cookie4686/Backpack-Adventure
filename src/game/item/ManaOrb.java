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
	
	@Override
	public void activatePerTurn() {
		Player.getInstance().setMaxMana(Player.getInstance().getMaxMana() + amount);
	}
	
	
	@Override
	public String toString() {
		return getName()+" is "+getTierName()+" ManaOrb\n"
				+ "Provide :\n"
				+ "Add "+amount+" MANA to Player\n\n"
				+ "Activate when in backpack";
	}
	
	//Getter & Setter
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
	
	
}
