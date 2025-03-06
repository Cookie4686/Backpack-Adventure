package sound;

import java.util.HashMap;

import game.util.Effect;
import javafx.scene.media.AudioClip;
import scene.popup.SettingPopup;

public class SfxPlayer {
	private static HashMap<Sfx, Resource> sfxMap;

	static {
		sfxMap = new HashMap<Sfx, Resource>();
		sfxMap.put(Sfx.SWORD, new Resource("sword.mp3"));
		sfxMap.put(Sfx.DENY, new Resource("denied.mp3"));
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
		sfxMap.put(Sfx.DEAD, new Resource("dead.mp3"));
		sfxMap.put(Sfx.GAMESTART, new Resource("gamestart.mp3"));
		sfxMap.put(Sfx.SELECT, new Resource("select.mp3"));
		sfxMap.put(Sfx.CLICK, new Resource("click.mp3"));
		sfxMap.put(Sfx.STONERUN, new Resource("stonerun.mp3"));
		sfxMap.put(Sfx.GRASSRUN, new Resource("grassrun.mp3"));
		sfxMap.put(Sfx.DRAG, new Resource("drag.mp3"));
		sfxMap.put(Sfx.OUTSIDEOFBACKPACK, new Resource("outsideofbackpack.mp3"));
		sfxMap.put(Sfx.INSIDEOFBACKPACK, new Resource("insideofbackpack.mp3"));
		sfxMap.put(Sfx.MAP, new Resource("map.mp3"));		
	}

	public static void play(Sfx sfx) {
		Resource resource = sfxMap.get(sfx);
		if (resource != null) {
			AudioClip audioClip = resource.getAudioClip();
			audioClip.setVolume(SettingPopup.getInstance().getSfxSlider().getValue());
			audioClip.play();
		}
	}

	public static void playByEffect(Effect effect) {
		switch (effect.getType()) {
		case FIRE -> play(Sfx.FIRE);
		case ANGER -> play(Sfx.ANGER);
		case DODGE -> play(Sfx.DODGE);
		case HEAL -> play(Sfx.HEAL);
		case REGEN -> play(Sfx.HEAL);
		case POISON -> play(Sfx.POISON);
		case SHIELD -> play(Sfx.SHIELD);
		case THORN -> play(Sfx.THORN);
		case VAMPIRIC -> play(Sfx.VAMPIRIC);
		case STUNNED -> play(Sfx.FIRE);
		case SUMMONER -> play(Sfx.FIRE);
		default			-> {}
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