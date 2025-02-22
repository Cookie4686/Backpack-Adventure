package game;

public class GameLogic {
	private static boolean isFighting;

	public static void fight() {
		isFighting = true;
		GameTop.getInstance().useBackpack();
	}

	public static boolean isFighting() {
		return isFighting;
	}
}
