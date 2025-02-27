package sound;

import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import scene.popup.SettingPopup;

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
			BackgroundSongPlayer.pause();
			currentPlayer = new MediaPlayer(
					new Media(ClassLoader.getSystemResource(String.format("theme/%s", path)).toString()));
			currentPlayer.volumeProperty().bind(SettingPopup.getInstance().getThemeSlider().valueProperty());
			currentPlayer.play();
			currentPlayer.setOnEndOfMedia(() -> {
				currentPlayer.seek(Duration.ZERO);
				currentPlayer.play();
			});
		}
	}

	public static void stop() {
		if (currentPlayer != null) {
			currentPlayer.stop();
			BackgroundSongPlayer.play();
		}
	}
}
