package game.item.weapon;

import entities.Player;
import game.item.Item;
import game.util.ItemTier;
import interfaces.Clickable;
import interfaces.ReStatable;
import logic.FightLogic;

public class Weapon extends Item implements Clickable, ReStatable{
	final private int initDamage;
	private int damage;
	private int costActivate;
	
	public Weapon(String name, String detail, int initDamage, int costActivate, int width, ItemTier tier) {
		super(name, detail, width, tier);
		setDamage(initDamage);
		this.initDamage = initDamage;
	}

	public Weapon(String name, String detail, int initDamage, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		setDamage(initDamage);
		this.initDamage = initDamage;
	}
	
	@Override
	public void reStatBeforeUpdate() {
		setDamage(initDamage);
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
	
	//For print only
	protected String getProvide() {
		String text=getName()+" is "+getTierName()+" weapon\n"
				+ "When click :\n"
				+ "Damage target : "+initDamage;
		if (damage>initDamage) text=text+" + "+(damage-initDamage);
		else if (damage<initDamage) text=text+" - "+(initDamage-damage);
		
		return text+" DAMAGE\n";
	}
	
	@Override
	public String toString() {
		return getProvide()+"\nCost "+costActivate+" energy per click";
	}
	
	
	//Getter & Setter
	public void addDamage(int damage) {
		setDamage(getDamage()+damage);
	}
	
	public int getInitDamage() {
		return initDamage;
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
