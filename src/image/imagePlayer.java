package image;

import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class imagePlayer {
	private static HashMap<String, Resource> mediaMap;
	
	static {
		mediaMap.put("menuBackground", new Resource("menuBackground.mp4"));
	}
	
	public static MediaPlayer getMediaPlayer(String name) {
		Resource resource = mediaMap.get(name);
		if (resource != null) {
			MediaPlayer media = new MediaPlayer(resource.getMediaClip());
			return media;
		}
		return null;
	}
}

class Resource {
	private Media mediaClip;
	private String path;
	
	public Resource(String path) {
		super();
		this.path = path;
	}
	
	public Media getMediaClip() {
		if (mediaClip == null) {
			mediaClip =  new Media(ClassLoader.getSystemResource(String.format("theme/%s", path)).toString());
		}
		return mediaClip;
	}
}