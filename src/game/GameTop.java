package game;

import game.backpack.Backpack;
import game.item.Item;
import game.map.Map;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameTop extends HBox {
	private static GameTop instance;
	private static StackPane map;
	private boolean isBackpack;

	public GameTop() {
		super();
		isBackpack = true;
		setAlignment(Pos.TOP_CENTER);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		getChildren().setAll(Backpack.getInstance());
		
		ImageView mapBackground = new ImageView(new Image(ClassLoader.getSystemResource("picture/map.png").toString()));
		mapBackground.setPreserveRatio(true);
		mapBackground.setFitWidth(400);
		getMap().setAlignment(Pos.TOP_CENTER);
		StackPane.setAlignment(map, Pos.TOP_CENTER);
		map.setMaxHeight(400);
		map.setMaxWidth(400);
		map.setBorder(new Border(
				new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		map.getChildren().setAll(mapBackground, Map.getInstance());
	}

	public void useBackpack() {
		isBackpack = true;
		Map.getInstance().hide();
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(true);
		}
		
		Game.getInstance().getChildren().remove(map);
	}

	public void useMap() {
		isBackpack = false;
		for (Item item : Game.getInstance().getItemsInGame()) {
			item.setVisible(false);
		}
		
		if (!Game.getInstance().getChildren().contains(map)) {
			Game.getInstance().getChildren().add(map);
		}
//		Map.getInstance().show();
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

	public static StackPane getMap() {
		if (map == null) map = new StackPane();
		return map;
	}
}
