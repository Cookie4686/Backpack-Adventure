package game.item;

import entities.Entity;
import entities.Player;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;
import interfaces.Clickable;
import logic.FightLogic;

public class DamageItem extends Item implements Clickable {
	final private boolean AoE;
	final private Effect effect;
	private int costActivate;
	
	public DamageItem(String name, String detail, Effect effect, int costActivate, boolean isAoE, int width, int height, ItemTier tier) {
		super(name, detail, width, height, tier);
		this.effect = effect;
		this.AoE = isAoE;
		setCostActivate(costActivate);
	}

	public DamageItem(String name, String detail, Effect effect, int costActivate, boolean isAoE, int height, ItemTier tier) {
		super(name, detail, height, tier);
		this.effect = effect;
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
		
		if (isAoE()) {
			if (getEffectType()==EffectType.DAMAGE) {
				for (Entity enemy:FightLogic.getInstance().getEntities()) {
					enemy.takeDamage(effect.getAmount());
				}
			}
			else if (getEffectType()==EffectType.FIRE || getEffectType()==EffectType.POISON || getEffectType()==EffectType.STUNTED) {
				for (Entity enemy:FightLogic.getInstance().getEntities()) {
					FightLogic.findEffectAndAdd(enemy.getAllEffect(), effect.getType(), effect.getAmount());
				}
			}
		}
		else {
			if (getEffectType()==EffectType.DAMAGE) {
				FightLogic.getInstance().getTarget().takeDamage(effect.getAmount());
			}
			if (effect.getType()==EffectType.FIRE || effect.getType()==EffectType.POISON || effect.getType()==EffectType.STUNTED) {
				FightLogic.findEffectAndAdd(FightLogic.getInstance().getTarget().getAllEffect(), effect.getType(), effect.getAmount());
			}
		}
			
	}

	
	//Getter & Setter
	public boolean isAoE() {
		return AoE;
	}

	public EffectType getEffectType() {
		return effect.getType();
	}
	
	public int getEffectAmount() {
		return effect.getAmount();
	}

	public int getCostActivate() {
		return costActivate;
	}

	public void setCostActivate(int costActivate) {
		this.costActivate = costActivate < 0 ? 0 : costActivate;
	}
}
