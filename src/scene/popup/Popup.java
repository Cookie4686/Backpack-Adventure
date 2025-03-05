package scene.popup;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Popup extends BorderPane {
	private HBox bottomBox;
	private static Image frameImage;

	public Popup(String title) {
		super();
		StackPane.setAlignment(this, Pos.CENTER);
		maxWidthProperty().bind(Main.root.widthProperty().multiply(0.7));
		maxHeightProperty().bind(Main.root.heightProperty().multiply(0.8));
		ImageView frame = new ImageView(getFrameImage());
		getChildren().add(frame);

		HBox topBox = new HBox();
		topBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		topBox.translateYProperty().bind(topBox.heightProperty().divide(-2));
		topBox.setPadding(new Insets(8));
		topBox.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(4), Insets.EMPTY)));
		Text titleText = new Text(title);
		titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 48));
		topBox.getChildren().setAll(titleText);
		setAlignment(topBox, Pos.CENTER);
		setTop(topBox);

		bottomBox = new HBox();
		bottomBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		bottomBox.setSpacing(32);
		bottomBox.translateYProperty().bind(bottomBox.heightProperty().divide(2));
		setAlignment(bottomBox, Pos.CENTER);
		setBottom(bottomBox);
	}

	public void show() {
		if (!Main.root.getChildren().contains(this)) {
			Main.root.getChildren().add(this);
		}
	}

	public void hide() {
		Main.root.getChildren().remove(this);
	}

	public HBox getBottomBox() {
		return bottomBox;
	}

	private static Image getFrameImage() {
		if (frameImage == null) {
			frameImage = new Image(ClassLoader.getSystemResource("picture/chooseCharacterFrame.png").toString());
		}
		return frameImage;
	}
}
