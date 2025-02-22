package game.item.weapon;

import entities.Player;
import game.item.Item;
import interfaces.Clickable;
import interfaces.ReStatable;
import logic.FightLogic;

public class Weapon extends Item implements Clickable, ReStatable{
	final private int initdamage;
	private int damage;
	private int costActivate;
	
	public Weapon(String name, String detail, int initdamage, int costActivate, int width) {
		super(name, detail, width);
		setDamage(initdamage);
		this.initdamage = initdamage;
	}

	public Weapon(String name, String detail, int initdamage, int costActivate, int width, int height) {
		super(name, detail, width, height);
		setDamage(initdamage);
		this.initdamage = initdamage;
	}
	
	@Override
	public void reStatBeforeUpdate() {
		setDamage(initdamage);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy()<costActivate) return false;
		return true;
	}

	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		//decrease player energy by costActivate
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		
		//damage enemy getDamage() amount
		FightLogic.getInstance().getTarget().takeDamage(damage);
	}
	
	
	
	//Getter & Setter
	public int getInitdamage() {
		return initdamage;
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
