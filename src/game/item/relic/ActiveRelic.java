package game.item.relic;

import entities.Player;
import game.util.Effect;
import interfaces.Clickable;

public class ActiveRelic extends Relic implements Clickable {
	private int costActivate;
	
	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int width, int height) {
		super(name, detail, effect, width, height);
	}

	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int height) {
		super(name, detail, effect, height);
	}
	
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy()<costActivate) return false;
		return true;
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		
		super.activate();
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
