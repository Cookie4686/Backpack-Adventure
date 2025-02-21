package game.item.wareable;

import game.util.EffectType;

public class AdvanceArmor extends Armor {
	private EffectType initEffectType;
	private int initEffectPower;
	
	public AdvanceArmor(String name, String detail, int initialShield, int increaseShield, EffectType initEffectType, int initEffectPower, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width, height);
		// TODO Auto-generated constructor stub
	}

	public AdvanceArmor(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void activateStart() {
		super.activateStart();
		
		//TODO : add initEffectType and initEffectPower to PLAYER
	}

	public EffectType getInitEffectType() {
		return initEffectType;
	}

	public int getInitEffectPower() {
		return initEffectPower;
	}

	public void setInitEffectType(EffectType initEffectType) {
		this.initEffectType = initEffectType;
	}

	public void setInitEffectPower(int initEffectPower) {
		this.initEffectPower = (initEffectPower<0)? 0 : initEffectPower;
	}
	
	
}
