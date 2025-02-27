package sound;

import java.util.HashMap;

import javafx.scene.media.AudioClip;
import scene.popup.SettingPopup;

public class SfxPlayer {
	private static HashMap<Sfx, Resource> sfxMap;

	static {
		sfxMap = new HashMap<Sfx, Resource>();
		sfxMap.put(Sfx.SWORD, new Resource("sword.mp3"));
	}

	public static void play(Sfx sfx) {
		Resource resource = sfxMap.get(sfx);
		if (resource != null) {
			AudioClip audioClip = resource.getAudioClip();
			audioClip.setVolume(SettingPopup.getInstance().getSfxSlider().getValue());
			audioClip.play();
		}
	}
}

class Resource {
	private AudioClip audioClip;
	private String path;

	public Resource(String path) {
		super();
		this.path = path;
	}

	public AudioClip getAudioClip() {
		if (audioClip == null) {
			audioClip = new AudioClip(ClassLoader.getSystemResource(String.format("sfx/%s", path)).toString());
		}
		return audioClip;
	}
}