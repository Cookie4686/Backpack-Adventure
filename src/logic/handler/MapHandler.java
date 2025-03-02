package logic.handler;

import application.Fader;
import game.map.MapMarker;
import game.map.MapSquare;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import logic.GameLogic;

public class MapHandler {
	public static void handleMouseClicked(MapSquare square) {
		if (square.getMarker() == MapMarker.MONSTER) {
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(event -> {
				square.setMarker(null);
				square.getChildren().clear();
				GameLogic.getInstance().initializeFight();
			});
			pause.play();
		}
	}
}
