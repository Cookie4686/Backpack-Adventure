package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scene.MenuScene;

public class Main extends Application {
	public static StackPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new StackPane();
		root.setPrefSize(600, 600);
		MenuScene.useScene();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Backpack");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
