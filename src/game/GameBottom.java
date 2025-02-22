package game;

import entities.Entity;
import entities.Player;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import logic.FightLogic;

public class GameBottom extends HBox implements ReRenderable {
	private static GameBottom instance;
	private HBox enemyBox;

	public GameBottom() {
		super();
		setAlignment(Pos.BOTTOM_LEFT);
		setPadding(new Insets(16));
		setSpacing(20);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		enemyBox = new HBox();
		enemyBox.setAlignment(Pos.BOTTOM_RIGHT);
		enemyBox.setSpacing(16);
		enemyBox.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setHgrow(enemyBox, Priority.ALWAYS);
		getChildren().addAll(Player.getInstance(), enemyBox);

		render();
	}

	@Override
	public void render() {
		Player.getInstance().render();
		enemyBox.getChildren().setAll(FightLogic.getInstance().getEntities());
		for (Entity entity : FightLogic.getInstance().getEntities()) {
			entity.render();
		}
	}

	public static GameBottom getInstance() {
		if (instance == null) {
			instance = new GameBottom();
		}
		return instance;
	}
}
