package game.item;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.ItemTier;
import logic.FightLogic;

public class ShieldItem extends ItemWithCost {
	final private ArrayList<Effect> effects;
	private int shield;

	public ShieldItem(String name, String detail, int shield, ArrayList<Effect> effects, int costActivate, int width,
			int height, ItemTier tier) {
		super(name, detail, costActivate, width, height, tier);
		this.shield = shield < 0 ? 0 : shield;
		this.effects = effects;
	}

	@Override
	public void activateItem() {
		System.out.println("Use " + getName());
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		for (Effect effect : effects) {
			FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount(),
					Player.getInstance());
		}
		Player.getInstance().setShield(Player.getInstance().getShield() + shield);
	}

	@Override
	public String toString() {
		String text = getName() + " is " + getTierName() + " apparel\n" + "Provide :\n" + "Add shield : " + shield
				+ " SHIELD\n";
		for (Effect effect : effects) {
			text = text + "Add " + effect.getAmount() + " " + effect.getTypeName() + " to Player\n";
		}
		return text + "\nCost " + costActivate + " energy per click";
	}
}
