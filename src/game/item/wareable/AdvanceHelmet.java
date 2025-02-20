package game.item.wareable;

import game.util.EffectType;

public class AdvanceHelmet extends Helmet {
	private EffectType initEffectType;
	private int initEffectPower;
	
	public AdvanceHelmet(String name, String detail, int initialShield, int increase, EffectType initEffectType, int initEffectPower, EffectType effectType, int effectPower,  int width, int height) {
		super(name, detail, initialShield, increase, effectType, effectPower, width, height);
		setInitEffectType(initEffectType);
		setInitEffectPower(initEffectPower);
	}
	public AdvanceHelmet(String name, String detail, int initialShield, int increase, EffectType initEffectType, int initEffectPower, EffectType effectType, int effectPower, int width) {
		super(name, detail, initialShield, increase, effectType, effectPower, width);
		setInitEffectType(initEffectType);
		setInitEffectPower(initEffectPower);
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
