package game.item.consumable;

import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.Clickable;

public class Consumable extends Item implements Clickable {
	final private Effect effect;
	private int durability;

	public Consumable(String name, String detail, int durability, Effect effect, int width, int height) {
		super(name, detail, width, height);
		this.effect = effect;
		setDurability(durability);
	}

	public Consumable(String name, String detail, int durability, Effect effect, int height) {
		super(name, detail, height);
		this.effect = effect;
		setDurability(durability);
	}
	
	
	@Override
	public void activatePerClick() {
		setDurability(getDurability()-1);
		
		
		if (effect.getType()==EffectType.DAMAGE) {
			//TODO: damage enemy by effectPower
		}
		else if (effect.getType()==EffectType.HEAL) {
			//TODO: heal self by effectPower
		}
		else if (effect.getType()==EffectType.ENERGY) {
			//TODO: increase enegy by effectPower
		}
		else if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
			//TODO: add effectType to enemy by effectPower amount
		}
		else if (effect.getType()==EffectType.THORN || effect.getType()==EffectType.ANGER || effect.getType()==EffectType.DODGE) {
			//TODO: add effectType to self by effectPower amount
		}
		
		if (getDurability()<=0) {
			//TODO: Delete this item
			
			if (this instanceof FoodWithContainer) {
				//TODO: replace this item with item container
			}
		}
	}
	
	
	//Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getDuration();
	}
	
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = (durability<0)? 0 : durability;
	}
}
