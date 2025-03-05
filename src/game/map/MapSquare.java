package game.map;

import game.backpack.Slot;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.handler.MapHandler;

public class MapSquare extends Pane {
	public final static int SIZE = (Slot.SIZE*7)/10;
	private MapMarker marker;

	public MapSquare() {
		this(null);
	}

	public MapSquare(MapMarker marker) {
		super();
		this.marker = marker;
		setMinSize(SIZE, SIZE);
		setMaxSize(SIZE, SIZE);
	}

	public void render(boolean top, boolean right, boolean bottom, boolean left) {
		if (marker != null) {
			if (marker != MapMarker.PATH) {
				ImageView imageView = new ImageView(MarkerLoader.getImage(marker));
				imageView.setFitWidth(SIZE);
				imageView.setFitHeight(SIZE);
				imageView.setOnMouseClicked(event -> {
					if(event.getButton() == MouseButton.PRIMARY) MapHandler.handleMouseClicked(this);
				});
				getChildren().setAll(imageView);
			}
			setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
			BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
					new BorderWidths(top ? 0 : 4, right ? 0 : 4, bottom ? 0 : 4, left ? 0 : 4));
			setBorder(new Border(borderStroke));
		}
	}

	public MapMarker getMarker() {
		return marker;
	}

	public void setMarker(MapMarker marker) {
		this.marker = marker;
	}
}
