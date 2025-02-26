package sound;

import java.util.HashMap;

import javafx.scene.media.AudioClip;
import scene.popup.SettingPopup;

public class SfxPlayer {
	private static HashMap<String, String> sfxMap;

	static {
		sfxMap = new HashMap<String, String>();
		sfxMap.put("sword", "sword.mp3");
	}

	public static void play(String name) {
		String path = sfxMap.get(name);
		if (path != null) {
			AudioClip audioClip = new AudioClip(
					ClassLoader.getSystemResource(String.format("sfx/%s", path)).toString());
			audioClip.setVolume(SettingPopup.getInstance().getSfxSlider().getValue());
			audioClip.play();
		}
	}
}
