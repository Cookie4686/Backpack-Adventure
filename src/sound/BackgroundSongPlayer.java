package sound;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import scene.popup.SettingPopup;

public class BackgroundSongPlayer {
	private static ArrayList<String> fight, floor;
	private static MediaPlayer currentPlayer;
	
	static {
		fight = new ArrayList<String>();
		fight.add("Fight1.mp3");
		
		floor = new ArrayList<String>();
		floor.add("Floor1.mp3");
	}
	
	public static void menu() {
		if (currentPlayer != null) currentPlayer.stop();
		currentPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource(String.format("song/Menu bg.mp3")).toString()));
		currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getMusicSlider().valueProperty());
		play();
	}
	
	public static void fight(int level) {
		currentPlayer.stop();
		currentPlayer = new MediaPlayer(
				new Media(ClassLoader.getSystemResource(String.format("song/%s", fight.get(level))).toString()));
		currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getMusicSlider().valueProperty());
		currentPlayer.play();
		play();
	}
	
	public static void floor(int level) {
		currentPlayer.stop();
		currentPlayer = new MediaPlayer(
				new Media(ClassLoader.getSystemResource(String.format("song/%s", floor.get(level))).toString()));
		currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getMusicSlider().valueProperty());
		play();
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
