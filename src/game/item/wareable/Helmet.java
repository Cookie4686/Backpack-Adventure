package game.item.wareable;

import java.util.ArrayList;

import game.util.Effect;
import game.util.ItemTier;

public class Helmet extends Wareable {
	public Helmet(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, initialShield, increaseShield, effects, width, height, tier);
	}

	
	private int underSlot() {	
		//TODO return number of row under
		return 0;
	}
	
	@Override
	public void statUpdate() {
		super.statUpdate();
		
		//setShield(getInitialShield() + (underSlot() * increaseShield));
		setShield(getShield() + (underSlot() * getIncreaseShield()));
	}
}
