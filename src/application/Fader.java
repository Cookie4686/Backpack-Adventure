package application;

import entities.Player;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fader {
	private static Rectangle blackScreen, blackout;
	private static Scene scene;

	public static void initialize(Stage stage) {
		scene = stage.getScene();
		if (scene == null) {
			System.err.println("Error: Scene is null in Fader.initialize!");
			return;
		}
		if (!(scene.getRoot() instanceof StackPane)) {
			System.err.println("Error: Root is not a StackPane!");
			return;
		}
		StackPane root = (StackPane) scene.getRoot();

		Platform.runLater(() -> {
			blackScreen = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
			blackScreen.setOpacity(1.0);
			blackout = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
			blackout.setOpacity(0.0);
			blackout.widthProperty().bind(scene.widthProperty());
			blackout.heightProperty().bind(scene.heightProperty());
			root.getChildren().add(blackout);
			blackout.toBack();
			System.out.println("Blackout added to StackPane!");
		});

	}

	public static void fadeOutAndIn() {
		if (scene == null || blackout == null) {
			System.err.println("Fader not initialized! Call initialize(Stage) first.");
			return;
		}

		System.out.println("Starting fade effect...");
		Platform.runLater(() -> {
			StackPane root = (StackPane) scene.getRoot();

			root.getChildren().remove(blackout);
			root.getChildren().add(blackout);
			blackout.toFront();
			System.out.println("Blackout moved to front!");

			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), blackout);
			fadeOut.setFromValue(0.0);
			fadeOut.setToValue(1.0);

			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), blackout);
			fadeIn.setFromValue(1.0);
			fadeIn.setToValue(0.0);

			fadeOut.setOnFinished(event -> {
				PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
				pause.setOnFinished(e -> {
					root.getChildren().remove(blackout);
					root.getChildren().add(blackout);
					blackout.toFront();

					fadeIn.play();
				});
				pause.play();
			});
			fadeIn.setOnFinished(_ -> Platform.runLater(() -> {
				blackout.toBack();
				System.out.println("Blackout moved to back!");
			}));
			fadeOut.play();
			Player.getInstance().moveLeftAndBack();
		});
	}

	public static Rectangle getBlackout() {
		return blackout;
	}

	public static Rectangle getBlackScreen() {
		return blackScreen;
	}
}
