package entities;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Npc extends VBox{
	private ImageView imageView;
	private ArrayList<Image> idleFrames;
	private Timeline idleTimeline;
	
	public Npc() {
		imageView = new ImageView();
		idleFrames = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("Frames/balancing1.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing2.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing3.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing4.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing5.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing6.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing7.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/balancing8.png").toString())
		));
		idleTimeline = createAnimation(idleFrames,0.1);
		idleTimeline.setCycleCount(Timeline.INDEFINITE);
		idleTimeline.play();
		this.getChildren().add(imageView);
	}
	
	private Timeline createAnimation(ArrayList<Image> images, double frameDuration) {
		Timeline timeline = new Timeline();
		for (int i = 0; i < images.size(); i++) {
	        final int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(frameDuration * i),
	            event -> imageView.setImage(images.get(frameIndex))
	        );
	        timeline.getKeyFrames().add(keyFrame);
	    }
	    imageView.setImage(images.get(0));
	    return timeline;
	}
}
