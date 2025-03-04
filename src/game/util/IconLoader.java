package game.util;

import java.util.function.Supplier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class IconLoader {
	public static EffectIcon newIcon(EffectType type, int amount) {
		Resource resource = IconList.getIconMap().get(type);
		return resource == null ? null : resource.newIcon(amount);
	}
}

class Resource {
	private Supplier<EffectIcon> supplier;
	private String path;
	private Image image;

	public Resource(Supplier<EffectIcon> supplier, String path) {
		this.supplier = supplier;
		this.path = path;
	}

	public EffectIcon newIcon(int amount) {
		if (image == null) {
			image = new Image(ClassLoader.getSystemResource(path).toString());
			if (image == null) {
				System.err.println("ERROR: Could not load image from " + path);
			} else {
				System.out.println("Successfully loaded image from " + path);
			}
		}
		EffectIcon effectIcon = supplier.get();
		ImageView icon = effectIcon.getIcon();
		Text text = effectIcon.getText();
		icon.setImage(image);
		text.setText(String.valueOf(amount));
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 20));
		effectIcon.getChildren().addAll(icon,text);
		return effectIcon;
	}
}