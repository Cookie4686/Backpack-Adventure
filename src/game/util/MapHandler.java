package game.util;

import game.GameLogic;
import game.map.MapMarker;
import game.map.MapSquare;

public class MapHandler {
	public static void handleMouseClicked(MapSquare square) {
		if (square.getMarker() == MapMarker.MONSTER) {
			square.setMarker(null);
			GameLogic.fight();
		}
	}
}
