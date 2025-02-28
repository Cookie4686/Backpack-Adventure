package game.item.weapon;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
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
			else {
				FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount());
			}
		}
	}
	
	
	@Override
	public String toString() {
		String text = getProvide();
		for (Effect effect : effects) {
			if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to target\n";
			}
			else if (effect.getType()==EffectType.REGEN) {
				text=text+"Heal Player "+effect.getAmount()+" Health\n";
			}
			else if (effect.getType()==EffectType.HEAL) {
				text=text+"Add Player "+effect.getAmount()+" MaxHealth (Reset after battle)\n";
			}
			else if (effect.getType()==EffectType.VAMPIRIC) {
				text=text+"Heal Player "+(getDamage()*effect.getAmount()/100)+" Health ("+effect.getAmount()+"% of damage)\n";
			}
			else if (effect.getType()==EffectType.LUCK) {
				text=text+"Add "+effect.getAmount()+" Luck to Player\n";
			}
			else {
				text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to Player\n";
			}
		}
		
		return text+"\nCost "+getCostActivate()+" mana per click";
	}
	
	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
