package sound;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import scene.popup.SettingPopup;

public class BackgroundSongPlayer {
	private static ArrayList<String> fight, floor;
	private static MediaPlayer currentPlayer;

	static {
		fight = new ArrayList<String>();
		fight.add("Fight1.mp3");
		fight.add("Fight1.mp3");
		fight.add("Fight1.mp3");
		fight.add("Fight1.mp3");
		fight.add("Fight1.mp3");
		floor = new ArrayList<String>();
		floor.add("Floor1.mp3");
		floor.add("Floor2.mp3");
		floor.add("Floor3.mp3");
	}

	public static void menu() {
		runNewPlayer("Menu bg.mp3");
	}

	public static void fight(int level) {
		runNewPlayer(fight.get(level));
	}

	public static void floor(int level) {
		runNewPlayer(floor.get(level));
	}

	private static void runNewPlayer(String name) {
		stop();
		currentPlayer = new MediaPlayer(
				new Media(ClassLoader.getSystemResource(String.format("song/%s", name)).toString()));
		currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getMusicSlider().valueProperty());
		currentPlayer.volumeProperty().addListener((_, oldValue, newValue) -> {
			if (oldValue.doubleValue() == 0) {
				play();
			}
			if (newValue.doubleValue() == 0) {
				pause();
			}
		});
		currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		play();
	}

	public static void play() {
		if (currentPlayer != null) {
			currentPlayer.play();
		}
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
}
