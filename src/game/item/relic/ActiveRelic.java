package game.item.relic;

import game.util.Effect;
import interfaces.Clickable;

public class ActiveRelic extends Relic implements Clickable {
	public ActiveRelic(String name, String detail, Effect effect, int width, int height) {
		super(name, detail, effect, width, height);
	}

	public ActiveRelic(String name, String detail, Effect effect, int height) {
		super(name, detail, effect, height);
	}
	
	@Override
	public void activatePerClick() {
		// TODO Auto-generated method stub
		
	}
}
