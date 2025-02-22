package game.item;

import game.util.Effect;
import game.util.EffectType;
import interfaces.Clickable;

public class DamageItem extends Item implements Clickable {
	final private boolean AoE;
	final private Effect effect;
	private int costActivate;
	
	public DamageItem(String name, String detail, Effect effect, int costActivate, boolean isAoE, int width, int height) {
		super(name, detail, width, height);
		this.effect = effect;
		this.AoE = isAoE;
		setCostActivate(costActivate);
	}

	public DamageItem(String name, String detail, Effect effect, int costActivate, boolean isAoE, int height) {
		super(name, detail, height);
		this.effect = effect;
		this.AoE = isAoE;
		setCostActivate(costActivate);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		//TODO: if Player dont have enough energy return false
		return true;
	}	
	
	@Override
	public void activatePerClick() {
		if (isAoE()) {
			if (getEffectType()==EffectType.DAMAGE) {
				//TODO: damage all enemy by effectPower
			}
			else if (getEffectType()==EffectType.FIRE || getEffectType()==EffectType.POISON || getEffectType()==EffectType.STUNTED) {
				//TODO: add effectType to all enemy by effectPower amount
			}
		}
		else {
			if (getEffectType()==EffectType.DAMAGE) {
				//TODO: damage select enemy by effectPower
			}
			if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				//TODO: add effectType to enemy by effectPower amount
			}
		}
			
	}

	
	//Getter & Setter
	public boolean isAoE() {
		return AoE;
	}

	public EffectType getEffectType() {
		return effect.getType();
	}
	
	public int getEffectAmount() {
		return effect.getAmount();
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
