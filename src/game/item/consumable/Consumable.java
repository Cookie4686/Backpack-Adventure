package game.item.consumable;

import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.Clickable;

public class Consumable extends Item implements Clickable {
	final private Effect effect;
	private int costActivate;
	private int durability;

	public Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width, int height) {
		super(name, detail, width, height);
		this.effect = effect;
		setDurability(durability);
		setCostActivate(costActivate);
	}

	public Consumable(String name, String detail, int durability, Effect effect, int costActivate, int height) {
		super(name, detail, height);
		this.effect = effect;
		setDurability(durability);
		setCostActivate(costActivate);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		//TODO: if Player dont have enough energy return false
		return true;
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		setDurability(getDurability()-1);
		
		if (effect.getType()==EffectType.HEAL) {
			//TODO: heal self by effectPower
		}
		else if (effect.getType()==EffectType.ENERGY) {
			//TODO: increase enegy by effectPower
		}
		else if (effect.getType()==EffectType.THORN || effect.getType()==EffectType.ANGER || effect.getType()==EffectType.DODGE) {
			//TODO: add effectType to self by effectPower amount
		}
		

		if (getDurability()<=0) {
			delete();
		}
	}
	
	
	//Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getAmount();
	}
	
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability < 0 ? 0 : durability;
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
