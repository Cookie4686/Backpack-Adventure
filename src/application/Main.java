package application;

import game.item.DraggableHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import scene.GameScene;

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
		//root.setPrefSize(600, 600);
		GameScene.useScene();
		Scene scene = new Scene(root, 400, 300);
		scene.setOnKeyPressed(event -> DraggableHandler.handleSceneKeyPress(event));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Backpack");
		primaryStage.setMaximized(true);
		//primaryStage.setResizable(false);
        
		
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
