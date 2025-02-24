package game;

import entities.Player;
import game.handler.ButtonHandler;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import logic.GameLogic;

public class GameHeader extends HBox implements ReRenderable {
	private static GameHeader instance;
	private Text floorText, experienceText;
	private Button backpackButton;

	public GameHeader() {
		super();
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(10);

		floorText = new Text();
		experienceText = new Text();
		Region region = new Region();
		setHgrow(region, Priority.ALWAYS);
		backpackButton = new Button("Toggle Backpack");
		backpackButton.setOnAction(event -> ButtonHandler.handleBackpackButtonOnAction());

		getChildren().addAll(floorText, experienceText, region, backpackButton);
		render();
	}

	@Override
	public void render() {
		floorText.setText(String.format("Floor: %s", GameLogic.getInstance().getCurrentFloor()));
		experienceText
				.setText(String.format("Exp: %s/%s", Player.getInstance().getXp(), Player.getInstance().getMaxXp()));
	}

	public static GameHeader getInstance() {
		if (instance == null) {
			instance = new GameHeader();
		}
		return instance;
	}
}
