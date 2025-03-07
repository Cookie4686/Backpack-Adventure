package component;

import javafx.scene.Cursor;

public class Button extends javafx.scene.control.Button {
	private final String IDLE_BUTTON_STYLE = "-fx-background-color: darkblue; -fx-text-fill: white;";
	private final String HOVER_BUTTON_STYLE = "-fx-background-color: blue; -fx-text-fill: white;";

	public Button(String text, int width, int height) {
		super(text);
		setPrefSize(width, height);
		setCursor(Cursor.HAND);
		setStyle(IDLE_BUTTON_STYLE);
		setOnMouseEntered(_ -> setStyle(HOVER_BUTTON_STYLE));
		setOnMouseExited(_ -> setStyle(IDLE_BUTTON_STYLE));

	}
}