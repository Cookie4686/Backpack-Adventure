package entities;

import java.util.ArrayList;

import game.util.Effect;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class Being extends VBox implements ReRenderable {
	protected String name;
	protected int hp, maxHp, shield;
	protected ArrayList<Effect> allEffect;

	public Being() {
		super();
		allEffect = new ArrayList<Effect>();
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 0 ? 0 : maxHp;
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
}
