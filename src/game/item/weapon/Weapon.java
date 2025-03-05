package game.item.weapon;

import entities.Entity;
import entities.Player;
import game.item.ItemWithCost;
import game.util.ItemTier;
import interfaces.ReStatable;
import logic.FightLogic;
import logic.GameLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class Weapon extends ItemWithCost implements ReStatable {
	final private int initDamage;
	private int damage;

	public Weapon(String name, String detail, int initDamage, int costActivate, int width, ItemTier tier) {
		super(name, detail, costActivate, width, tier);
		setDamage(this.initDamage = initDamage);
	}

	public Weapon(String name, String detail, int initDamage, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, costActivate, width, height, tier);
		setDamage(this.initDamage = initDamage);
	}

	@Override
	public void reStatBeforeUpdate() {
		setDamage(initDamage);
	}

	@Override
	public void activatePerClick() {
		if (isEnoughEnergy()) {
			activateItem();
		} else {
			System.out.println("Not enough " + (this instanceof ManaWeapon ? "mana" : "energy"));
			SfxPlayer.play(Sfx.DENY);
		}
	}

	@Override
	public void activateItem() {
		System.out.println("Use " + getName());
		Player.getInstance().attack();
		SfxPlayer.play(this instanceof ManaWeapon ? Sfx.MAGIC : Sfx.SWORD);

		// decrease player energy by costActivate
		if (this instanceof ManaWeapon) {
			Player.getInstance().setMana(Player.getInstance().getMana() - getCostActivate());
		} else {
			Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		}

		FightLogic.doDamage(damage, Player.getInstance(), FightLogic.getInstance().getTarget());

		for (Entity e : FightLogic.getInstance().getEntities()) {
			if (e.getHp() > 0)
				return;
		}
		GameLogic.getInstance().endFight();
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

	public void addDamage(int damage) {
		setDamage(getDamage() + damage);
	}
}
