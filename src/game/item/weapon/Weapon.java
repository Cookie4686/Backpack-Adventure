package game.item.weapon;

import entities.Player;
import game.item.Item;
import game.util.ItemTier;
import interfaces.Clickable;
import interfaces.ReStatable;
import logic.FightLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class Weapon extends Item implements Clickable, ReStatable {
	final private int initDamage;
	private int damage, costActivate;

	public Weapon(String name, String detail, int initDamage, int costActivate, int width, ItemTier tier) {
		super(name, detail, width, tier);
		this.initDamage = initDamage;
		setDamage(initDamage);
		setCostActivate(costActivate);
	}

	public Weapon(String name, String detail, int initDamage, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.initDamage = initDamage;
		setDamage(initDamage);
		setCostActivate(costActivate);
	}

	@Override
	public void reStatBeforeUpdate() {
		setDamage(initDamage);
	}

	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy() < costActivate)
			return false;
		return true;
	}

	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy())
			return;

		// TODO: change this later
		SfxPlayer.play(Sfx.SWORD);
		// decrease player energy by costActivate
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);

		// damage enemy getDamage() amount
		FightLogic.getInstance().getTarget().takeDamage(damage);
	}

	// For print only
	public String getProvide() {
		String text = getName() + " is " + getTierName() + " weapon\n" + "When click :\n" + "Damage target : "
				+ initDamage;
		if (damage > initDamage)
			text = text + " + " + (damage - initDamage);
		else if (damage < initDamage)
			text = text + " - " + (initDamage - damage);

		return text + " DAMAGE\n";
	}

	@Override
	public String toString() {
		return getProvide() + "\nCost " + costActivate + " energy per click";
	}

	// Getter & Setter

	public int getInitDamage() {
		return initDamage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage < 0 ? 0 : damage;
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
