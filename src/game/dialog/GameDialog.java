package game.dialog;

import application.Main;
import game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameDialog extends BorderPane {
	private static Image frameImage;
	private Text dialogText;
	private VBox optionBox;

	public GameDialog(String title) {
		super();
		StackPane.setAlignment(this, Pos.CENTER);
		maxWidthProperty().bind(Main.root.widthProperty().multiply(0.55));

		HBox topBox = new HBox();
		topBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		topBox.translateYProperty().bind(topBox.heightProperty().divide(-2));
		topBox.setPadding(new Insets(8));
		topBox.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(4), Insets.EMPTY)));
		Text titleText = new Text(title);
		titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 48));
		topBox.getChildren().setAll(titleText);
		setAlignment(topBox, Pos.CENTER);

		VBox root = new VBox();
		root.translateYProperty().bind(topBox.translateYProperty());
		root.setPadding(new Insets(16, 32, 16, 32));
		root.maxWidthProperty().bind(maxWidthProperty().subtract(64));
		root.setMaxHeight(USE_PREF_SIZE);
		root.setSpacing(32);
		dialogText = new Text();
		dialogText.wrappingWidthProperty().bind(root.maxWidthProperty());
		dialogText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 24));
		optionBox = new VBox();
		optionBox.setMaxHeight(USE_PREF_SIZE);
		optionBox.setSpacing(16);
		root.getChildren().setAll(dialogText, optionBox);

		maxHeightProperty().bind(root.heightProperty());
		ImageView frame = new ImageView(getFrameImage());
		frame.fitWidthProperty().bind(maxWidthProperty());
		frame.fitHeightProperty().bind(maxHeightProperty().add(64));
		getChildren().add(frame);
		setTop(topBox);
		setCenter(root);
	}

	public void setText(String text) {
		dialogText.setText(text);
	}

	public void addOption(String textStr, EventHandler<? super MouseEvent> onMouseClickedHandler) {
		Text text = new Text(textStr);
		text.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 24));
		HBox hBox = new HBox(text);
		hBox.setPadding(new Insets(4));
		hBox.setBackground(new Background(new BackgroundFill(Color.CORNSILK, new CornerRadii(2), Insets.EMPTY)));
		hBox.setCursor(Cursor.HAND);
		hBox.setOnMouseClicked(onMouseClickedHandler);
		optionBox.getChildren().add(hBox);
	}

	public void show() {
		if (!Game.getInstance().getChildren().contains(this)) {
			Game.getInstance().getChildren().add(this);
		}
	}

	public void hide() {
		Game.getInstance().getChildren().remove(this);
	}

	private static Image getFrameImage() {
		if (frameImage == null) {
			frameImage = new Image(ClassLoader.getSystemResource("picture/chooseCharacterFrame.png").toString());
		}
		return frameImage;
	}

}
