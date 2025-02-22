package game.item;

import interfaces.TurnActivable;

public class Coins extends Item implements TurnActivable{
	private int amount;

	public Coins(String name, String detail, int amount) {
		super(name, detail, 1, 1);
	}
	
	@Override
	public void activatePerTurn() {
		// TODO : set Player isMoney true;
		// TODO : set Player Money = amount;
		
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount < 0 ? 0 : amount;
	}
}
