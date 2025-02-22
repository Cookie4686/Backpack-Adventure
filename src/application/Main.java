package application;

import game.util.ItemHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import scene.MenuScene;

public class Main extends Application {
	public static StackPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double screenWidth = bounds.getWidth();
		double screenHeight = bounds.getHeight();
		double aspectRatio = screenWidth / screenHeight;

		root = new StackPane();
		root.prefWidthProperty().bind(primaryStage.widthProperty());
		root.prefHeightProperty().bind(primaryStage.heightProperty());
		// root.setPrefSize(600, 600);
		MenuScene.use();
		Scene scene = new Scene(root, 800, 600);
		scene.setOnKeyPressed(event -> ItemHandler.handleSceneKeyPress(event));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Backpack");
		// primaryStage.setMaximized(true);
		// primaryStage.setResizable(false);

		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
