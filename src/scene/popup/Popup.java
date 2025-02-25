package scene.popup;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Popup extends BorderPane {
	private HBox bottomBox;

	public Popup(String title) {
		super();
		maxWidthProperty().bind(Main.root.widthProperty().multiply(0.8));
		maxHeightProperty().bind(Main.root.heightProperty().multiply(0.7));
		setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, new CornerRadii(4), Insets.EMPTY)));

		HBox topBox = new HBox();
		topBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		topBox.translateYProperty().bind(topBox.heightProperty().divide(-2));
		topBox.setPadding(new Insets(8));
		topBox.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, new CornerRadii(4), Insets.EMPTY)));
		Text titleText = new Text(title);
		titleText.setFont(Font.font("Consolas", FontWeight.BOLD, 48));
		topBox.getChildren().setAll(titleText);
		setAlignment(topBox, Pos.CENTER);
		setTop(topBox);

		bottomBox = new HBox();
		bottomBox.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
		bottomBox.translateYProperty().bind(bottomBox.heightProperty().divide(2));
		setAlignment(bottomBox, Pos.CENTER);
		setBottom(bottomBox);
	}

	public void show() {
		Main.root.getChildren().add(this);
	}

	public void hide() {
		Main.root.getChildren().remove(this);
	}

	public HBox getBottomBox() {
		return bottomBox;
	}
}
