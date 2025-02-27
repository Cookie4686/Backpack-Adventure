package game.item.wearable;

import java.util.ArrayList;

import game.backpack.Backpack;
import game.util.Effect;
import game.util.ItemPosition;
import game.util.ItemTier;

public class Helmet extends Wearable {
	public Helmet(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, initialShield, increaseShield, effects, width, height, tier);
	}

	
	private int underSlot() {	
		//Find number of row under
		Backpack backpack = Backpack.getInstance();
		ArrayList<ItemPosition> itemPostions = backpack.getItemPosition(this);
		int x=itemPostions.get(0).getX();
		
		int count=0;
		for (int y=itemPostions.get(0).getY() ; y<Backpack.HEIGHT ; y++) {
			if (backpack.getSlots()[y][x].isUnlocked()) count++;
		}
		
		return count;
	}
	
	@Override
	public void statUpdate() {
		super.statUpdate();
		
		setShield(getShield() + (underSlot() * getIncreaseShield()));
	}
	
	
	@Override
	public String toString() {
		return getProvide()+"\nGet "+getIncreaseShield()+" extra SHIELD per row below\n"
				+ "Activate when in backpack";
	}
}
