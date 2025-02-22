package entities;

import java.util.ArrayList;

import game.item.Item;
import game.util.Effect;
import interfaces.TurnActivable;

public class Player extends Being implements TurnActivable{
	private static Player instance = null;
	private int xp, maxXp, energy, maxEnergy, mana, maxMana, money, luck;
	private ArrayList<String> pic;
	private ArrayList<Item> inventory;
	
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
		this.dodge = 0;
		this.money = 0;
		this.pic = null;
		this.allEffect = new ArrayList<Effect>();
		this.inventory = new ArrayList<Item>();
	}
	public static Player getInstance() {
		if(instance == null) {
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

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
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
	public void activatePerTurn() {
		// TODO Auto-generated method stub
		
	}
	
}
