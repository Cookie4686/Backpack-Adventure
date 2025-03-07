package game.map;

import java.util.ArrayList;
import java.util.Random;

import game.util.Position;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.GameLogic;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private MapSquare[][] squares;
	private ArrayList<Position> marks;
	private boolean noHeal;

	public Map(int width, int height) {
		super();
		setAlignment(Pos.CENTER);
		setPickOnBounds(false);
		this.width = width;
		this.height = height;
		noHeal = false;
		squares = new MapSquare[width][height];
		marks = new ArrayList<Position>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				squares[x][y] = new MapSquare();
				add(squares[x][y], x, y);
			}
		}
		// for setting marker
		initialize();
	}

	private void initialize() {
		placeRandomMarker(MapMarker.PLAYER);
		while (marks.size() < 2) { // create 1 monster diffrence pos
			placeRandomMarker(MapMarker.MONSTER);
		}
		buildPath(marks.get(0), marks.get(1));
		while (marks.size() < 5 + GameLogic.getInstance().getCurrentFloor()) { // create 3 monster diffrence pos
			if (placeRandomMarker(MapMarker.MONSTER))
				findRoute();
		}
		while (marks.size() < 6 + GameLogic.getInstance().getCurrentFloor()) { // create 1 door diffrence pos
			if (GameLogic.getInstance().getCurrentFloor() == 2 && GameLogic.getInstance().getCurrentSubFloor() == 2) {
				if (placeRandomMarker(MapMarker.FINAL))
					findRoute();
			} else {
				if (placeRandomMarker(MapMarker.DOOR))
					findRoute();
			}
		}
		while (marks.size() < 7 + GameLogic.getInstance().getCurrentFloor()) { // create 1 door diffrence pos
			if (placeRandomMarker(MapMarker.DOCTOR))
				findRoute();
		}
		render();
	}

	private boolean placeRandomMarker(MapMarker marker) {
		int x = random(width), y = random(height);
		if (squares[x][y].getMarker() == null) {
			squares[x][y].setMarker(marker);
			squares[x][y].setPosition(x, y);
			marks.add(new Position(x, y));
			return true;
		}

		return false;
	}

	private void findRoute() {
		// add each node route to node at the index infront randomly.
		int r = random(2) + 2;
		buildPath(marks.getLast(), marks.get(marks.size() - r));
	}

	private boolean isAdjacent(Position prev, Position pos) {
		int x = pos.getX(), y = pos.getY();

		if (x + 1 < width)
			if (squares[x + 1][y].getMarker() != null)
				if (!(x + 1 == prev.getX() && y == prev.getY()))
					return true;
		if (x - 1 >= 0)
			if (squares[x - 1][y].getMarker() != null)
				if (!(x - 1 == prev.getX() && y == prev.getY()))
					return true;
		if (y + 1 < height)
			if (squares[x][y + 1].getMarker() != null)
				if (!(x == prev.getX() && y + 1 == prev.getY()))
					return true;
		if (y - 1 >= 0)
			if (squares[x][y - 1].getMarker() != null)
				if (!(x == prev.getX() && y - 1 == prev.getY()))
					return true;

		return false;
	}

	private void buildPath(Position start, Position end) {
		Position prev = new Position(start.getX(), start.getY());
		while (start.getX() != end.getX() || start.getY() != end.getY()) {
			if (isAdjacent(prev, start))
				break;
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

	private Position randomHeading(Position start, Position end) {
		if (random(2) == 0) { // x
			start.setX(start.getX() + (start.getX() < end.getX() ? 1 : -1));
		} else { // y
			start.setY(start.getY() + (start.getY() < end.getY() ? 1 : -1));
		}
		return start;
	}

	public boolean isReachable(MapSquare targetSquare) {
		// DFS
		boolean[][] visited = new boolean[width][height];
		Position targetPosition = null, currentPosition = null;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (squares[x][y].getMarker() == null) {
					visited[x][y] = true;
				}
				if (squares[x][y] == targetSquare) {
					targetPosition = new Position(x, y);
				}
				if (squares[x][y].getMarker() == MapMarker.PLAYER) {
					currentPosition = new Position(x, y);
				}
			}
		}
		if (targetPosition != null && currentPosition != null) {
			return recur(currentPosition.getX(), currentPosition.getY(), visited, targetPosition);
		}
		return false;
	}

	private boolean recur(int x, int y, boolean[][] visited, Position targetPosition) {
		switch (squares[x][y].getMarker()) {
		case PLAYER, PATH	-> {}
		case DOCTOR			-> {
			if (x == targetPosition.getX() && y == targetPosition.getY())
				return true;
		}
		default				-> { return x == targetPosition.getX() && y == targetPosition.getY(); }
		}

		if (x + 1 != width && !visited[x + 1][y]) {
			visited[x + 1][y] = true;
			if (recur(x + 1, y, visited, targetPosition))
				return true;
		}
		if (x != 0 && !visited[x - 1][y]) {
			visited[x - 1][y] = true;
			if (recur(x - 1, y, visited, targetPosition))
				return true;
		}
		if (y + 1 != height && !visited[x][y + 1]) {
			visited[x][y + 1] = true;
			if (recur(x, y + 1, visited, targetPosition))
				return true;
		}
		if (y != 0 && !visited[x][y - 1]) {
			visited[x][y - 1] = true;
			if (recur(x, y - 1, visited, targetPosition))
				return true;
		}
		return false;
	}

	public void render() {
		MapSquare square;
		boolean top, right, bottom, left;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				square = squares[x][y];
				if (square.getMarker() == null) {
					square.render(true, true, true, true);
				} else {
					top = y != 0 && squares[x][y - 1].getMarker() != null;
					right = x + 1 != width && squares[x + 1][y].getMarker() != null;
					bottom = y + 1 != height && squares[x][y + 1].getMarker() != null;
					left = x != 0 && squares[x - 1][y].getMarker() != null;
					square.render(top, right, bottom, left);
				}
			}
		}
	}

	public void removePlayerMark() {
		for (int x = 0; x < Map.getInstance().getMapWidth(); x++) {
			for (int y = 0; y < Map.getInstance().getMapHeight(); y++) {
				if (squares[x][y].getMarker() == MapMarker.PLAYER) {
					if (noHeal) {
						squares[x][y].setMarker(MapMarker.DOCTOR);
						return;
					}
					squares[x][y].setMarker(MapMarker.PATH);
					squares[x][y].getChildren().clear();
					return;
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

	public int getMapWidth() {
		return width;
	}

	public int getMapHeight() {
		return height;
	}

	public boolean isNoHeal() {
		return noHeal;
	}

	public void setNoHeal(boolean noHeal) {
		this.noHeal = noHeal;
	}
}