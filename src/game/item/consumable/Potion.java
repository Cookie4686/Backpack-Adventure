package game.item.consumable;

import game.util.EffectType;

public class Potion extends Consumable{
	private boolean AoE;

	public Potion(String name, String detail, EffectType effectType, int effectPower, boolean isAoE, int width, int height) {
		super(name, detail, effectType, effectPower, width, height);
		setAoe(AoE);
	}

	public Potion(String name, String detail, EffectType effectType, boolean isAoE, int effectPower, int height) {
		super(name, detail, effectType, effectPower, height);
		setAoe(AoE);
	}
	
	@Override
	public void activatePerClick() {
		EffectType effect = getEffectType();
		
		if (effect==EffectType.HEAL) {
			//TODO: heal self by effectPower
		}
		else if (effect==EffectType.ENERGY) {
			//TODO: increase enegy by effectPower
		}
		else if (effect==EffectType.SHIELD) {
			//TODO: increase shield by effectPower
		}
		else if (effect==EffectType.DAMAGE) {
			if (isAoE()) {
				//TODO: damage all enemy by effectPower
			}
			else {
				//TODO: damage select enemy by effectPower
			}
		}
		else if (effect==EffectType.FIRE || effect==EffectType.POISON || effect==EffectType.STUNTED) {
			if (isAoE()) {
				//TODO: add effectType to all enemy by effectPower amount
			}
			else {
				//TODO: add effectType to select enemy by effectPower amount
			}
		}
		else if (effect==EffectType.THORN || effect==EffectType.ANGER || effect==EffectType.VAMPIRIC || effect==EffectType.DODGE) {
			//TODO: add effectType to self by effectPower amount
		}
	}

	public boolean isAoE() {
		return AoE;
	}

	public void setAoe(boolean isAoe) {
		this.AoE = isAoe;
	}
}
