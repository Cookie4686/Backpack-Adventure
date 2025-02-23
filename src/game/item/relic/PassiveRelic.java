package game.item.relic;

import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.StatUpdatable;

public class PassiveRelic extends Relic implements StatUpdatable {
	public PassiveRelic(String name, String detail, Effect effect, int range, int width, int height, ItemTier tier) {
		super(name, detail, effect, range, width, height, tier);
	}

	public PassiveRelic(String name, String detail, Effect effect,int range, int height, ItemTier tier) {
		super(name, detail, effect, range, height, tier);
	}
	
	@Override
	public void statUpdate() {
		super.activate();
	}
	
	@Override
	public String toString() {
		String text = getProvide()+"Provide :\n";
		if (isDiagonal) {
			if (getEffectType()==EffectType.SHIELD) {
				text=text+"Add "+getEffectAmount()+" SHIELD to apparel at "+getRange()+" diagonal slot away";
			}
			if (getEffectType()==EffectType.DAMAGE) {
				text=text+"Add  "+getEffectAmount()+" DAMAGE to weapon at "+getRange()+" diagonal slot away";
			}
		} else {
			if (getEffectType()==EffectType.SHIELD) {
				text=text+"Add "+getEffectAmount()+" SHIELD to apparel at "+getRange()+" beside slot away";
			}
			if (getEffectType()==EffectType.DAMAGE) {
				text=text+"Add  "+getEffectAmount()+" DAMAGE to weapon at "+getRange()+" beside slot away";
			}
		}
		
		return text+"\nActivate when in backpack";
	}
}
