package component;

import javafx.scene.Cursor;

public class Button extends javafx.scene.control.Button {
	final String IDLE_BUTTON_STYLE = "-fx-background-color: darkblue; -fx-text-fill: white;";
	final String HOVERED_BUTTON_STYLE = "-fx-background-color: blue; -fx-text-fill: white;";

	public Button(String arg0, int width, int height) {
		super(arg0);
		setCursor(Cursor.HAND);
		setStyle(IDLE_BUTTON_STYLE);
		setOnMouseEntered(_ -> setStyle(HOVERED_BUTTON_STYLE));
		setOnMouseExited(_ -> setStyle(IDLE_BUTTON_STYLE));

		setPrefSize(width, height);
	}
}