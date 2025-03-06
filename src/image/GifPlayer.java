package image;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GifPlayer {
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
	
	private static ArrayList<Image> knightIcons = new ArrayList<Image>(Arrays.asList(
		new Image(ClassLoader.getSystemResource("picture/knightIconFrame1.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/knightIconFrame2.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/knightIconFrame3.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/knightIconFrame4.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/knightIconFrame1.png").toString())
	));
	
	private static ArrayList<Image> attackKnight = new ArrayList<Image>(Arrays.asList(
		new Image(ClassLoader.getSystemResource("picture/player_Attack1.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Attack2.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Attack3.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Attack4.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Attack4.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Attack1.png").toString())
	));
	
	private static ArrayList<Image> runKnight = new ArrayList<Image>(Arrays.asList(
		new Image(ClassLoader.getSystemResource("picture/player_Run1.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run2.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run3.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run4.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run5.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run6.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run7.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run8.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run9.png").toString()),
		new Image(ClassLoader.getSystemResource("picture/player_Run1.png").toString())
	));
	
	private static ArrayList<Image> selectIcons = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("picture/select1.png").toString()),
			new Image(ClassLoader.getSystemResource("picture/select2.png").toString()),
			new Image(ClassLoader.getSystemResource("picture/select3.png").toString()),
			new Image(ClassLoader.getSystemResource("picture/select4.png").toString()),
			new Image(ClassLoader.getSystemResource("picture/select1.png").toString())
		));
	
	private static ArrayList<Image> menuBackground = new ArrayList<Image>(Arrays.asList(
		new Image(ClassLoader.getSystemResource("theme/menuBg1.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg2.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg3.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg4.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg5.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg6.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg7.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg8.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg9.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg10.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg11.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg12.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg13.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg14.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg15.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg16.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg17.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg18.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg19.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg20.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg21.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg22.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg23.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg24.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg25.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg26.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg27.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg28.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg29.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg30.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg31.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg32.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg33.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg34.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg35.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg36.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg37.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg38.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg39.png").toString()),
		new Image(ClassLoader.getSystemResource("theme/menuBg1.png").toString())
	));
	
	public static Timeline createAnimation(ImageView imageView, ArrayList<Image> arr, double frameDuration) {
		Timeline timeline = new Timeline();
		for (int i = 0; i < arr.size(); i++) {
	        final int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(frameDuration * i),
	            _ -> imageView.setImage(arr.get(frameIndex))
	        );
	        timeline.getKeyFrames().add(keyFrame);
	    }
	    imageView.setImage(arr.get(0));
	    return timeline;
	}

	public static ArrayList<Image> getIdleKnight() {
		return idleKnight;
	}

	public static ArrayList<Image> getKnightIcons() {
		return knightIcons;
	}

	public static ArrayList<Image> getMenuBackground() {
		return menuBackground;
	}

	public static ArrayList<Image> getAttackKnight() {
		return attackKnight;
	}

	public static ArrayList<Image> getRunKnight() {
		return runKnight;
	}

	public static ArrayList<Image> getSelectIcons() {
		return selectIcons;
	}
}
