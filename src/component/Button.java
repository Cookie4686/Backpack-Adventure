package component;

import javafx.scene.Cursor;

public class Button extends javafx.scene.control.Button {
	final String IDLE_BUTTON_STYLE = "-fx-background-color: darkblue; -fx-text-fill: white;";
	final String HOVERED_BUTTON_STYLE = "-fx-background-color: blue; -fx-text-fill: white;";

	public Button(ButtonSize size) {
		super();
		initialize(size);
	}

	public Button(String arg0, ButtonSize size) {
		super(arg0);
		initialize(size);
	}

	public void initialize(ButtonSize size) {
		setCursor(Cursor.HAND);
		setStyle(IDLE_BUTTON_STYLE);
		setOnMouseEntered(_ -> setStyle(HOVERED_BUTTON_STYLE));
		setOnMouseExited(_ -> setStyle(IDLE_BUTTON_STYLE));

		switch (size) {
		case LARGE	-> setPrefWidth(128);
		case MEDIUM	-> setPrefWidth(64);
		case SMALL	-> setPrefWidth(32);
		}
		setPrefHeight(getPrefWidth() / 4);
	}

}