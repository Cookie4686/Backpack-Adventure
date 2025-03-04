package image;

import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoPlayer {
	private static MediaPlayer currentPlayer;
	private static HashMap<String, Resource> mediaMap;
	
	static {
		mediaMap.put("menuBackground", new Resource("menuBackground.mp4"));
	}
	
	private static Media getMedia(String path) {
		return new Media(ClassLoader.getSystemResource(String.format("theme/%s", path)).toString());
	}
	
	public static MediaView getView(String name) {
		if (currentPlayer != null) currentPlayer.stop();
		currentPlayer = new MediaPlayer(getMedia(name));
		currentPlayer.setMute(true);
		return new MediaView(currentPlayer);
	}
	
	public static void play() {
		currentPlayer.play();
	}
	
	public static MediaPlayer getCurrentPlayer() {
		return currentPlayer;
	}
}
