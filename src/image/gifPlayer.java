package image;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class gifPlayer {
	private static ArrayList<Image> idleKnight = new ArrayList<Image>(Arrays.asList(
		new Image(ClassLoader.getSystemResource("picture/player_Idle1.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle2.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle3.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle4.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle5.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle6.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle7.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle8.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle9.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Idle10.png").toString())
	));
	
	public static Timeline createKnightAnimation(ImageView imageView, double frameDuration) {
		Timeline timeline = new Timeline();
		for (int i = 0; i < idleKnight.size(); i++) {
	        final int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(frameDuration * i),
	            _ -> imageView.setImage(idleKnight.get(frameIndex))
	        );
	        timeline.getKeyFrames().add(keyFrame);
	    }
	    imageView.setImage(idleKnight.get(0));
	    return timeline;
	}
}
