package component;

import java.util.HashMap;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sound.Sfx;
import sound.SfxPlayer;

public class GameButton extends StackPane {
	private static HashMap<GameButtonType, Resource> typeMap;
	private GameButtonType type;

	static {
		typeMap = new HashMap<GameButtonType, Resource>();
		typeMap.put(GameButtonType.CONTINUE, new Resource("continueGameButton1.png", "continueGameButton2.png"));
		typeMap.put(GameButtonType.NEW_GAME, new Resource("newGameButton1.png", "newGameButton2.png"));
		typeMap.put(GameButtonType.SETTING, new Resource("SettingButton1.png", "SettingButton2.png"));
		typeMap.put(GameButtonType.JOURNEY_ON, new Resource("journeyButton1.png", "journeyButton2.png"));
		typeMap.put(GameButtonType.CLOSE, new Resource("closeButton1.png", "closeButton2.png"));
		typeMap.put(GameButtonType.MENU, new Resource("menuButton1.png", "menuButton2.png"));
		typeMap.put(GameButtonType.EXIT, new Resource("exitButton1.png", "exitButton2.png"));
	}

	public GameButton(double width, double height, GameButtonType type) {
		super();
		this.type = type;
		setMaxSize(width, height);
		ImageView imageView = new ImageView(getIdleImage());
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		getChildren().setAll(imageView);

		setCursor(Cursor.HAND);
		setOnMouseEntered(event -> {
			SfxPlayer.play(Sfx.CLICK);
			imageView.setImage(getHoverImage());
		});
		setOnMouseExited(_ -> imageView.setImage(getIdleImage()));
	}

	private Image getIdleImage() {
		return typeMap.get(type).getIdleImage();
	}

	private Image getHoverImage() {
		return typeMap.get(type).getHoverImage();
	}
}

class Resource {
	private Image idleImage, hoverImage;
	private String idlePath, hoverPath;

	public Resource(String idlePath, String hoverPath) {
		super();
		this.idlePath = idlePath;
		this.hoverPath = hoverPath;
	}

	public Image getIdleImage() {
		if (idleImage == null) {
			idleImage = new Image(ClassLoader.getSystemResource(String.format("picture/%s", idlePath)).toString());
		}
		return idleImage;
	}

	public Image getHoverImage() {
		if (hoverImage == null) {
			hoverImage = new Image(ClassLoader.getSystemResource(String.format("picture/%s", hoverPath)).toString());
		}
		return hoverImage;
	}
}