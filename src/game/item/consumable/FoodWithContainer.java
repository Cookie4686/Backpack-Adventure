package game.item.consumable;

import entities.Player;
import game.backpack.Backpack;
import game.itemGenerator.ResourceLoader;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import logic.FightLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class FoodWithContainer extends Consumable {
	String container;
	
	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int width,int height, ItemTier tier) {
		super(name, detail, durability, effect, costActivate, width, height, tier);
		setContainer(container);
	}
	
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) {
			System.out.println("Not enough enegry");
			SfxPlayer.play(Sfx.DENINE);
			return;
		}
		System.out.println("Use "+getName());
		
		SfxPlayer.play(Sfx.EAT);
		
		setDurability(getDurability()-1);
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - getCostActivate());
		
		if (getEffectType()==EffectType.HEAL) {
			Player.getInstance().setHp(Player.getInstance().getHp()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.ENERGY) {
			Player.getInstance().setEnergy(Player.getInstance().getEnergy()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.SHIELD) {
			Player.getInstance().setShield(Player.getInstance().getShield()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.LUCK) {
			Player.getInstance().setLuck(Player.getInstance().getLuck()+getEffectAmount());
		}
		else if (getEffectType()==EffectType.THORN || getEffectType()==EffectType.ANGER || getEffectType()==EffectType.DODGE) {
			FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), getEffectType(), getEffectAmount());
		}
		

		if (getDurability()<=0) {
			System.out.println("reset to "+container);
			Backpack.getInstance().replaceItem(this,ResourceLoader.newItem(container));
			delete();
		}
	}

	@Override
	protected String getHeader() {
		return getName()+" is "+getTierName()+" Drink\n"
				+ getDurability()+" use left"
				+ "\nWhen click :\n";
	}
	
	@Override
	public String toString() {
		return getHeader()+getProvideMid()+"\nWhen out of uses. Will be replace by "+container+
				"\nCost "+getCostActivate()+" ENERGY per click";
	}
	
	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
}
