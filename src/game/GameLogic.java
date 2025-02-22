package game;

import entities.Entity;
import entities.Player;

public class GameLogic {
	private static boolean isFighting;
	private static Entity selectedEntity;

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
		GameBottom.getInstance().addAllEntity(new Entity("Enemy1", null, 10, 20, 10, 20, 10, 20, null),
				new Entity("Enemy2", null, 15, 25, 15, 25, 15, 25, null));
	}

	public static boolean isFighting() {
		return isFighting;
	}

	public static Entity getSelectedEntity() {
		return selectedEntity;
	}

	public static void setSelectedEntity(Entity selectedEntity) {
		GameLogic.selectedEntity = selectedEntity;
		GameBottom.getInstance().render();
	}
}
