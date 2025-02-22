package game.handler;

import entities.Entity;
import game.GameLogic;

public class EntityHandler {
	public static void handleMouseClicked(Entity entity) {
		GameLogic.setSelectedEntity(entity);

	}
}
