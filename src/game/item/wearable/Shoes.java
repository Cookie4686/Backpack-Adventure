package game.item.wearable;

import java.util.ArrayList;

import game.backpack.Backpack;
import game.util.Effect;
import game.util.Position;
import game.util.ItemTier;

public class Shoes extends Wearable {
	public Shoes(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, initialShield, increaseShield, effects, width, height, tier);
	}

	private int emptySpace() {	
		//Find number of empty space above
		Backpack backpack = Backpack.getInstance();
		
		int freeSpace=0;
		for (Position itemPostion : backpack.getItemPosition(this)) {
			int x = itemPostion.getX();
			for (int y=itemPostion.getY()-1 ; y>=0 ; y--) {
				if (backpack.getSlots()[y][x].getItem()==null && backpack.getSlots()[y][x].isUnlocked()) freeSpace++;
			}
		}
		
		return freeSpace;
	}
	
	private boolean isLowest() {
		Backpack backpack = Backpack.getInstance();
		
		for (Position itemPostion : backpack.getItemPosition(this)) {
			int y=itemPostion.getY()+1;
			if (y==Backpack.HEIGHT) return true;
			for (int x=0 ; x<Backpack.WIDTH ; x++) {
				if (backpack.getSlots()[y][x].isUnlocked()) break;
				
				if (x==Backpack.WIDTH-1) return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void statUpdate() {
		if (isLowest()) setShield(getShield() + (emptySpace() * getIncreaseShield()));
		super.statUpdate();
	}
	
	@Override
	public String toString() {
		return getProvide()+"\nThis need to be LOWEST for "+getIncreaseShield()+" extra SHIELD per empty space above\n"
				+ "Activate when in backpack";
	}
}
