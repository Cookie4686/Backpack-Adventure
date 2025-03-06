package game;

import entities.Player;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.handler.ButtonHandler;
import scene.popup.SettingPopup;

public class GameHeader extends HBox implements ReRenderable {
	private static GameHeader instance;
	private Text floorText, experienceText;
	private Button backpackButton;
	private Button settingButton;

	public GameHeader() {
		super();
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(10);
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

		floorText = new Text();
		experienceText = new Text();
		Region region = new Region();
		setHgrow(region, Priority.ALWAYS);
		backpackButton = new Button("Toggle Backpack");
		backpackButton.setOnAction(_ -> ButtonHandler.handleBackpackButtonOnAction());

		settingButton = new Button("Settings");
		settingButton.setOnAction(_ -> SettingPopup.getInstance().show());

		getChildren().setAll(floorText, experienceText, region, settingButton, backpackButton);
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

	public static void setInstance(GameHeader instance) {
		GameHeader.instance = instance;
	}
}
