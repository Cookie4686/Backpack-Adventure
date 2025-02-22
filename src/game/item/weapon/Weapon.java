package game.item.weapon;

import game.item.Item;
import interfaces.Clickable;

public class Weapon extends Item implements Clickable{
	private int damage;
	private int costActivate;
	
	public Weapon(String name, String detail, int damage, int costActivate, int width) {
		super(name, detail, width);
		setDamage(damage);
	}

	public Weapon(String name, String detail, int damage, int costActivate, int width, int height) {
		super(name, detail, width, height);
		setDamage(damage);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		//TODO: if Player dont have enough energy return false
		return true;
	}

	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		//TODO: decrease player energy by costActivate
		//TODO: damage enemy getDamage() amount
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = (damage<0)? 0 : damage;
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate;
	}
}
