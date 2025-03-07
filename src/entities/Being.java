package entities;

import java.util.ArrayList;

import component.HpBar;
import game.util.Effect;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public abstract class Being extends VBox implements ReRenderable {
	protected String name;
	protected int hp, maxHp, shield;
	protected ArrayList<Effect> allEffect;
	protected HpBar hpBar;
	private static boolean isUpdating;

	public Being() {
		super();
		allEffect = new ArrayList<Effect>();
		setAlignment(Pos.BOTTOM_CENTER);
		
	}

	@Override
	public abstract void render();

	public abstract int takeDamage(int damaged);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp < 0 ? 0 : (hp > maxHp ? maxHp : hp);
		if (!isUpdating) hpBar.setHpBar();
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 0 ? 0 : maxHp;
		if (!isUpdating) setHp(hp);
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

	public HpBar getHpBar() {
		return hpBar;
	}

	public void setHpBar(HpBar hpBar) {
		this.hpBar = hpBar;
	}

	public static boolean isUpdating() {
		return isUpdating;
	}

	public static void setUpdating(boolean isUpdating) {
		Being.isUpdating = isUpdating;
	}
}
