package backpack.item;

import java.util.HashMap;

import javafx.scene.image.Image;

public class ResourceLoader {
	private static HashMap<String, Resource> resourceMap;

	static {
		resourceMap = new HashMap<String, Resource>();
		resourceMap.put("apple", new Resource("apple", "apple.png", 1, 1));
	}

	public static Resource getResource(String name) {
		return resourceMap.get(name);
	}
}

class Resource {
	private final String name, path;
	private final int width, height;
	private Image image;

	public Resource(String name, String path, int width, int height) {
		this.name = name;
		this.path = path;
		this.width = width;
		this.height = height;
		this.image = null;
	}

	public Image getImage() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(path).toString());
			System.out.println("New image");
		}
		return image;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
