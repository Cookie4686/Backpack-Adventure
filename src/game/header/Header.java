package game.header;

import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class Header extends HBox implements ReRenderable {
	public static Header instance;
	private Text floorText, experienceText, coinText;
	private Button saveButton;

	public Header() {
		super();
		floorText = new Text("Floor: -");
		experienceText = new Text("Exp: -/-");
		coinText = new Text("Coins: -/-");
		Region region = new Region();
		saveButton = new Button("Save Game");
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(10);
		setHgrow(region, Priority.ALWAYS);
		region.setMaxWidth(Double.MAX_VALUE);
		setAlignment(Pos.CENTER);
		getChildren().addAll(floorText, experienceText, coinText, region, saveButton);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	public static Header getInstance() {
		if (instance == null) {
			instance = new Header();
		}
		return instance;
	}
}
