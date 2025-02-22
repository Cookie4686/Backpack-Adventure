package entities;

import java.util.ArrayList;

import game.util.Effect;
import javafx.scene.layout.BorderPane;

public abstract class Being extends BorderPane {
	protected String name;
	protected int hp, maxHp, shield, dodge;
	protected ArrayList<Effect> allEffect;

	public Being() {
		super();
		allEffect = new ArrayList<Effect>();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		int pos = (hp < 0 ? 0 : hp);
		this.hp = pos > maxHp ? maxHp : pos;
	}

	public abstract int takeDamage(int damaged);

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
