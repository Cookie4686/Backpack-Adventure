package entities;

import java.util.ArrayList;

import game.util.Effect;

public class Being {
	protected int hp, maxHp, shield, dodge;
	protected ArrayList<Effect> allEffect;
	
	public Being() {
		super();
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp < 0 ? 0 : hp;
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
}
