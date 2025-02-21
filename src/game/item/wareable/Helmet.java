package game.item.wareable;

import java.util.ArrayList;

import game.util.Effect;

public class Helmet extends Wareable {
	public Helmet(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height) {
		super(name, detail, initialShield, increaseShield, effects, width, height);
	}

	public Helmet(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width) {
		super(name, detail, initialShield, increaseShield, effects, width);
	}
	
	private int underSlot() {	
		//return number of row under
		return 0;
	}
	
	@Override
	public void StatUpdate() {
		super.StatUpdate();
		//setShield(getInitialShield() + (underSlot() * increaseShield));
	}
}
