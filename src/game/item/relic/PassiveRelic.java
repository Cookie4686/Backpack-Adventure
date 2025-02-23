package game.item.relic;

import game.util.Effect;
import game.util.ItemTier;
import interfaces.StatUpdatable;

public class PassiveRelic extends Relic implements StatUpdatable {
	public PassiveRelic(String name, String detail, Effect effect, int width, int height, ItemTier tier) {
		super(name, detail, effect, width, height, tier);
	}

	public PassiveRelic(String name, String detail, Effect effect, int height, ItemTier tier) {
		super(name, detail, effect, height, tier);
	}
	
	@Override
	public void statUpdate() {
		super.activate();
	}
}
