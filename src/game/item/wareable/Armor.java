package game.item.wareable;

import java.util.ArrayList;

import game.backpack.Backpack;
import game.backpack.Slot;
import game.item.Item;
import game.util.Effect;
import game.util.ItemPosition;
import game.util.ItemTier;

public class Armor extends Wareable {
	public Armor(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, initialShield, increaseShield, effects, width, height, tier);
	}

	private boolean isMeetCondition(Item item, ArrayList<Item> items) {
		if (item instanceof Wareable) 
			if (!item.equals(this))
				if (!items.contains(item)) return true;
		return false;
	}
	
	private int numberOfAdjacent() {
		Backpack backpack = Backpack.getInstance();
		ArrayList<Item> items = new ArrayList<Item>();
		
		for (ItemPosition position : backpack.getItemPosition(this)) {
			Slot[][] slots = backpack.getSlots();
			if (position.getY()-1>=0) //North
				if (isMeetCondition(slots[position.getY()-1][position.getX()].getItem(), items))
					items.add(slots[position.getY()-1][position.getX()].getItem());
			
			if (position.getY()+1<Backpack.HEIGHT) //South
				if (isMeetCondition(slots[position.getY()+1][position.getX()].getItem(), items))
					items.add(slots[position.getY()+1][position.getX()].getItem()); 
			
			if (position.getX()-1>=0) //Wast
				if (isMeetCondition(slots[position.getY()][position.getX()-1].getItem(), items))
					items.add(slots[position.getY()][position.getX()-1].getItem());
			
			if (position.getX()+1<Backpack.WIDTH) //East
				if (isMeetCondition(slots[position.getY()][position.getX()+1].getItem(), items))
					items.add(slots[position.getY()][position.getX()+1].getItem()); 
		}
		
		return items.size();
	}
	
	
	@Override
	public void statUpdate() {
		super.statUpdate();
		
		setShield(getShield() + (numberOfAdjacent() * getIncreaseShield()));
	}
	
	
	@Override
	public String toString() {
		return getProvide()+"\nGet "+getIncreaseShield()+" extra SHIELD per adjacent apparel\n"
				+ "Activate when in backpack";
	}
}
