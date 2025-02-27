package sound;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import scene.popup.SettingPopup;

public class BackgroundSongPlayer {
	private static ArrayList<String> paths;
	private static int currentIndex;
	private static MediaPlayer currentPlayer;

	static {
		paths = new ArrayList<String>();
		currentIndex = 0;
		paths.add("CHIC - LeFreak.mp3");
		paths.add("Jamiroquai - CosmicGirl.mp3");
		paths.add("RickAstley - NeverGonnaGiveYouUp.mp3");
		paths.add("Redtenbacher's Funkestra - Funktionality.mp3");
	}

	public static void autoplay() {
		currentPlayer = new MediaPlayer(
				new Media(ClassLoader.getSystemResource(String.format("song/%s", paths.get(currentIndex))).toString()));
		currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getMusicSlider().valueProperty());
		currentPlayer.play();
		currentPlayer.setOnEndOfMedia(() -> {
			currentIndex = currentIndex + 1 == paths.size() ? 0 : currentIndex + 1;
			autoplay();
		});
	}

	public static void play() {
		if (currentPlayer != null) {
			currentPlayer.play();
		} else {
			autoplay();
		}
	}

	public static void pause() {
		if (currentPlayer != null) {
			currentPlayer.pause();
		}
	}

	public static MediaPlayer getCurrentPlayer() {
		return currentPlayer;
	}
}
