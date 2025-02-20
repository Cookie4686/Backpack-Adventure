package game.item;

import interfaces.Clickable;

public class Container extends Item implements Clickable{
	private int damage;
	
	public Container(String name, String detail, int damage, int width, int height) {
		super(name, detail, width, height);
		setDamage(damage);
	}

	public Container(String name, String detail, int damage, int height) {
		super(name, detail, height);
		setDamage(damage);
	}
	
	@Override
	public void activatePerClick() {
		//TODO: damage enemy by damage
		// delete this item
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
	
	
}
