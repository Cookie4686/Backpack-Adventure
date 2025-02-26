package game.map;

import java.util.HashMap;

import javafx.scene.image.Image;

public class MarkerLoader {
	private static HashMap<MapMarker, Resource> markerMap;
	static {
		markerMap = new HashMap<MapMarker, Resource>();
		markerMap.put(MapMarker.DOOR, new Resource("door.png"));
		markerMap.put(MapMarker.MONSTER, new Resource("skull.png"));
		markerMap.put(MapMarker.PLAYER, new Resource("Items/apple.png"));
	}

	public static Image getImage(MapMarker marker) {
		Resource resource = markerMap.get(marker);
		return resource == null ? null : resource.getImage();
	}
}

class Resource {
	private Image image;
	private String path;

	public Resource(String path) {
		super();
		this.path = path;
	}

	public Image getImage() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(path).toString());
		}
		return image;
	}
}
