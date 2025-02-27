package game.item.weapon;

import java.util.ArrayList;

import game.util.Effect;
import game.util.ItemTier;
import interfaces.TurnActivable;

public class IncreaseCostWeapon extends SpecialWeapon implements TurnActivable{
	final private int initCost;
	private int increaseCost;

	public IncreaseCostWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int increaseCost, int width, int height, ItemTier tier) {
		super(name, detail, effects, damage, costActivate, width, height, tier);
		this.initCost = costActivate;
		this.increaseCost = increaseCost;
	}

	public IncreaseCostWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int increaseCost, int width, ItemTier tier) {
		super(name, detail, effects, damage, costActivate, width, tier);
		this.initCost = costActivate;
		this.increaseCost = increaseCost;
	}
	
	@Override
	public void activatePerTurn() {
		increaseCost=0;
		setCostActivate(initCost);
	}
	
	@Override
	public void activatePerClick() {
		super.activatePerClick();
		setCostActivate(getCostActivate()+increaseCost);
	}
	
	@Override
	public String toString() {
		return getProvide()+"\nCost "+getCostActivate()+" and increase "+increaseCost+" energy per click. Will be reset every turn";
	}
}
