package component;

import entities.Player;
import interfaces.ReRenderable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EnergyOrb extends StackPane implements ReRenderable {
	private static int SIZE = 40;
	private static Image image;
	private Text text;

	public EnergyOrb() {
		super();

		if (image == null) {
			image = new Image(ClassLoader.getSystemResource("component/energy.png").toString());
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(SIZE);
		imageView.setFitHeight(SIZE);
		text = new Text();
		text.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, SIZE * 0.75));
		text.setFill(Color.DARKBLUE);

		getChildren().setAll(imageView, text);
	}

	@Override
	public void render() {
		text.setText(Integer.toString(Player.getInstance().getEnergy()));
	}
}
