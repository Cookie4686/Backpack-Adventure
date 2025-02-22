package game.item.relic;

import game.item.Item;
import game.util.Effect;
import game.util.EffectType;

public abstract class Relic extends Item {
	final private Effect effect;
	
	public Relic(String name, String detail, Effect effect, int width, int height) {
		super(name, detail, width, height);
		this.effect = effect;
	}

	public Relic(String name, String detail, Effect effect, int height) {
		super(name, detail, height);
		this.effect = effect;
	}
	
	public void activate() {
		switch (getEffectType()) {
		case SHIELD:
			// TODO: Find adjacent shield & wareable 
			// add them shield
			break;
		case DAMAGE:
			// TODO: Find adjacent weapon
			// add them damage
			break;
		default:
			break;
		}
	}
	
	//Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getAmount();
	}
}
