package game.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IconLoader {
	public static EffectIcon newIcon(EffectType type, int amount) {
		Resource resource = IconList.getIconMap().get(type);
		return resource == null ? null : resource.newIcon(type, amount);
	}
}

class Resource {
	private String path;
	private Image image;

	public Resource(String path) {
		this.path = path;
	}

	public EffectIcon newIcon(EffectType type, int amount) {
		EffectIcon effectIcon = new EffectIcon(type);
		ImageView icon = effectIcon.getIcon();
		icon.setImage(getImage());
		Text text = effectIcon.getText();
		text.setText(Integer.toString(amount));
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 20));
		effectIcon.getChildren().addAll(icon, text);
		return effectIcon;
	}

	private Image getImage() {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(String.format("icons/%s", path)).toString());
			if (image == null) {
				System.err.println("ERROR: Could not load image from " + path);
			} else {
				System.out.println("Successfully loaded image from " + path);
			}
		}
		return image;
	}
}