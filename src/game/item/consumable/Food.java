package game.item.consumable;

import game.util.EffectType;

public class Food extends Consumable {
	private int durability;
	
	public Food(String name, String detail, int durability, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, effectType, effectPower, width, height);
		setDurability(durability);
	}

	public Food(String name, String detail, int durability, EffectType effectType, int effectPower, int height) {
		super(name, detail, effectType, effectPower, height);
		setDurability(durability);
	}
	
	@Override
	public void activatePerClick() {
		durability--;
		if (getEffectType()==EffectType.HEAL) {
			//TODO: heal self by getEffectPower()
			
		}
		else if (getEffectType()==EffectType.ENERGY) {
			//TODO: increase enegy by getEffectPower()
		}
		
		if (durability<=0) {
			//TODO: Delete this item
			
			if (this instanceof FoodWithContainer) {
				//TODO: replace this item with item container
			}
		}
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = (durability<0)? 0 : durability;
	}
}
