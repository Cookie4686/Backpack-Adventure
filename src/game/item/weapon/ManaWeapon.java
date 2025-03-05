package game.item.weapon;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.ItemTier;

public class ManaWeapon extends SpecialWeapon {
	public ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width,
			ItemTier tier) {
		super(name, detail, effects, damage, costActivate, width, tier);
	}

	public ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width,
			int height, ItemTier tier) {
		super(name, detail, effects, damage, costActivate, width, height, tier);
	}

	@Override
	public boolean isEnoughEnergy() {
		return Player.getInstance().getMana() >= getCostActivate();
	}

	@Override
	public String toString() {
		return getProvide() + "\nCost " + getCostActivate() + " mana per click";
	}
}
