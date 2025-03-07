package game.util;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EffectIcon extends VBox {
	private ImageView icon;
	private Text text;
	private EffectType type;
	private FadeTransition fadeIn;

	public EffectIcon(EffectType type) {
		this.setAlignment(Pos.CENTER);
		icon = new ImageView();
		text = new Text();
		this.type = type;
		this.setOpacity(0.0);
		fadeIn = new FadeTransition(Duration.seconds(0.5), this);
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);
	}

	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text amount) {
		this.text = amount;
	}

	public EffectType getType() {
		return type;
	}

	public FadeTransition getFadeIn() {
		return fadeIn;
	}
}
