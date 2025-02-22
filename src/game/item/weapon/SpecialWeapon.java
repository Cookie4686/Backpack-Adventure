package game.item.weapon;

import java.util.ArrayList;

import game.util.Effect;
import logic.FightLogic;

public class SpecialWeapon extends Weapon {
	final private ArrayList<Effect> effects;

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, int height) {
		super(name, detail, damage, costActivate, width, height);
		this.effects = effects;
	}

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width) {
		super(name, detail, damage, costActivate, width);
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
	
	

	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
