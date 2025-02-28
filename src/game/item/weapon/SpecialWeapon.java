package game.item.weapon;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
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
		if (!isEnoughEnergy()) return;
		
		//Add effectType to enemy with effectPower amount;
		for (Effect effect:effects) {
			if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(), effect.getAmount());
			}
			else if (effect.getType()==EffectType.HEAL) {
				Player.getInstance().setMaxHp(Player.getInstance().getMaxHp() + effect.getAmount());
			}
			else if (effect.getType()==EffectType.VAMPIRIC) {
				Player.getInstance().setHp(Player.getInstance().getHp() + (getDamage() * effect.getAmount()/100));
			}
			else if (effect.getType()==EffectType.LUCK) {
				Player.getInstance().setLuck(Player.getInstance().getLuck() + effect.getAmount());
			}
			else if (effect.getType()==EffectType.SHIELD) {
				Player.getInstance().setShield(Player.getInstance().getShield() + effect.getAmount());
			}
			else {
				FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount());
			}
		}
	}
	
	@Override
	public String getProvide() {
		String text = super.getProvide();
		for (Effect effect : effects) {
			if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to target\n";
			}
			else if (effect.getType()==EffectType.HEAL) {
				text=text+"Add Player "+effect.getAmount()+" MaxHealth (Reset after battle)\n";
			}
			else if (effect.getType()==EffectType.VAMPIRIC) {
				text=text+"Heal Player "+(getDamage()*effect.getAmount()/100)+" Health ("+effect.getAmount()+"% of damage)\n";
			}
			else if (effect.getType()==EffectType.LUCK) {
				text=text+"Add "+effect.getAmount()+" Luck to player\n";
			}
			else if (effect.getType()==EffectType.SHIELD) {
				text=text+"Add "+effect.getAmount()+" SHIELD to player\n";
			}
			else {
				text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to Player\n";
			}
		}
		return text;
	}
	
	@Override
	public String toString() {
		return getProvide()+"\nCost "+getCostActivate()+" energy per click";
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
