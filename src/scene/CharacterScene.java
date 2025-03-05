package scene;

import image.GifPlayer;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sound.Sfx;
import sound.SfxPlayer;

public class CharacterScene {
	
	public static HBox use() {
		HBox root = new HBox();
		root.setSpacing(20);
		root.setAlignment(Pos.TOP_CENTER);
		
		
		VBox characterList = new VBox();
		characterList.setSpacing(60);
		characterList.setAlignment(Pos.BOTTOM_CENTER);
		
		VBox selectedCharacter = new VBox();
		selectedCharacter.setSpacing(20);
		selectedCharacter.setAlignment(Pos.TOP_LEFT);
		
		HBox mainCharacter = new HBox();
		mainCharacter.setSpacing(20);
		
		VBox characterInfo = new VBox();
		characterInfo.setAlignment(Pos.CENTER_LEFT);
		
		StackPane animationList = new StackPane();
		animationList.setAlignment(Pos.CENTER);
		
		HBox animation = new HBox();
		animation.setAlignment(Pos.CENTER);
		animation.setSpacing(100);
		
		//Adding to pane
		ImageView frame = new ImageView(new Image(ClassLoader.getSystemResource("picture/animationFrame.png").toString()));
		frame.setFitHeight(260);
		frame.setFitWidth(670);
		
		ImageView idelPreview = new ImageView();
		idelPreview.setPreserveRatio(true);
		idelPreview.setFitHeight(100);
		Timeline idel = GifPlayer.createAnimation(idelPreview, GifPlayer.getIdleKnight(), 0.1);
		idel.setCycleCount(Timeline.INDEFINITE);
		idel.play();
		
		ImageView attackPreview = new ImageView();
		attackPreview.setPreserveRatio(true);
		attackPreview.setFitHeight(100);
		Timeline attack = GifPlayer.createAnimation(attackPreview, GifPlayer.getAttackKnight(), 0.12);
		attack.setCycleCount(Timeline.INDEFINITE);
		attack.play();
		
		ImageView runPreview = new ImageView();
		runPreview.setPreserveRatio(true);
		runPreview.setFitHeight(100);
		Timeline run = GifPlayer.createAnimation(runPreview, GifPlayer.getRunKnight(), 0.08);
		run.setCycleCount(Timeline.INDEFINITE);
		run.play();
		
		ImageView charactor = new ImageView(new Image(ClassLoader.getSystemResource("picture/knight.png").toString()));
		charactor.setFitWidth(170);
		charactor.setFitHeight(170);
		
		Text name = new Text("Knight");
		name.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 60));
		name.setFill(Color.WHITE);
		Text detail = new Text("A knight of unwavering honor, felt the weight of\nthe world on his shoulders. His duty drove him\ninto the heart of the darkness.");
		detail.setFill(Color.WHITE);
		detail.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 20));
		
		animation.getChildren().addAll(idelPreview, attackPreview, runPreview);
		animationList.getChildren().addAll(frame,animation);
		characterInfo.getChildren().addAll(name, detail);
		mainCharacter.getChildren().addAll(charactor, characterInfo);
		selectedCharacter.getChildren().addAll(mainCharacter, animationList);
		
		ImageView knightIcons = new ImageView();
		knightIcons.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY) SfxPlayer.play(Sfx.SELECT);
		});
		knightIcons.setFitWidth(100);
		knightIcons.setFitHeight(100);
		knightIcons.setCursor(Cursor.CROSSHAIR);
		
		Timeline icon = GifPlayer.createAnimation(knightIcons, GifPlayer.getKnightIcons(), 0.25);
		icon.setCycleCount(Timeline.INDEFINITE);		
		
		ImageView unknown1 = new ImageView(new Image(ClassLoader.getSystemResource("picture/unknown.png").toString()));
		unknown1.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY) SfxPlayer.play(Sfx.DENY);
		});
		unknown1.setCursor(Cursor.CROSSHAIR);
		unknown1.setFitWidth(100);
		unknown1.setFitHeight(100);
		
		ImageView unknown2 = new ImageView(new Image(ClassLoader.getSystemResource("picture/unknown.png").toString()));
		unknown2.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY)SfxPlayer.play(Sfx.DENY);
		});
		unknown2.setCursor(Cursor.CROSSHAIR);
		unknown2.setFitWidth(100);
		unknown2.setFitHeight(100);
		characterList.getChildren().addAll(knightIcons, unknown1, unknown2);
		
		icon.play();
		
		root.getChildren().addAll(characterList, selectedCharacter);
		
		return root;
	}
}
