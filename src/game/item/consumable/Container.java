package game.item.consumable;

import entities.Player;
import game.item.Item;
import game.util.ItemTier;
import interfaces.Clickable;
import logic.FightLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class Container extends Item implements Clickable {
	private int damage;

	public Container(String name, String detail, int damage, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		setDamage(damage);
	}

	@Override
	public boolean isEnoughEnergy() {
		return true;
	}

	@Override
	public void activatePerClick() {
		Player.getInstance().attack();
		SfxPlayer.play(Sfx.THROW);
		FightLogic.getInstance().getTarget().takeDamage(damage);
		delete();
	}

	@Override
	public String toString() {
		return getName() + " is " + getTierName() + " container\n" + "Item will be gone after use\n" + "When click :\n"
				+ "Damage target : " + damage + " DAMAGE\n" + "\nCost 0 energy per click";
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
