package game.item.wareable;

import java.util.ArrayList;

import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.StatUpdatable;
import interfaces.TurnActivable;

public abstract class Wareable extends Item implements TurnActivable, StatUpdatable{
	ArrayList<Effect> effects;
	final private int initialShield;
	private int shield;
	private int increaseShield;
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height) {
		super(name, detail, width, height);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
		this.effects = effects;
	}
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width) {
		super(name, detail, width);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
		this.effects = effects;
	}
	
	@Override
	public void StatUpdate() {
		for (Effect effect:effects) {
			if (effect.getType()==EffectType.LUCK) {
				//TODO: increase luck in player by effect power
			}
			else if (effect.getType()==EffectType.DODGE) {
				//TODO: increase dodge in player by effect power
			}
			else if (effect.getType()==EffectType.HEAL) {
				//TODO: increase health in player by effect power
			}
		}
	}
	
	@Override
	public void activatePerTurn() {
		for (Effect effect:effects) {
			if (effect.getType()!=null) {
				// TODO: add effectType and effectPower to PLAYER
			}
			
			// TODO: add Shield to Player
		}
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
