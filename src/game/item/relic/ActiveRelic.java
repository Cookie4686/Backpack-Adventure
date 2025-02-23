package game.item.relic;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.Clickable;

public class ActiveRelic extends Relic implements Clickable {
	private int costActivate;
	
	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int width, int height, ItemTier tier) {
		super(name, detail, effect, range, width, height, tier);
	}

	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int height, ItemTier tier) {
		super(name, detail, effect, range, height, tier);
	}
	
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy()<costActivate) return false;
		return true;
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		
		super.activate();
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
	
	
	@Override
	public String toString() {
		String text = getProvide()+"Can use once per turn\nOn use :\n";
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
		
		return text+"\nCost "+getCostActivate()+" energy per click";
	}
}
