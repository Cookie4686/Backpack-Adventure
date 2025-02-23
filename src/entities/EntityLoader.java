package entities;

import java.util.function.Supplier;


import javafx.scene.image.Image;

public class EntityLoader {
	public static Entity newEntity(String name) {
		Resource resource = EntityList.getItemMap().get(name);
		return resource == null ? null : resource.newEntity();
	}
}

class Resource {
	private Supplier<Entity> supplier;
	private String path;
	private Image image;

	public Resource(Supplier<Entity> supplier, String path) {
		this.supplier = supplier;
		this.path = path;
	}

	public Entity newEntity() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(path).toString());
		}
		Entity entity = supplier.get();
		entity.initialize(image);
		return entity;
	}
}