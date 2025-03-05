package logic.handler;

import application.Fader;
import game.dialog.GameDialog;
import game.map.MapSquare;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import logic.GameLogic;

public class MapHandler {
	public static void handleMouseClicked(MapSquare square) {
		switch (square.getMarker()) {
		case MONSTER	-> {
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				square.setMarker(null);
				square.getChildren().clear();
				GameLogic.getInstance().initializeFight();
			});
			pause.play();
		}
		// Test dialog
		case PLAYER		-> {
			GameDialog dialog = new GameDialog("Test Dialog");
			dialog.setText(
					"""
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Sed laoreet dignissim urna, vel faucibus magna pellentesque sed.
							Ut varius, tortor nec varius cursus, neque neque facilisis metus, quis ultrices mi nulla ullamcorper risus.
							Curabitur vestibulum vel risus eu convallis. Vestibulum quis nunc magna. Curabitur vitae elit scelerisque, pharetra diam in, lacinia libero.
									""");
			dialog.addOption("Option 1 (print op1)", _ -> {
				System.out.println("op1");
			});
			dialog.addOption("Option 2 (print op2)", _ -> {
				System.out.println("op2");
			});
			dialog.addOption("Option 3 (print op3)", _ -> {
				System.out.println("op3");
			});
			dialog.show();
		}
		default			-> {}
		}
	}
}
