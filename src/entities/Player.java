package entities;

import java.util.ArrayList;

import game.util.Effect;

public class Player {
	private static String name;
	private static ArrayList<String> pic;
	private static int hp;
	private static int maxHp;
	private static int shield;
	private static int xp;
	private static int maxXp;
	private static int energy;
	private static int maxEnergy;
	private static ArrayList<Effect> allEffect;
	
	public static int getHp() {
		return hp;
	}
	public static void setHp(int hp) {
		if(hp < 0) hp = 0;
		Player.hp = hp;
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
		if(shield < 0) shield = 0;
		Player.shield = shield;
	}
	public static int getXp() {
		return xp;
	}
	public static void setXp(int xp) {
		if(xp < 0) xp = 0;
		Player.xp = xp;
	}
	public static int getMaxHp() {
		return maxHp;
	}
	public static void setMaxHp(int maxHp) {
		if(maxHp < 0) maxHp = 0;
		Player.maxHp = maxHp;
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
		if(energy < 0) energy = 0;
		Player.energy = energy;
	}
	public static int getMaxEnergy() {
		return maxEnergy;
	}
	public static void setMaxEnergy(int maxEnergy) {
		if(maxEnergy < 0) maxEnergy = 0;
		Player.maxEnergy = maxEnergy;
	}
	
}
