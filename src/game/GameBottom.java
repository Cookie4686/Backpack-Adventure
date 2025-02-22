package game;

import entities.Player;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GameBottom extends HBox implements ReRenderable {
	private static GameBottom instance;
	private Text text;

	public GameBottom() {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(20);

		text = new Text();
		getChildren().addAll(text);
		render();
	}

	@Override
	public void render() {
		text.setText(String.format("Hp: %s/%s, Df: %s, Energy: %s", Player.getHp(), Player.getMaxHp(),
				Player.getShield(), Player.getEnergy()));
	}

	public static GameBottom getInstance() {
		if (instance == null) {
			instance = new GameBottom();
		}
		return instance;
	}
}
