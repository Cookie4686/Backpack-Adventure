package game.map;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MapSquare extends Pane {
	public final static int SIZE = 48;
	private MapMarker marker;

	public MapSquare(double x, double y) {
		super();
		setMinSize(SIZE, SIZE);
		setMaxSize(SIZE, SIZE);
		// setOnClick
	}

	public MapSquare(MapMarker marker) {
		super();
		this.marker = marker;
		ImageView imageView = new ImageView(MarkerLoader.getImage(marker));
		getChildren().add(imageView);
	}

	public MapMarker getMarker() {
		return marker;
	}

	public void removeMarker() {
		marker = null;
	}
}
