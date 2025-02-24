package game.item.wareable;

import java.util.ArrayList;

import game.util.Effect;
import game.util.ItemTier;

public class Armor extends Wareable {
	public Armor(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) {
		super(name, detail, initialShield, increaseShield, effects, width, height, tier);
	}

	
	private int numberOfAdjacent() {
		//TODO: return each Wareable type adjacent to this
		
		return 0;
	}
	
	@Override
	public void statUpdate() {
		super.statUpdate();
		
		setShield(getShield() + (numberOfAdjacent() * getIncreaseShield()));
	}
}
