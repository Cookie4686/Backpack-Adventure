package component;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

public class VolumeSlider extends HBox {
	private static Image mute, vol1, vol2, vol3;
	private double lastValue;
	private Slider slider;

	public VolumeSlider(double iconSize) {
		this(iconSize, false);
	}

	public VolumeSlider(double iconSize, boolean isOnMute) {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(8);

		lastValue = 0.5;
		slider = new Slider(0, 1, isOnMute ? 0 : lastValue);
		ImageView imageView = new ImageView();
		imageView.setFitWidth(iconSize);
		imageView.setFitHeight(iconSize);
		imageView.setCursor(Cursor.HAND);
		imageView.setPickOnBounds(true);
		imageView.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (slider.getValue() == 0) {
					slider.setValue(lastValue);
				} else {
					lastValue = slider.getValue();
					slider.setValue(0);
				}
			}

		});
		slider.valueProperty().addListener((_, _, newValue) -> {
			calcIcon(imageView, newValue.doubleValue());
		});
		calcIcon(imageView, slider.getValue());
		getChildren().setAll(imageView, slider);
	}

	private static void calcIcon(ImageView muteButtonView, double value) {
		if (value >= 0.67) {
			if (vol3 == null) {
				vol3 = new Image(
						ClassLoader.getSystemResource(String.format("component/%s", "volume3.png")).toString());
			}
			muteButtonView.setImage(vol3);
		} else if (value >= 0.33) {
			if (vol2 == null) {
				vol2 = new Image(
						ClassLoader.getSystemResource(String.format("component/%s", "volume2.png")).toString());
			}
			muteButtonView.setImage(vol2);
		} else if (value > 0) {
			if (vol1 == null) {
				vol1 = new Image(
						ClassLoader.getSystemResource(String.format("component/%s", "volume1.png")).toString());
			}
			muteButtonView.setImage(vol1);
		} else {
			if (mute == null) {
				mute = new Image(
						ClassLoader.getSystemResource(String.format("component/%s", "volumeX.png")).toString());
			}
			muteButtonView.setImage(mute);
		}
	}

	public Slider getSlider() {
		return slider;
	}
}
