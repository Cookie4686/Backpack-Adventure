package game.backpack;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Backpack extends HBox {
	private static Backpack instance;

	public Backpack() {
		super();
		setAlignment(Pos.TOP_CENTER);
		getChildren().add(SlotPane.getInstance());
	}

	public static Backpack getInstance() {
		if (instance == null) {
			instance = new Backpack();
		}
		return instance;
	}
}
