package game.item;

import interfaces.Clickable;

public class DamageItem extends Item implements Clickable {
	private int damage;
	private boolean AoE;
	
	public DamageItem(String name, String detail, int damage, boolean isAoE, int width, int height) {
		super(name, detail, width, height);
		setDamage(damage);
		setAoE(AoE);
	}

	public DamageItem(String name, String detail, int damage, boolean isAoE, int height) {
		super(name, detail, height);
		setDamage(damage);
		setAoE(AoE);
	}
	
	@Override
	public void activatePerClick() {
		// TODO Auto-generated method stub
		
	}

	
	//Getter & Setter
	public int getDamage() {
		return damage;
	}

	public boolean isAoE() {
		return AoE;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setAoE(boolean aoE) {
		AoE = aoE;
	}
}
