package game.item;

import interfaces.TurnActivable;

public class ManaOrb extends Item implements TurnActivable{
	private int amount;
	
	public ManaOrb(String name, String detail, int amount, int width, int height) {
		super(name, detail, width, height);
		setAmount(amount);
	}

	public ManaOrb(String name, String detail, int amount, int height) {
		super(name, detail, height);
		setAmount(amount);
	}
	
	@Override
	public void activatePerTurn() {
		// TODO: Add Player max mana by amount
	}
	
	
	//Getter & Setter
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
	
	
}
