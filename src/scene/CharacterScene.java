package scene;

import entities.Player;
import image.gifPlayer;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CharacterScene {
	public static ImageView preview;
	
	public static HBox use() {
		HBox root = new HBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER);
		
		VBox characterList = new VBox();
		characterList.setSpacing(40);
		characterList.setAlignment(Pos.CENTER);
		
		VBox selectedCharacter = new VBox();
		selectedCharacter.setAlignment(Pos.CENTER);
		
		HBox mainCharacter = new HBox();
		
		VBox characterInfo = new VBox();
		characterInfo.setPadding(new Insets(20));
		characterInfo.setAlignment(Pos.TOP_LEFT);
		
		HBox costumeList = new HBox();
		costumeList.setSpacing(20);
		costumeList.setAlignment(Pos.BOTTOM_CENTER);
		
		//Adding to pane
		preview = new ImageView();
		preview.setPreserveRatio(true);
		preview.setFitHeight(200);
		Timeline timeline = gifPlayer.createKnightAnimation(preview, 0.1);
		costumeList.getChildren().add(preview);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		ImageView charactor = new ImageView(new Image(ClassLoader.getSystemResource("picture/knight.png").toString()));
		charactor.setFitWidth(200);
		charactor.setFitHeight(200);
		Text name = new Text("Knight");
		name.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
		Text detail = new Text("Heavy blade designed for raw power over finesse.");
		detail.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
		
		characterInfo.getChildren().addAll(name, detail);
		mainCharacter.getChildren().addAll(charactor, characterInfo);
		selectedCharacter.getChildren().addAll(mainCharacter, costumeList);
		
		ImageView knightIcon = new ImageView(new Image(ClassLoader.getSystemResource("picture/knightIconFrame.png").toString()));
		knightIcon.setFitWidth(100);
		knightIcon.setFitHeight(100);
		
		characterList.getChildren().addAll(knightIcon);
		
		root.getChildren().addAll(characterList, selectedCharacter);
		
		return root;
	}
}
