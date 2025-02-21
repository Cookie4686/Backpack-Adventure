package entities;

import java.util.ArrayList;

import game.util.Effect;

public class Player {
	private static String name;
	private static int hp, maxHp, shield, xp, maxXp, energy, maxEnergy;
	private static ArrayList<String> pic;
	private static ArrayList<Effect> allEffect;

	public static int getHp() {
		return hp;
	}

	public static void setHp(int hp) {
		Player.hp = hp < 0 ? 0 : (hp > maxHp ? maxHp : hp);
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Player.name = name;
	}

	public static ArrayList<String> getPic() {
		return pic;
	}

	public static void setPic(ArrayList<String> pic) {
		Player.pic = pic;
	}

	public static int getShield() {
		return shield;
	}

	public static void setShield(int shield) {
		Player.shield = shield < 0 ? 0 : shield;
	}

	public static int getXp() {
		return xp;
	}

	public static void setXp(int xp) {
		Player.xp = xp < 0 ? 0 : xp;
	}

	public static int getMaxHp() {
		return maxHp;
	}

	public static void setMaxHp(int maxHp) {
		Player.maxHp = maxHp < 0 ? 0 : maxHp;
	}

	public static int getMaxXp() {
		return maxXp;
	}

	public static void setMaxXp(int maxXp) {
		Player.maxXp = maxXp;
	}

	public static ArrayList<Effect> getAllEffect() {
		return allEffect;
	}

	public static void setAllEffect(ArrayList<Effect> allEffect) {
		Player.allEffect = allEffect;
	}

	public static int getEnergy() {
		return energy;
	}

	public static void setEnergy(int energy) {
		Player.energy = energy < 0 ? 0 : energy;
	}

	public static int getMaxEnergy() {
		return maxEnergy;
	}

	public static void setMaxEnergy(int maxEnergy) {
		Player.maxEnergy = maxEnergy < 0 ? 0 : maxEnergy;
	}
}
