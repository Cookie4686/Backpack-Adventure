package game.item.wareable;

import game.util.EffectType;

public class Helmet extends Wareable {

	public Helmet(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width, height);
	}

	public Helmet(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width);
	}
	
	private int underSlot() {	
		//return number of row under
		return 0;
	}

	@Override
	public void activateStart() {
		//setShield(getInitialShield() + (underSlot() * increaseShield));
		
		// TODO: add Shield to Player
	}
	
	@Override
	public void activatePerTurn() {
		if (getEffectType() != null) {
			// TODO: add effectType and effectPower to PLAYER
		}
		
		// TODO: add Shield to Player
	}
	
}
