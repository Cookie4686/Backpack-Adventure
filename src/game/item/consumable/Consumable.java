package game.item.consumable;

import game.item.Item;
import game.util.EffectType;
import interfaces.Clickable;

public abstract class Consumable extends Item implements Clickable {
	private EffectType effectType;
	private int effectPower;

	public Consumable(String name, String detail, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, width, height);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}

	public Consumable(String name, String detail, EffectType effectType, int effectPower, int height) {
		super(name, detail, height);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}
	
	
	@Override
	public void activatePerClick() {
		if (effectType==EffectType.DAMAGE) {
			//TODO: damage enemy by effectPower
		}
		else if (effectType==EffectType.HEAL) {
			//TODO: heal self by effectPower
		}
		else if (effectType==EffectType.ENERGY) {
			//TODO: increase enegy by effectPower
		}
		else if (effectType==EffectType.FIRE || effectType==EffectType.POISON || effectType==EffectType.STUNTED) {
			//TODO: add effectType to enemy by effectPower amount
		}
	}

	
	//Getter & Setter
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
		this.effectPower = (effectPower<0)? 0 : effectPower;
	}
}
