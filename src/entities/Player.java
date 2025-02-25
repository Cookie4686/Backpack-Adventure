package entities;

import java.util.ArrayList;

import component.HpBar;
import game.util.EffectType;
import interfaces.ReStatable;
import interfaces.TurnActivable;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.FightLogic;
import logic.GameLogic;

public class Player extends Being implements TurnActivable, ReStatable {
	private static Player instance = null;
	private int xp, maxXp, energy, maxEnergy, mana, maxMana, coins, luck;
	private ArrayList<String> pic;

	private Text text;

	public Player() {
		super();
		this.name = "Player";
		this.hp = this.maxHp = 100;
		this.shield = 0;
		this.xp = 0;
		this.maxXp = 100;
		this.energy = this.maxEnergy = 3;
		this.mana = this.maxMana = 0;
		this.coins = 0;
		this.luck = 0;
		this.pic = null;

		text = new Text();
		initialize(null);
	}

	// @Override
	public void initialize(Image image) {
		hpBar = new HpBar(this);
		getChildren().setAll(text, hpBar);
		render();
	}

	@Override
	public void render() {
		text.setText(String.format("Energy: %s", energy));
		hpBar.render();
	}

	@Override
	public void activatePerTurn() {
		this.shield = 0;
	}

	@Override
	public void reStatBeforeUpdate() {
		this.maxHp = 100;
		this.maxEnergy = 3;
		this.maxMana = 0;
		this.coins = 0;
	}

	@Override
	public int takeDamage(int damaged) {
		if (FightLogic.findEffectAndDecrease(allEffect, EffectType.DODGE, 1)) {
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
		if (Player.getInstance().getHp() == 0) {
			GameLogic.getInstance().gameOver();
		}

		return damaged;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp < 0 ? 0 : xp;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public void setMaxXp(int maxXp) {
		this.maxXp = maxXp;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy < 0 ? 0 : energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy < 0 ? 0 : maxEnergy;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana < 0 ? 0 : (mana > maxMana ? maxMana : mana);
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public ArrayList<String> getPic() {
		return pic;
	}

	public void setPic(ArrayList<String> pic) {
		this.pic = pic;
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
}
