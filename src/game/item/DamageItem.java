package game.item;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.Clickable;
import logic.FightLogic;

public class DamageItem extends Item implements Clickable {
	final private boolean AoE;
	final private ArrayList<Effect> effects;
	private int costActivate;
	
	public DamageItem(String name, String detail, ArrayList<Effect> effects, int costActivate, boolean isAoE, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.effects = effects;
		this.AoE = isAoE;
		setCostActivate(costActivate);
	}
	
	@Override
	public boolean isEnoughEnergy() {
		if (Player.getInstance().getEnergy()<costActivate) return false;
		return true;
	}	
	
	@Override
	public void activatePerClick() {
		if (!isEnoughEnergy()) return;
		
		//decrease player energy by costActivate
		Player.getInstance().setEnergy(Player.getInstance().getEnergy() - costActivate);
		
		for (Effect effect:effects) {
			if (isAoE()) {
				if (effect.getType()==EffectType.DAMAGE) {
					for (Entity enemy:FightLogic.getInstance().getEntities()) {
						enemy.takeDamage(effect.getAmount());
					}
				}
				else if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
					for (Entity enemy:FightLogic.getInstance().getEntities()) {
						FightLogic.findEffectAndAdd(enemy.getAllEffect(), effect.getType(), effect.getAmount());
					}
				}
			}
			else {
				if (effect.getType()==EffectType.DAMAGE) {
					FightLogic.getInstance().getTarget().takeDamage(effect.getAmount());
				}
				if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
					FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(), effect.getAmount());
				}
			}
		}
		
		delete();
	}

	
	@Override
	public String toString() {
		String text=getName()+" is "+getTierName()+" throw weapon\n"
				+ "Will gone after use\n"
				+ "When click :\n";
		
		for (Effect effect : effects) {
			if (effect.getType()==EffectType.DAMAGE) {
				if (isAoE()) {
					text=text+"Damage all enemy : "+effect.getAmount()+" DAMAGE\n";
				} else {
					text=text+"Damage target : "+effect.getAmount()+" DAMAGE\n";
				}
			}
			else if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				if (isAoE()) {
					text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to all enemy\n";
				} else {
					text=text+"Add "+effect.getAmount()+" "+effect.getTypeName()+" to target\n";
				}
			}
		}
		
		return text+"\nCost "+costActivate+" energy per click";
	}

	
	//Getter & Setter
	public boolean isAoE() {
		return AoE;
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
