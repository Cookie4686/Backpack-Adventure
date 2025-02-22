package game;

import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class GameHeader extends HBox implements ReRenderable {
	private static GameHeader instance;
	private Text floorText, experienceText, coinText;
	private Button backpackButton;

	public GameHeader() {
		super();
		floorText = new Text("Floor: -");
		experienceText = new Text("Exp: -/-");
		coinText = new Text("Coins: -/-");
		Region region = new Region();
		backpackButton = new Button("Toggle Backpack");
		backpackButton.setOnAction(event -> {
			if (!GameLogic.isFighting()) {
				if (GameTop.getInstance().isBackpack()) {
					GameTop.getInstance().useMap();
				} else {
					GameTop.getInstance().useBackpack();
				}
			}
		});
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(10);
		setHgrow(region, Priority.ALWAYS);
		region.setMaxWidth(Double.MAX_VALUE);
		setAlignment(Pos.CENTER);
		getChildren().addAll(floorText, experienceText, coinText, region, backpackButton);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	public static GameHeader getInstance() {
		if (instance == null) {
			instance = new GameHeader();
		}
		return instance;
	}
}
