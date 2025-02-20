package game.item;

public class Weapon extends Item {
	private int damage;
	private int costEnergy;

	public Weapon(String name, String detail, int damage, int costEnergy, int width) {
		super(name, detail, width);
		this.damage = damage;
		this.costEnergy = costEnergy;
		System.out.println("weapon created");
	}

	public Weapon(String name, String detail, int damage, int costEnergy, int width, int height) {
		super(name, detail, width, height);
		this.damage = damage;
		this.costEnergy = costEnergy;
		System.out.println("weapon created");
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCostEnergy() {
		return costEnergy;
	}

	public void setCostEnergy(int costEnergy) {
		this.costEnergy = costEnergy;
	}
}
