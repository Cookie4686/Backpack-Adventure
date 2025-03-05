package game.item;

import entities.Player;
import game.util.ItemTier;
import interfaces.Clickable;
import sound.Sfx;
import sound.SfxPlayer;

public abstract class ItemWithCost extends Item implements Clickable {
	protected int costActivate;

	public ItemWithCost(String name, String detail, int costActivate, int height, ItemTier tier) {
		super(name, detail, height, tier);
		setCostActivate(costActivate);
	}

	public ItemWithCost(String name, String detail, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		setCostActivate(costActivate);
	}

	@Override
	public boolean isEnoughEnergy() {
		return Player.getInstance().getEnergy() >= costActivate;
	}

	@Override
	public void activatePerClick() {
		if (isEnoughEnergy()) {
			activateItem();
		} else {
			System.out.println("Not enough energy");
			SfxPlayer.play(Sfx.DENY);
		}
	}

	public abstract void activateItem();

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
