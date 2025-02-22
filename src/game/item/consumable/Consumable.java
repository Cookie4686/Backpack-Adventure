package game.item.consumable;

import entities.Player;
import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.Clickable;
import logic.FightLogic;

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
		if (Player.getInstance().getEnergy()<costActivate) return false;
		return true;
	}
	
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		setDurability(getDurability()-1);
		
		if (effect.getType()==EffectType.HEAL) {
			Player.getInstance().setHp(Player.getInstance().getHp()+effect.getAmount());
		}
		else if (effect.getType()==EffectType.ENERGY) {
			Player.getInstance().setEnergy(Player.getInstance().getEnergy()+effect.getAmount());
		}
		else if (effect.getType()==EffectType.THORN || effect.getType()==EffectType.ANGER || effect.getType()==EffectType.DODGE) {
			FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount());
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
