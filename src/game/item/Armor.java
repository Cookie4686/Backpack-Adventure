package game.item;

import interfaces.Adjacency;

public class Armor extends Item implements Adjacency{
	final private int initialShield;
	private int shield;
	private int costEnergy;
	private int increaseShield;
	
	public Armor(String name, String detail, int initialShield, int increaseShield, int costEnergy, int width, int height) {
		super(name, detail, width, height);
		this.shield = initialShield;
		this.costEnergy = costEnergy;
		this.initialShield = initialShield;
		this.increaseShield = increaseShield;
	}
	
	public Armor(String name, String detail, int initialShield, int increaseShield, int costEnergy, int width) {
		super(name, detail, width);
		this.shield = initialShield;
		this.costEnergy = costEnergy;
		this.initialShield = initialShield;
		this.increaseShield = increaseShield;
	}

	
	public void increaseCauseAdjacent() {
		//TODO: increase shield per adjacency
		
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getCostEnergy() {
		return costEnergy;
	}

	public void setCostEnergy(int costEnergy) {
		this.costEnergy = costEnergy;
	}

	public int getInitialShield() {
		return initialShield;
	}
}
