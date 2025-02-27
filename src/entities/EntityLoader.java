package entities;

import java.util.ArrayList;
import java.util.function.Supplier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import logic.handler.EntityHandler;

public class EntityLoader {
	public static Entity newEntity(String name) {
		Resource resource = EntityList.getItemMap().get(name);
		return resource == null ? null : resource.newEntity();
	}
}

class Resource {
	private Supplier<Entity> supplier;
	private ArrayList<String> paths;
	private ArrayList<Image> images;

	public Resource(Supplier<Entity> supplier, ArrayList<String> arrayList) {
		this.supplier = supplier;
		this.paths = arrayList;
	}

	public Entity newEntity() {
		if (images == null) {
			images = new ArrayList<Image>();
			for(String p : paths) {
				Image image = new Image(ClassLoader.getSystemResource(p).toString());
				if (image == null) {
				    System.err.println("ERROR: Could not load image from " + p);
				} else {
				    System.out.println("Successfully loaded image from " + p);
				}
				images.add(image);
			}
		}
		Entity entity = supplier.get();
		ImageView imageView = entity.getImageView();
		
		Timeline timeline = new Timeline();
		for (int i = 0; i < images.size(); i++) {
	        final int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(0.2 * i),
	            event -> imageView.setImage(images.get(frameIndex))
	        );
	        timeline.getKeyFrames().add(keyFrame);
	    }
		timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	    imageView.setImage(images.get(0));
		imageView.setOnMousePressed(event -> EntityHandler.handleMouseClicked(entity));
		entity.getChildren().add(imageView);
		entity.setTimeline(timeline);
		return entity;
	}
}