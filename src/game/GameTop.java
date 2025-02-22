package game;

import game.backpack.Backpack;
import game.item.Item;
import game.map.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GameTop extends HBox {
	private static GameTop instance;
	private boolean isBackpack;

	public GameTop() {
		super();
		setAlignment(Pos.TOP_CENTER);
		isBackpack = true;
		getChildren().setAll(Backpack.getInstance());
	}

	public void useBackpack() {
		isBackpack = true;
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(true);
		}
		getChildren().setAll(Backpack.getInstance());
	}

	public void useMap() {
		isBackpack = false;
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(false);
		}
		getChildren().setAll(Map.getInstance());
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
