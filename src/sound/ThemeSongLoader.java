package sound;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ThemeSongLoader {
	private static HashMap<String, String> themeMap;
	private static MediaPlayer currentPlayer;

	static {
		themeMap = new HashMap<String, String>();
		themeMap.put("boss1", "TobyFox - Megalovania.mp3");
		themeMap.put("boss2", "TheImmortals - TechnoSyndrome.mp3");
	}

	public static void play(String name) {
		String path = themeMap.get(name);
		if (path != null) {
			fadeOut(BackgroundSongLoader.getCurrentPlayer(), () -> {
				BackgroundSongLoader.getCurrentPlayer().pause();
			});
			currentPlayer = new MediaPlayer(
					new Media(ClassLoader.getSystemResource(String.format("sound/%s", path)).toString()));
			currentPlayer.play();
			currentPlayer.setOnEndOfMedia(() -> {
				currentPlayer.seek(Duration.ZERO);
				currentPlayer.play();
			});
		}
	}

	public static void stop() {
		if (currentPlayer != null) {
			fadeOut(currentPlayer, () -> {
				currentPlayer.stop();
			});
			BackgroundSongLoader.getCurrentPlayer().setVolume(1);
			BackgroundSongLoader.getCurrentPlayer().play();
		}
	}

	private static void fadeOut(MediaPlayer player, Runnable after) {
		Thread thread = new Thread(() -> {
			final int SMOOTHNESS = 75;
			double interval = player.getVolume() / SMOOTHNESS;
			// Fade out background song
			for (int i = 0; i < SMOOTHNESS; i++) {
				Platform.runLater(() -> {
					player.setVolume(player.getVolume() - interval);
				});
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			after.run();
		});
		thread.start();
	}
}
