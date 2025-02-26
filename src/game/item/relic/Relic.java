package game.item.relic;

import java.util.ArrayList;

import entities.Player;
import game.backpack.Backpack;
import game.backpack.Slot;
import game.item.Item;
import game.item.wareable.Wareable;
import game.item.weapon.Weapon;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemPosition;
import game.util.ItemTier;

public abstract class Relic extends Item {
	final private Effect effect;
	final protected int range;
	
	public Relic(String name, String detail, Effect effect, int range, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.effect = effect;
		this.range = range;
	}

	public Relic(String name, String detail, Effect effect, int range, int height, ItemTier tier) {
		super(name, detail, height, tier);
		this.effect = effect;
		this.range = range;
	}
	
	
	private void increaseShield(ArrayList<Item> items) {
		if (this instanceof ActiveRelic) {
			Player.getInstance().setShield(Player.getInstance().getShield() + effect.getAmount());
			return;
		}
		
		for (Item item : items) {
			if (item instanceof Wareable) {
				((Wareable)item).addShield(effect.getAmount());
			}
		}
	}
	
	private void increaseDamage(ArrayList<Item> items) {
		for (Item item : items) {
			if (item instanceof Weapon) {
				((Weapon)item).addDamage(effect.getAmount());
			}
		}
	}
	
	private ArrayList<Item> findItemInSlot() {
		ItemPosition position = Backpack.getInstance().getItemPosition(this).get(0);
		Slot[][] slots = Backpack.getInstance().getSlots();
		ArrayList<Item> items = new ArrayList<Item>();
		
		if (isDiagonal) {
			if (position.getY()-range>=0 && position.getX()-range>=0) {				//North Wast
				items.add(slots[position.getY()-range][position.getX()-range].getItem());
			}
			if (position.getY()-range>=0 && position.getX()+1<Backpack.WIDTH) {		//North East
				items.add(slots[position.getY()-range][position.getX()+range].getItem());
			}
			if (position.getY()+1<Backpack.HEIGHT && position.getX()-range>=0) {	//South Wast
				items.add(slots[position.getY()+range][position.getX()-range].getItem());
			}
			if (position.getY()+1<Backpack.HEIGHT && position.getX()+1<Backpack.WIDTH) {//south East
				items.add(slots[position.getY()+range][position.getX()+range].getItem());
			}
		}
		else {
			if (position.getY()-range>=0) 			//North
				items.add(slots[position.getY()-range][position.getX()].getItem());
			
			if (position.getY()+range<Backpack.HEIGHT) 	//South
				items.add(slots[position.getY()+range][position.getX()].getItem());
			
			if (position.getX()-range>=0) 				//Wast
				items.add(slots[position.getY()][position.getX()-range].getItem());
			
			if (position.getX()+range<Backpack.WIDTH) 	//East
				items.add(slots[position.getY()][position.getX()+range].getItem());
		}
		
		return items;
	}
	
	public void activate() {
		switch (getEffectType()) {
		case SHIELD:
			increaseShield(findItemInSlot());
			break;
		case DAMAGE:
			increaseDamage(findItemInSlot());
			break;
		default:
			break;
		}
	}
	
	
	protected String getProvide() {	
		return getName()+" is "+getTierName();
	}
	
	
	//Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getAmount();
	}

	public int getRange() {
		return range;
	}
}
