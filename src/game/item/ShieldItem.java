package game.item;

import java.util.ArrayList;

import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.Clickable;
import logic.FightLogic;

public class ShieldItem extends Item implements Clickable{
	final private ArrayList<Effect> effects;
	private int shield, costActivate;
	
	public ShieldItem(String name, String detail, int shield, ArrayList<Effect> effects, int costActivate, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.shield = shield<0 ? 0 : shield;
		this.costActivate = costActivate;
		this.effects = effects;
	}
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy() < costActivate)
			return false;
		return true;
	}
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) {
			System.out.println("Not enough energy");
			return;
		}
		System.out.println("Use "+getName());
		
		for (Effect effect:effects) {
			FightLogic.findEffectAndAdd(Player.getInstance().getAllEffect(), effect.getType(), effect.getAmount());
		}
		
		Player.getInstance().setShield(Player.getInstance().getShield()+shield);
	}
	
	@Override
	public String toString() {
		String text=getName()+" is "+getTierName()+" apparel\n"
				+ "Provide :\n"
				+ "Add shield : "+shield+" SHIELD\n";
		
		for (Effect effect : effects) {
			text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to Player\n";
		}
		
		return text+ "\nCost " + costActivate + " energy per click";
	}
}
