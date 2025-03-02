package image;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BackgroundVedioPlayer {
	private static MediaPlayer currentPlayer;
	
	public static MediaPlayer get(String name) {
		return imagePlayer.getMediaPlayer(name);
	}
	
	public static void play() {
		currentPlayer.play();
		currentPlayer.setOnEndOfMedia(() -> {
			currentPlayer.seek(Duration.ZERO);
			currentPlayer.play();
		});
	}

	public static void pause() {
		if (currentPlayer != null) {
			currentPlayer.pause();
		}
	}
	
	public static void stop() {
		if (currentPlayer != null) {
			currentPlayer.stop();
		}
	}

	public static MediaPlayer getCurrentPlayer() {
		return currentPlayer;
	}
}
