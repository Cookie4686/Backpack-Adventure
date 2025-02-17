package scene.popup;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Popup extends VBox {
	public Popup(Color color) {
		super();
		init(color);
	}

	public Popup(Color color, Node... arg0) {
		super(arg0);
		init(color);
	}

	private void init(Color color) {
		maxWidthProperty().bind(Main.root.widthProperty().multiply(0.8));
		maxHeightProperty().bind(Main.root.heightProperty().multiply(0.7));
		setAlignment(Pos.CENTER);
		setSpacing(20);
		setBackground(new Background(new BackgroundFill(color, new CornerRadii(5), new Insets(4))));
	}
}
