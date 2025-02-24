package logic.handler;

import entities.Entity;
import logic.FightLogic;

public class EntityHandler {
	public static void handleMouseClicked(Entity entity) {
		FightLogic.getInstance().setTarget(entity);
	}
}
