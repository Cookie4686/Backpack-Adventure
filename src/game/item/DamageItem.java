package game.item;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import logic.FightLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class DamageItem extends ItemWithCost {
	final private boolean AoE;
	final private ArrayList<Effect> effects;

	public DamageItem(String name, String detail, ArrayList<Effect> effects, int costActivate, boolean isAoE, int width,
			int height, ItemTier tier) {
		super(name, detail, costActivate, width, height, tier);
		this.AoE = isAoE;
		this.effects = effects;
	}

	@Override
	public void activateItem() {
		System.out.println("Use " + getName());
		Player.getInstance().attack();
		SfxPlayer.play(Sfx.THROW);
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		for (Effect effect : effects) {
			if (isAoE()) {
				for (Entity enemy : FightLogic.getInstance().getEntities()) {
					activateEffect(effect, enemy);
				}
			} else {
				activateEffect(effect, FightLogic.getInstance().getTarget());
			}
		}
		delete();
	}

	private void activateEffect(Effect effect, Entity enemy) {
		switch (effect.getType()) {
		case DAMAGE					-> { enemy.takeDamage(effect.getAmount()); }
		case FIRE, POISON, STUNNED	-> {
			FightLogic.findEffectAndAdd(enemy.getAllEffect(), effect.getType(), effect.getAmount(), enemy);
		}
		default						-> {}
		}
	}

	@Override
	public String toString() {
		String text = getName() + " is " + getTierName() + " throw weapon\n" + "Will gone after use\n"
				+ "When click :\n";

		for (Effect effect : effects) {
			if (effect.getType() == EffectType.DAMAGE) {
				if (isAoE()) {
					text = text + "Damage all enemy : " + effect.getAmount() + " DAMAGE\n";
				} else {
					text = text + "Damage target : " + effect.getAmount() + " DAMAGE\n";
				}
			} else if (effect.getType() == EffectType.FIRE || effect.getType() == EffectType.POISON
					|| effect.getType() == EffectType.STUNNED) {
				if (isAoE()) {
					text = text + "Add " + effect.getAmount() + " " + effect.getTypeName() + " to all enemy\n";
				} else {
					text = text + "Add " + effect.getAmount() + " " + effect.getTypeName() + " to target\n";
				}
			}
		}
		return text + "\nCost " + costActivate + " energy per click";
	}

	// Getter & Setter
	public boolean isAoE() {
		return AoE;
	}
}
