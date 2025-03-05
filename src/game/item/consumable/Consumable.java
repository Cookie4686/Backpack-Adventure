package game.item.consumable;

import entities.Player;
import game.backpack.Backpack;
import game.item.ItemWithCost;
import game.itemGenerator.ResourceLoader;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import logic.FightLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class Consumable extends ItemWithCost {
	final private Effect effect;
	private int costActivate, durability;

	public Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width,
			int height, ItemTier tier) {
		super(name, detail, costActivate, width, height, tier);
		this.effect = effect;
		setDurability(durability);
	}

	@Override
	public void activateItem() {
		System.out.println("Use " + getName());
		SfxPlayer.play(Sfx.EAT);
		setDurability(getDurability() - 1);
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);

		switch (effect.getType()) {
		case HEAL					-> Player.getInstance().setHp(Player.getInstance().getHp() + effect.getAmount());
		case ENERGY					->
			Player.getInstance().setEnergy(Player.getInstance().getEnergy() + effect.getAmount());
		case LUCK					->
			Player.getInstance().setLuck(Player.getInstance().getLuck() + effect.getAmount());
		case THORN, ANGER, DODGE	-> FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(),
				effect.getType(), effect.getAmount(), Player.getInstance());
		default						-> {}
		}

		if (getDurability() == 0) {
			if (this instanceof FoodWithContainer) {
				System.out.println("reset to " + ((FoodWithContainer) this).getContainer());
				Backpack.getInstance().replaceItem(this,
						ResourceLoader.newItem(((FoodWithContainer) this).getContainer()));
			}
			delete();
		}
	}

	// For print only
	protected String getHeader() {
		return getName() + " is " + getTierName() + " Food\n" + getDurability() + " use left" + "\nWhen click :\n";
	}

	protected String getProvideMid() {
		return switch (effect.getType()) {
		case HEAL	-> "Heal Player " + effect.getAmount() + " Health\n";
		case LUCK	-> "Add " + effect.getAmount() + " LUCK to Player\n";
		case ENERGY	-> "Add " + effect.getAmount() + " ENEGRY to Player\n";
		default		-> "Add " + effect.getAmount() + " " + effect.getTypeName() + " to Player\n";
		};
	}

	@Override
	public String toString() {
		return getHeader() + getProvideMid() + "\nCost " + costActivate + " energy per click";
	}

	// Getter & Setter
	public EffectType getEffectType() {
		return effect.getType();
	}

	public int getEffectAmount() {
		return effect.getAmount();
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability < 0 ? 0 : durability;
	}
}
