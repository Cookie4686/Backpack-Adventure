package game.item.consumable;

import game.util.Effect;
import game.util.EffectType;

public class Potion extends Consumable{
	private boolean AoE;

	public Potion(String name, String detail, int durability, Effect effect, boolean isAoE, int width, int height) {
		super(name, detail, durability, effect, width, height);
		setAoe(AoE);
	}

	public Potion(String name, String detail, int durability, Effect effect, boolean isAoE, int height) {
		super(name, detail, durability, effect, height);
		setAoe(AoE);
	}
	
	@Override
	public void activatePerClick() {
		if (isAoE()) {
			setDurability(getDurability()-1);
			
			if (getEffectType()==EffectType.DAMAGE) {
				//TODO: damage all enemy by effectPower
			}
			else if (getEffectType()==EffectType.FIRE || getEffectType()==EffectType.POISON || getEffectType()==EffectType.STUNTED) {
				//TODO: add effectType to all enemy by effectPower amount
			}
			
			if (getDurability()<=0) {
				//TODO: Delete this item
			}
		}
		else {
			super.activatePerClick();
		}
	}

	public boolean isAoE() {
		return AoE;
	}

	public void setAoe(boolean isAoe) {
		this.AoE = isAoe;
	}
}
