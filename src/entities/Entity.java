package entities;

import java.util.ArrayList;
import java.util.Random;

import game.util.Effect;

public class Entity {
	protected String name;
	protected ArrayList<String> pic;
	protected int hp;
	protected int maxHp;
	protected int atk;
	protected int shield;
	protected int xp;
	protected ArrayList<Effect> allAttributes;
	protected ArrayList<Effect> allEffect;
	public Entity(String name, ArrayList<String> pic, int maxHpLb, int maxHpUb, int atkLb, int atkUb, int xpLb, int xpUb, ArrayList<Effect> allAttributes) {
		this.name = name;
		this.pic = pic;
		Random rand = new Random(); 
		int randomMaxHp = rand.nextInt((maxHpUb - maxHpLb) + 1) + maxHpLb; 
		this.hp = randomMaxHp;
		this.maxHp = randomMaxHp;
		int randomAtk = rand.nextInt((atkUb - atkLb) + 1) + atkLb; 
		this.atk = randomAtk;
		this.shield = 0;
		int randomXp = rand.nextInt((xpUb - xpLb) + 1) + xpLb; 
		this.xp = randomXp;
		this.allAttributes = allAttributes;
	}
	public int attack() {
		int damaged = atk;
		if(Player.getShield() >= atk) {
			Player.setShield(Player.getShield() - damaged);
			damaged = 0;
		} else {
			damaged -= Player.getShield();
			Player.setShield(0);
			if(Player.getHp() - damaged < 0) damaged = Player.getHp();
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
		if(hp < 0) hp = 0;
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		if(atk < 0) atk = 0;
		this.atk = atk;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		if(shield < 0) shield = 0;
		this.shield = shield;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		if(xp < 0) xp = 0;
		this.xp = xp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		if(maxHp < 0) maxHp = 0;
		this.maxHp = maxHp;
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
