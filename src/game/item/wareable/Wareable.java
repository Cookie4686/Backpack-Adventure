package game.item.wareable;

import java.util.ArrayList;

import entities.Player;
import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.ReStatable;
import interfaces.StatUpdatable;
import interfaces.TurnActivable;
import logic.FightLogic;

public abstract class Wareable extends Item implements TurnActivable, StatUpdatable, ReStatable{
	final private ArrayList<Effect> effects;
	final private int initialShield;
	private int shield;
	private int increaseShield;
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
		this.effects = effects;
	}
	
	@Override
	public void reStatBeforeUpdate() {
		setShield(initialShield);
	}
	
	@Override
	public void statUpdate() {
		for (Effect effect:effects) {
			if (effect.getType()==EffectType.LUCK) {
				Player.getInstance().setLuck(Player.getInstance().getLuck()+effect.getAmount());
			}
			else if (effect.getType()==EffectType.HEAL) {
				Player.getInstance().setMaxHp(Player.getInstance().getMaxHp()+effect.getAmount());
			}
			else if (effect.getType()==EffectType.ENERGY) {
				Player.getInstance().setMaxEnergy(Player.getInstance().getMaxEnergy()+effect.getAmount());
			}
			else if (effect.getType()==EffectType.DODGE) {
				FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), EffectType.DODGE, effect.getAmount());
			}
		}
	}
	
	@Override
	public void activatePerTurn() {
		for (Effect effect:effects) {
			if (effect.getType()!=EffectType.LUCK && effect.getType()!=EffectType.DODGE && effect.getType()!=EffectType.HEAL && effect.getType()!=EffectType.ENERGY) {
				FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount());
			}
		}
		
		Player.getInstance().setShield(Player.getInstance().getShield()+getShield());
	}
	
	
	//Getter & Setter
	public ArrayList<Effect> getEffects() {
		return effects;
	}
	
	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = (shield<0)? 0 : shield;
	}

	public int getInitialShield() {
		return initialShield;
	}

	public void setIncreaseShield(int increaseShield) {
		this.increaseShield = (increaseShield<0)? 0 : increaseShield;
	}


	public int getIncreaseShield() {
		return increaseShield;
	}
}
