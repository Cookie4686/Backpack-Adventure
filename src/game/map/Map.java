package game.map;

import javafx.scene.layout.GridPane;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private MapSquare[][] squares;

	public Map() {
		super();
		initialize(10, 10);
	}

	public void initialize(int width, int height) {
		this.width = width;
		this.height = height;
		squares = new MapSquare[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				squares[y][x] = new MapSquare(MapMarker.PATH);
				add(squares[y][x], x, y);
			}
		}
		squares[0][0].setMarker(null);
		squares[0][1].setMarker(null);
		squares[0][2].setMarker(MapMarker.DOOR);
		squares[0][3].setMarker(null);
		squares[0][4].setMarker(null);
		render();
	}

	public void render() {
		MapSquare square;
		boolean top, right, bottom, left;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				square = squares[y][x];
				if (square.getMarker() == null) {
					square.render(true, true, true, true);
				} else {
					top = y != 0 && squares[y - 1][x].getMarker() != null;
					right = x + 1 != width && squares[y][x + 1].getMarker() != null;
					bottom = y + 1 != height && squares[y + 1][x].getMarker() != null;
					left = x != 0 && squares[y][x - 1].getMarker() != null;
					square.render(top, right, bottom, left);
				}
			}
		}
	}

	public MapSquare[][] getSquares() {
		return squares;
	}

	public static Map getInstance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
}