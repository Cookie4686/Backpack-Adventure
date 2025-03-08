package entities;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Npc extends VBox {
	private ImageView imageView;
	private ArrayList<Image> idleframes;
	private Timeline idleTimeline;
	private static Npc instance;

	public Npc() {
		imageView = new ImageView();
		idleframes = new ArrayList<Image>(
				Arrays.asList(new Image(ClassLoader.getSystemResource("frames/balancing1.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing2.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing3.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing4.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing5.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing6.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing7.png").toString()),
						new Image(ClassLoader.getSystemResource("frames/balancing8.png").toString())));
		idleTimeline = createAnimation(idleframes, 0.15);
		idleTimeline.setCycleCount(Timeline.INDEFINITE);
		idleTimeline.play();
		this.getChildren().add(imageView);
	}

	private Timeline createAnimation(ArrayList<Image> images, double frameDuration) {
		Timeline timeline = new Timeline();
		for (int i = 0; i < images.size(); i++) {
			final int frameIndex = i;
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(frameDuration * i),
					_ -> imageView.setImage(images.get(frameIndex)));
			timeline.getKeyFrames().add(keyFrame);
		}
		imageView.setImage(images.get(0));
		return timeline;
	}

	public static Npc getInstance() {
		if (instance == null) {
			instance = new Npc();
		}
		return instance;
	}

	public void setInstance(Npc instance) {
		Npc.instance = instance;
	}

}
