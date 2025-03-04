package scene;

import entities.Player;
import image.GifPlayer;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sound.Sfx;
import sound.SfxPlayer;

public class CharacterScene {
	private static ImageView preview;
	
	public static HBox use() {
		HBox root = new HBox();
		root.setSpacing(40);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(40));
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
		Timeline idel = GifPlayer.createAnimation(preview, GifPlayer.getIdleKnight(), 0.1);
		costumeList.getChildren().add(preview);
		idel.setCycleCount(Timeline.INDEFINITE);
		idel.play();
		
		ImageView charactor = new ImageView(new Image(ClassLoader.getSystemResource("picture/knight.png").toString()));
		charactor.setFitWidth(200);
		charactor.setFitHeight(200);
		
		Text name = new Text("Knight");
		name.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 60));
		Text detail = new Text("A knight of unwavering honor, felt the weight \nof the world on his shoulders. His duty drove \nhim into the heart of the darkness.");
		detail.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 20));
		
		characterInfo.getChildren().addAll(name, detail);
		mainCharacter.getChildren().addAll(charactor, characterInfo);
		selectedCharacter.getChildren().addAll(mainCharacter, costumeList);
		
		ImageView knightIcons = new ImageView();
		knightIcons.setOnMouseClicked(_ -> SfxPlayer.play(Sfx.SELECT));
		knightIcons.setFitWidth(100);
		knightIcons.setFitHeight(100);
		knightIcons.setCursor(Cursor.CROSSHAIR);
		
		Timeline icon = GifPlayer.createAnimation(knightIcons, GifPlayer.getKnightIcons(), 0.25);
		icon.setCycleCount(Timeline.INDEFINITE);		
		
		ImageView unknown1 = new ImageView(new Image(ClassLoader.getSystemResource("picture/unknown.png").toString()));
		unknown1.setOnMouseClicked(_ -> SfxPlayer.play(Sfx.DENINE));
		unknown1.setCursor(Cursor.CROSSHAIR);
		unknown1.setFitWidth(100);
		unknown1.setFitHeight(100);
		
		ImageView unknown2 = new ImageView(new Image(ClassLoader.getSystemResource("picture/unknown.png").toString()));
		unknown2.setOnMouseClicked(_ -> SfxPlayer.play(Sfx.DENINE));
		unknown2.setCursor(Cursor.CROSSHAIR);
		unknown2.setFitWidth(100);
		unknown2.setFitHeight(100);
		characterList.getChildren().addAll(knightIcons, unknown1, unknown2);
		
		icon.play();
		
		root.getChildren().addAll(characterList, selectedCharacter);
		
		return root;
	}
}
