package game.map;

import javafx.scene.layout.GridPane;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private MapSquare[][] squares;

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		squares = new MapSquare[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				squares[y][x] = new MapSquare();
				add(squares[y][x], x, y);
			}
		}
		// for setting marker
		initialize();
	}

	public void initialize() {
		squares[0][0].setMarker(MapMarker.PATH);
		squares[0][1].setMarker(MapMarker.PATH);
		squares[0][2].setMarker(MapMarker.DOOR);
		squares[0][3].setMarker(MapMarker.PATH);
		squares[0][4].setMarker(MapMarker.PATH);
		squares[0][5].setMarker(MapMarker.MONSTER);
		squares[0][6].setMarker(MapMarker.PATH);
		squares[0][7].setMarker(MapMarker.PATH);
		squares[0][8].setMarker(MapMarker.PLAYER);
		
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
			instance = new Map(10, 10);
		}
		return instance;
	}
}