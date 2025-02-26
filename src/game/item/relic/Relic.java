package game.item.relic;

import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;

public abstract class Relic extends Item {
	final private Effect effect;
	final protected int range;
	
	public Relic(String name, String detail, Effect effect, int range, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.effect = effect;
		this.range = range;
	}

	public Relic(String name, String detail, Effect effect, int range, int height, ItemTier tier) {
		super(name, detail, height, tier);
		this.effect = effect;
		this.range = range;
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
	
	public String getProvide() {	
		return getName()+" is "+getTierName()+" relic\n";
	}
	
	//Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getAmount();
	}

	public int getRange() {
		return range;
	}
}
