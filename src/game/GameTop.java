package game;

import game.backpack.Backpack;
import game.item.Item;
import game.map.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameTop extends HBox {
	private static GameTop instance;
	private boolean isBackpack;

	public GameTop() {
		super();
		isBackpack = true;
		setAlignment(Pos.TOP_CENTER);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		getChildren().setAll(Backpack.getInstance());
	}

	public void useBackpack() {
		isBackpack = true;
		Map.getInstance().hide();
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(true);
		}
	}

	public void useMap() {
		isBackpack = false;
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(false);
		}
		Map.getInstance().show();
	}

	public boolean isBackpack() {
		return isBackpack;
	}

	public static GameTop getInstance() {
		if (instance == null) {
			instance = new GameTop();
		}
		return instance;
	}
}
