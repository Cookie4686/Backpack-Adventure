package game.item.weapon;

import game.util.EffectType;

public class SpecialWeapon extends Weapon {
	private EffectType effectType;
	private int effectPower;

	public SpecialWeapon(String name, String detail, EffectType effectType, int effectPower, int damage, int costActivate, int width, int height) {
		super(name, detail, damage, costActivate, width, height);
		setEffectPower(effectPower);
		setEffectType(effectType);
	}

	public SpecialWeapon(String name, String detail, EffectType effectType, int effectPower, int damage, int costActivate, int width) {
		super(name, detail, damage, costActivate, width);
		setEffectPower(effectPower);
		setEffectType(effectType);
	}

	@Override
	public void activatePerClick() {
		super.activatePerClick();
		
		//TODO: add effectType to enemy with effectPower amount;
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
