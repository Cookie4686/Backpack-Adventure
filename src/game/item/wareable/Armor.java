package game.item.wareable;

import game.util.EffectType;

public class Armor extends Wareable {

	public Armor(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width, height);
	}

	public Armor(String name, String detail, int initialShield, int increaseShield, EffectType effectType, int effectPower, int width) {
		super(name, detail, initialShield, increaseShield, effectType, effectPower, width);
	}
	
	private int numberOfAdjacent() {
		//TODO: return each Wareable type adjacent to this
		
		return 0;
	}
	
	@Override
	public void activateStart() {
		//setShield(getInitialShield() + (numberOfAdjacent() * increaseShield));
		
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
