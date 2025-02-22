package game;

import entities.Entity;
import entities.Player;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameBottom extends HBox implements ReRenderable {
	private static GameBottom instance;
	private Text text;
	private HBox enemyBox;

	public GameBottom() {
		super();
		setAlignment(Pos.BOTTOM_LEFT);
		setPadding(new Insets(16));
		setSpacing(20);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		text = new Text();
		enemyBox = new HBox();
		enemyBox.setAlignment(Pos.BOTTOM_RIGHT);
		setHgrow(enemyBox, Priority.ALWAYS);
		getChildren().addAll(text, enemyBox);

		render();
	}

	@Override
	public void render() {
		text.setText(String.format("Hp: %s/%s, Df: %s, Energy: %s", Player.getHp(), Player.getMaxHp(),
				Player.getShield(), Player.getEnergy()));
		for (Node node : enemyBox.getChildren()) {
			if (node instanceof Entity)
				((Entity) node).render();
		}
	}

	public void addAllEntity(Entity... entities) {
		enemyBox.getChildren().addAll(entities);
	}

	public static GameBottom getInstance() {
		if (instance == null) {
			instance = new GameBottom();
		}
		return instance;
	}
}
