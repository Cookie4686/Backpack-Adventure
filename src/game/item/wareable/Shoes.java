package game.item.wareable;

import game.util.EffectType;

public class Shoes extends Wareable {

	public Shoes(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width, height);
	}

	public Shoes(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width);
	}
	
	private int emptySpace() {	
		//return number of empty space above
		return 0;
	}
	
	@Override
	public void activateStart() {
		//need to be lowest otherwise no bonus shield
		//setShield(getInitialShield() + (emptyspace()  * increaseShield));
		
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
