package component;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SettingSlider extends HBox {
	private Text text;
	private Slider slider;

	public SettingSlider(String name) {
		super();
		text = new Text(name);
		text.setFont(Font.font("Consolas", FontWeight.BOLD, 24));
		slider = new Slider(0, 1, 0.5);

		setAlignment(Pos.CENTER);
		setSpacing(12);
		getChildren().setAll(text, slider);
	}

	public Slider getSlider() {
		return slider;
	}
}
