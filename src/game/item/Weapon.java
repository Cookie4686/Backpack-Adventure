package game.item;

public abstract class Weapon extends Item {
	private int damage;
	
	public Weapon(String name, String detail, int damage, int costEnergy, int width) {
		super(name, detail, width);
		setDamage(damage);
	}

	public Weapon(String name, String detail, int damage, int costEnergy, int width, int height) {
		super(name, detail, width, height);
		setDamage(damage);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = (damage<0)? 0 : damage;
	}
	
	
//	if (getEffectType() == EffectType.FIRE || getEffectType() == EffectType.POISON || getEffectType() == EffectType.STUNTED) {
//		// TODO: Fill effectType and effectPower to Enemy
//	}
}
