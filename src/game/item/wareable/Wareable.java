package game.item.wareable;

import game.item.Item;
import game.util.EffectType;
import interfaces.TurnActivable;

public abstract class Wareable extends Item implements TurnActivable{
	final private int initialShield;
	private int shield;
	private int increaseShield;
	private EffectType effectType;
	private int effectPower;
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, width, height);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}
	
	public Wareable(String name, String detail, int initialShield, int increaseShield,  EffectType effectType, int effectPower, int width) {
		super(name, detail, width);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}
	
	
	public abstract void activateStart();
	
	//Getter & Setter
	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = (shield<0)? 0 : shield;
	}

	public int getInitialShield() {
		return initialShield;
	}

	public void setIncreaseShield(int increaseShield) {
		this.increaseShield = (increaseShield<0)? 0 : increaseShield;
	}
	
	public EffectType getEffectType() {
		return effectType;
	}

	public int getEffectPower() {
		return effectPower;
	}

	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}

	public void setEffectPower(int effectPower) {
		this.effectPower = (effectPower < 0) ? 0 : effectPower;
	}
	
}
