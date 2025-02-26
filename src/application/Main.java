package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.handler.ItemHandler;
import scene.MenuScene;
import sound.BackgroundSongLoader;

public class Main extends Application {
	public static StackPane root;
	public static MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Screen screen = Screen.getPrimary();
		// Rectangle2D bounds = screen.getVisualBounds();
		// double screenWidth = bounds.getWidth();
		// double screenHeight = bounds.getHeight();
		// double aspectRatio = screenWidth / screenHeight;

		root = new StackPane();
		root.prefWidthProperty().bind(primaryStage.widthProperty());
		root.prefHeightProperty().bind(primaryStage.heightProperty());
		MenuScene.use();
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(ClassLoader.getSystemResource("test.css").toExternalForm());
		scene.setOnKeyPressed(event -> ItemHandler.handleSceneKeyPress(event));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Backpack");
		// primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();

//		BackgroundSongLoader.autoplay();
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
