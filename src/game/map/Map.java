package game.map;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.GameLogic;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private MapSquare[][] squares;
	private ArrayList<MarkPosition> marks;

	public Map(int width, int height) {
		super();
		setAlignment(Pos.CENTER);
		setPickOnBounds(false);
		this.width = width;
		this.height = height;
		squares = new MapSquare[height][width];
		marks = new ArrayList<MarkPosition>();
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
		placeRandomMarker(MapMarker.PLAYER);
		while (marks.size() < 2 + GameLogic.getInstance().getCurrentFloor()) { // create 1 monster diffrence pos
			placeRandomMarker(MapMarker.MONSTER);
		}
		buildPath(marks.get(0), marks.get(1));
		while (marks.size() < 5 + GameLogic.getInstance().getCurrentFloor()) { // create 3 monster diffrence pos
			if (placeRandomMarker(MapMarker.MONSTER)) findRoute();
		}
		while (marks.size() < 6 + GameLogic.getInstance().getCurrentFloor()) { // create 1 door diffrence pos
			if (placeRandomMarker(MapMarker.DOOR)) findRoute();
		}
		while (marks.size() < 7 + GameLogic.getInstance().getCurrentFloor()) { // create 1 door diffrence pos
			if (placeRandomMarker(MapMarker.DOCTOR)) findRoute();
		}
		render();
	}

	private boolean placeRandomMarker(MapMarker marker) {
		int x = random(width), y = random(height);
		if (squares[x][y].getMarker() == null) {
			squares[x][y].setMarker(marker);
			marks.add(new MarkPosition(x, y));
			return true;
		}
		
		return false;
	}

	private void findRoute() {
		// add each node route to node at the index infront randomly.
		int r = random(2) + 2;
		buildPath(marks.getLast(), marks.get(marks.size()-r));
	}
	
	private boolean findAdjacent(MarkPosition prev, MarkPosition pos) {
		int x = pos.getX(), y = pos.getY();
		
		if (x+1<width)
			if (squares[x+1][y].getMarker() != null)
				if (!(x+1==prev.getX() && y==prev.getY())) return true;
		if (x-1>=0)
			if (squares[x-1][y].getMarker() != null )
				if (!(x-1==prev.getX() && y==prev.getY())) return true;
		if (y+1<height)
			if (squares[x][y+1].getMarker() != null)
				if (!(x==prev.getX() && y+1==prev.getY())) return true;
		if (y-1>=0)
			if (squares[x][y-1].getMarker() != null)
				if (!(x==prev.getX() && y-1==prev.getY())) return true;
		
		return false;
	}

	private void buildPath(MarkPosition start, MarkPosition end) {
		MarkPosition prev = new MarkPosition(start.getX(), start.getY());
		
		while (start.getX() != end.getX() || start.getY() != end.getY()) {
			if (findAdjacent(prev, start)) break;
			prev.setX(start.getX());
			prev.setY(start.getY());
			
			if (start.getX() == end.getX()) {
				start.setY(start.getY() + (start.getY() < end.getY() ? 1 : -1));
			} else if (start.getY() == end.getY()) {
				start.setX(start.getX() + (start.getX() < end.getX() ? 1 : -1));
			} else {
				start = randomHeading(start, end);
			}
			if (squares[start.getX()][start.getY()].getMarker() == null)
				squares[start.getX()][start.getY()].setMarker(MapMarker.PATH);
		}
	}

	private MarkPosition randomHeading(MarkPosition start, MarkPosition end) {
		if (random(2) == 0) { // x
			start.setX(start.getX() + (start.getX() < end.getX() ? 1 : -1));
		} else { // y
			start.setY(start.getY() + (start.getY() < end.getY() ? 1 : -1));
		}
		return start;
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

	private int random(int limit) {
		return new Random().nextInt(0, limit);
	}
	
	public void createNewMap() {
		instance = new Map(10, 10);
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

	public static void setInstance(Map instance) {
		Map.instance = instance;
	}
}