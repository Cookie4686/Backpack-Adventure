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
	
	public static void menu() {
		currentPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource(String.format("song/Menu bg.mp3")).toString()));
		currentPlayer.play();
		currentPlayer.getOnRepeat();
	}

	public static void play() {
		currentPlayer.play();
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
