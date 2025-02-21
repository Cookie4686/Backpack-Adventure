package game.map;

import javafx.scene.layout.GridPane;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private MapSquare[][] squares;

	public Map() {
		super();
	}

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		squares = new MapSquare[height][width];
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (squares[y][x] != null) {
					add(squares[y][x], x, y);
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