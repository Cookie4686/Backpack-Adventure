package game.item.relic;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.Clickable;
import interfaces.TurnActivable;
import sound.Sfx;
import sound.SfxPlayer;

public class ActiveRelic extends Relic implements Clickable, TurnActivable {
	private int costActivate;
	private boolean isUsed;

	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int width, int height,
			ItemTier tier) {
		super(name, detail, effect, range, width, height, tier);
		setCostActivate(costActivate);
		isUsed = false;
	}

	public ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int height,
			ItemTier tier) {
		super(name, detail, effect, range, height, tier);
		setCostActivate(costActivate);
		isUsed = false;
	}

	@Override
	public boolean isEnoughEnergy() {
		return Player.getInstance().getEnergy() >= costActivate && !isUsed;
	}

	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) {
			System.out.println("Not enough energy");
			SfxPlayer.play(Sfx.DENY);
			return;
		}
		System.out.println("Use " + getName());
		SfxPlayer.play(Sfx.SELECT);
		isUsed = true;
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);

		super.activate();
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}

	@Override
	public void activatePerTurn() {
		isUsed = false;
	}

	@Override
	public String toString() {
		String text = getProvide() + " Active Relic\nCan use once per turn\nOn use :\n";
		if (isDiagonal) {
			if (getEffectType() == EffectType.SHIELD) {
				text = text + "Add " + getEffectAmount() + " SHIELD to player per apparel at " + getRange()
						+ " diagonal slot away\n";
			}
			if (getEffectType() == EffectType.DAMAGE) {
				text = text + "Add  " + getEffectAmount() + " DAMAGE to weapon at " + getRange()
						+ " diagonal slot away\n";
			}
		} else {
			if (getEffectType() == EffectType.SHIELD) {
				text = text + "Add " + getEffectAmount() + " SHIELD to player per apparel at " + getRange()
						+ " beside slot away\n";
			}
			if (getEffectType() == EffectType.DAMAGE) {
				text = text + "Add  " + getEffectAmount() + " DAMAGE to weapon at " + getRange()
						+ " beside slot away\n";
			}
		}

		return text + "\nCost " + getCostActivate() + " energy per click";
	}
}
