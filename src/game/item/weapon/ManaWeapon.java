package game.item.weapon;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.ItemTier;
import logic.FightLogic;

public class ManaWeapon extends Weapon{
	final private ArrayList<Effect> effects;
	
	public ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, damage, costActivate, width, height, tier);
		this.effects = effects;
	}

	public ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, ItemTier tier) {
		super(name, detail, damage, costActivate, width, tier);
		this.effects = effects;
	}
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getMana() < getCostActivate()) return false;
		return true;
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		//decrease mana point in player by costActivate
		Player.getInstance().setMana(Player.getInstance().getMana() - getCostActivate());
		
		//damage enemy getDamage() amount
		FightLogic.getInstance().getTarget().takeDamage(getDamage());
		
		//Add effectType to enemy with effectPower amount;
		for (Effect effect:effects) {
			FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(), effect.getAmount());
		}
	}
	
	
	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
