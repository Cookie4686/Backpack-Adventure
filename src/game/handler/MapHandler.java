package game.handler;

import game.map.MapMarker;
import game.map.MapSquare;
import logic.GameLogic;

public class MapHandler {
	public static void handleMouseClicked(MapSquare square) {
		if (square.getMarker() == MapMarker.MONSTER) {
			square.setMarker(null);
			square.getChildren().clear();
			GameLogic.getInstance().initializeFight();
		}
	}
}
