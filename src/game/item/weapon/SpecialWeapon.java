package game.item.weapon;

import java.util.ArrayList;

import game.util.Effect;
import game.util.ItemTier;
import logic.FightLogic;

public class SpecialWeapon extends Weapon {
	final private ArrayList<Effect> effects;

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, damage, costActivate, width, height, tier);
		this.effects = effects;
	}

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, ItemTier tier) {
		super(name, detail, damage, costActivate, width, tier);
		this.effects = effects;
	}

	@Override
	public void activatePerClick() {
		super.activatePerClick();
		
		//Add effectType to enemy with effectPower amount;
		for (Effect effect:effects) {
			FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(), effect.getAmount());
		}
	}
	
	@Override
	public String toString() {
		String text = getProvide();
		for (Effect effect : effects) {
			text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to target\n";
		}
		
		return text;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
