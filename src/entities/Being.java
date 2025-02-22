package entities;

import java.util.ArrayList;

import game.util.Effect;
import game.util.EffectType;
import logic.GameLogic;

public class Being {
	protected String name;
	protected int hp, maxHp, shield, dodge;
	protected ArrayList<Effect> allEffect;
	
	public Being() {
		super();
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		int pos = (hp < 0 ? 0 : hp);
		this.hp = pos > maxHp ? maxHp : pos;
	}
	
	public int takeDamage(int damaged) {
		if(GameLogic.findEffectAndDecrease(allEffect,EffectType.DODGE,1)) {
			return 0;
		}
		if (Player.getInstance().getShield() >= damaged) {
			Player.getInstance().setShield(Player.getInstance().getShield() - damaged);
			damaged = 0;
		} else {
			damaged -= Player.getInstance().getShield();
			Player.getInstance().setShield(0);
			if (Player.getInstance().getHp() - damaged < 0) {
				damaged = Player.getInstance().getHp();
			}
			Player.getInstance().setHp(Player.getInstance().getHp() - damaged);
		}
		
		new Thread(()->{
			
		}).start();
		return damaged;
	}
	
	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield < 0 ? 0 : shield;
	}
	
	public ArrayList<Effect> getAllEffect() {
		return allEffect;
	}

	public void setAllEffect(ArrayList<Effect> allEffect) {
		this.allEffect = allEffect;
	}
	
	public int getDodge() {
		return dodge;
	}
	
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}
	
	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 0 ? 0 : maxHp;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
