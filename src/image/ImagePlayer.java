package image;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImagePlayer {
	private static HashMap<String, Resource> imageMap;

	static {
		imageMap = new HashMap<String, Resource>();
		imageMap.put("knight", new Resource("knight.png"));
		imageMap.put("knightIconFrame", new Resource("knightIconFrame.png"));
	}

	public static ImageView getImageView(String name) {
		Resource resource = imageMap.get(name);
		return new ImageView(resource.getImage());
	}
}

class Resource {
	private String path;
	private Image image;

	public Resource(String path) {
		super();
		this.path = path;
	}

	public Image getImage() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(String.format("picture/%s", path)).toString());
		}
		return image;
	}
}