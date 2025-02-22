package game;

import entities.Player;

public class GameLogic {
	private static boolean isFighting;

	public static void initialize() {
		isFighting = false;
		Player.setMaxHp(30);
		Player.setHp(30);
		Player.setShield(0);
		Player.setXp(0);
		Player.setMaxXp(10);
		Player.setEnergy(3);
	}

	public static void fight() {
		isFighting = true;
		Game.getInstance().clearFloatingItem();
		GameTop.getInstance().useBackpack();
	}

	public static boolean isFighting() {
		return isFighting;
	}
}
