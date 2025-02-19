package game.item;

import interfaces.TurnActivable;

public class Helmet extends Wareable implements TurnActivable {
	private EffectType effectType;
	private int effectPower;

	public Helmet(String name, String detail, int startShield, int increase, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, startShield, increase, width, height);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}

	public Helmet(String name, String detail, int startShield, int increase, EffectType effectType, int effectPower, int width) {
		super(name, detail, startShield, increase, width);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}

	@Override
	public void activateStart() {
		setShield(getInitialShield() + increasePerAdjacent());
		
		// TODO: add Shield to Player
	}
	
	@Override
	public void activatePerTurn() {
		if (getEffectType() == null) {
			// TODO: add effectType and effectPower to PLAYER
		}
		
		// TODO: add Shield to Player
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
