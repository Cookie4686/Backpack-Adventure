package game.item.consumable;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import logic.FightLogic;

public class FoodWithContainer extends Consumable {
	String container;
	
	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int width,int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, width, height, tier);
		setContainer(container);
	}
	
	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, height, tier);
		setContainer(container);
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		setDurability(getDurability()-1);
		
		if (getEffectType()==EffectType.HEAL) {
			Player.getInstance().setHp(Player.getInstance().getHp()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.ENERGY) {
			Player.getInstance().setEnergy(Player.getInstance().getEnergy()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.THORN || getEffectType()==EffectType.ANGER || getEffectType()==EffectType.DODGE) {
			FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), getEffectType(), getEffectAmount());
		}
		

		if (getDurability()<=0) {
			//TODO : replace item
			delete();
		}
	}

	
	@Override
	public String toString() {
		return getProvide()+"When out of uses. Will be replace by "+container+
				"\nCost "+getCostActivate()+" ENERGY per click";
	}
	
	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
}
