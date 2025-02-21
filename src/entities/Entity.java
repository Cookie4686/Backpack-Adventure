package entities;

import java.util.ArrayList;
import java.util.Random;

import game.util.Effect;

public class Entity {
	protected String name;
	protected int hp, maxHp, atk, shield, xp;
	protected ArrayList<String> pic;
	protected ArrayList<Effect> allAttributes, allEffect;

	public Entity(String name, ArrayList<String> pic, int maxHpLb, int maxHpUb, int atkLb, int atkUb, int xpLb,
			int xpUb, ArrayList<Effect> allAttributes) {
		this.name = name;
		Random rand = new Random();
		this.hp = this.maxHp = rand.nextInt((maxHpUb - maxHpLb) + 1) + maxHpLb;
		this.atk = rand.nextInt((atkUb - atkLb) + 1) + atkLb;
		this.shield = 0;
		this.xp = rand.nextInt((xpUb - xpLb) + 1) + xpLb;
		this.pic = pic;
		this.allAttributes = allAttributes;
	}

	public int attack() {
		int damaged = atk;
		if (Player.getShield() >= atk) {
			Player.setShield(Player.getShield() - damaged);
			damaged = 0;
		} else {
			damaged -= Player.getShield();
			Player.setShield(0);
			if (Player.getHp() - damaged < 0)
				damaged = Player.getHp();
			Player.setHp(Player.getHp() - damaged);
		}
		return damaged;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getPic() {
		return pic;
	}

	public void setPic(ArrayList<String> pic) {
		this.pic = pic;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp < 0 ? 0 : hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk < 0 ? 0 : atk;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield < 0 ? 0 : shield;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp < 0 ? 0 : xp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 0 ? 0 : maxHp;
	}

	public ArrayList<Effect> getAllAttributes() {
		return allAttributes;
	}

	public void setAllAttributes(ArrayList<Effect> allAttributes) {
		this.allAttributes = allAttributes;
	}

	public ArrayList<Effect> getAllEffect() {
		return allEffect;
	}

	public void setAllEffect(ArrayList<Effect> allEffect) {
		this.allEffect = allEffect;
	}
}
