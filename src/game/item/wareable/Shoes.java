package game.item.wareable;

import java.util.ArrayList;

import game.util.Effect;

public class Shoes extends Wareable {
	public Shoes(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height) {
		super(name, detail, initialShield, increaseShield, effects, width, height);
	}

	public Shoes(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width) {
		super(name, detail, initialShield, increaseShield, effects, width);
	}
	
	private int emptySpace() {	
		//return number of empty space above
		return 0;
	}
	
	@Override
	public void statUpdate() {
		super.statUpdate();
		
		//TODO: need to be lowest otherwise no bonus shield
		
		//Set bonus shield
		setShield(getShield() + (emptySpace() * getIncreaseShield()));
	}
}
