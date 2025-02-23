package game.item.consumable;

import game.item.Item;
import game.util.ItemTier;
import interfaces.Clickable;
import logic.FightLogic;

public class Container extends Item implements Clickable{
	private int damage;
	
	public Container(String name, String detail, int damage, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		setDamage(damage);
	}

	public Container(String name, String detail, int damage, int height, ItemTier tier) {
		super(name, detail, height, tier);
		setDamage(damage);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		return true;
	}
	
	
	@Override
	public void activatePerClick() {
		FightLogic.getInstance().getTarget().takeDamage(damage);
		delete();
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
	
	
}
