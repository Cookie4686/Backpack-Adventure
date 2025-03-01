package sound;

import java.util.HashMap;

import javafx.scene.media.AudioClip;
import scene.popup.SettingPopup;

public class SfxPlayer {
	private static HashMap<Sfx, Resource> sfxMap;

	static {
		sfxMap = new HashMap<Sfx, Resource>();
		sfxMap.put(Sfx.SWORD, new Resource("sword.mp3"));
		sfxMap.put(Sfx.DENINE, new Resource("denied.mp3"));
		sfxMap.put(Sfx.MAGIC, new Resource("magic.mp3"));
		sfxMap.put(Sfx.EAT, new Resource("eat.mp3"));
		sfxMap.put(Sfx.THROW, new Resource("throw.mp3"));
		sfxMap.put(Sfx.GAMEOVER, new Resource("gameover.mp3"));
		sfxMap.put(Sfx.FIRE, new Resource("fire.mp3"));
		sfxMap.put(Sfx.POISON, new Resource("poison.mp3"));
		sfxMap.put(Sfx.ANGER, new Resource("anger.mp3"));
		sfxMap.put(Sfx.DAMAGE, new Resource("damage.mp3"));
		sfxMap.put(Sfx.DODGE, new Resource("dodge.mp3"));
		sfxMap.put(Sfx.HEAL, new Resource("heal.mp3"));
		sfxMap.put(Sfx.SHIELD, new Resource("shield.mp3"));
		sfxMap.put(Sfx.VAMPIRIC, new Resource("vampiric.mp3"));
		sfxMap.put(Sfx.THORN, new Resource("thorn.mp3"));
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