package game.item.relic;

import game.util.Effect;
import interfaces.StatUpdatable;

public class PassiveRelic extends Relic implements StatUpdatable {
	public PassiveRelic(String name, String detail, Effect effect, int width, int height) {
		super(name, detail, effect, width, height);
	}

	public PassiveRelic(String name, String detail, Effect effect, int height) {
		super(name, detail, effect, height);
	}
	
	@Override
	public void statUpdate() {
		super.activate();
	}
}
