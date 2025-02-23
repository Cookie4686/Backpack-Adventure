package entities;

import java.util.ArrayList;

import game.GameBottom;
import game.util.Effect;
import game.util.EffectType;
import interfaces.ReRenderable;
import interfaces.ReStatable;
import interfaces.TurnActivable;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.FightLogic;
import logic.GameLogic;


public class Player extends Being implements TurnActivable, ReRenderable, ReStatable {
	private static Player instance = null;
	private int xp, maxXp, energy, maxEnergy, mana, maxMana, money, luck;
	private ArrayList<String> pic;

	private Text text;

	public Player() {
		super();
		this.name = "Player";
		this.hp = 100;
		this.maxHp = 100;
		this.shield = 0;
		this.xp = 0;
		this.maxXp = 100;
		this.energy = 3;
		this.maxEnergy = 3;
		this.mana = 0;
		this.maxMana = 0;
		this.money = 0;
		this.pic = null;
		this.luck = 0;
		this.allEffect = new ArrayList<Effect>();

		text = new Text();
		setCenter(text);
	}
	
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
		if(Player.getInstance().getHp() == 0) {
			GameLogic.getInstance().gameOver();
		}
		
		return damaged;
	}
	
	//@Override
	public void initialize(Image image) {
		text.setText(String.format("Hp: %s/%s, Df: %s, Energy: %s", hp, maxHp, shield, energy));
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
		this.money = 0;
		this.luck = 0;
	}
	
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
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
		int pos = (mana < 0 ? 0 : mana);
		this.mana = pos > maxMana ? maxMana : pos;
	}

	public ArrayList<String> getPic() {
		return pic;
	}

	public void setPic(ArrayList<String> pic) {
		this.pic = pic;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getLuck() {
		return luck;
	}
	public void setLuck(int luck) {
		this.luck = luck;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		text.setText(String.format("Hp: %s/%s, Df: %s, Energy: %s", hp, maxHp, shield, energy));
	}
}
