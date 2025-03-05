package game.item.weapon;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.ItemTier;
import logic.FightLogic;

public class SpecialWeapon extends Weapon {
	final private ArrayList<Effect> effects;

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width,
			ItemTier tier) {
		super(name, detail, damage, costActivate, width, tier);
		this.effects = effects;
	}

	public SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width,
			int height, ItemTier tier) {
		super(name, detail, damage, costActivate, width, height, tier);
		this.effects = effects;
	}

	@Override
	public void activateItem() {
		super.activateItem();
		for (Effect effect : effects) {
			switch (effect.getType()) {
			case FIRE, POISON, STUNNED	->
				FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(),
						effect.getAmount(), FightLogic.getInstance().getTarget());
			case HEAL					->
				Player.getInstance().setMaxHp(Player.getInstance().getMaxHp() + effect.getAmount());
			case VAMPIRIC				->
				Player.getInstance().setHp(Player.getInstance().getHp() + (getDamage() * effect.getAmount() / 100));
			case LUCK					->
				Player.getInstance().setLuck(Player.getInstance().getLuck() + effect.getAmount());
			case SHIELD					->
				Player.getInstance().setShield(Player.getInstance().getShield() + effect.getAmount());
			default						-> FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(),
					effect.getType(), effect.getAmount(), Player.getInstance());
			}
		}
	}

	@Override
	public String getProvide() {
		String text = super.getProvide();
		for (Effect effect : effects) {
			text += switch (effect.getType()) {
			case FIRE, POISON, STUNNED	-> "Add " + effect.getAmount() + " " + effect.getTypeName() + " to target\n";
			case HEAL					-> "Add Player " + effect.getAmount() + " MaxHealth (Reset after battle)\n";
			case VAMPIRIC				-> "Heal Player " + (getDamage() * effect.getAmount() / 100) + " Health ("
					+ effect.getAmount() + "% of damage)\n";
			case LUCK					-> "Add " + effect.getAmount() + " Luck to player\n";
			case SHIELD					-> "Add " + effect.getAmount() + " SHIELD to player\n";
			default						-> "Add " + effect.getAmount() + " " + effect.getTypeName() + " to Player\n";
			};
		}
		return text;
	}

	@Override
	public String toString() {
		return getProvide() + "\nCost " + getCostActivate() + " energy per click";
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
