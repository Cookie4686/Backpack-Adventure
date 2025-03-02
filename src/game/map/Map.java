package game.map;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.GridPane;
import logic.GameLogic;

public class Map extends GridPane {
	private static Map instance;
	private int width, height;
	private static MapSquare[][] squares;
	private static ArrayList<markPosition> marks;
	

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
	
	private markPosition randomHeading(markPosition start, markPosition end) {
		int direction = random(2);
		if (direction==0) { //x
			start.setX(start.getX() + (start.getX()<end.getX() ? 1 : -1));
		} else { //y
			start.setY(start.getY() + (start.getY()<end.getY() ? 1 : -1));
		}
		
		return start;
	}
	
	private void buildPath(markPosition start, markPosition end) {
		while (start.getX()!=end.getX() || start.getY()!=end.getY()) {
			
			if (start.getX()==end.getX()) {
				start.setY(start.getY() + (start.getY()<end.getY() ? 1 : -1));
			}
			else if (start.getY()==end.getY()) {
				start.setX(start.getX() + (start.getX()<end.getX() ? 1 : -1));
			}
			else {
				start = randomHeading(start, end);
			}
			
			if (squares[start.getX()][start.getY()].getMarker()==null) squares[start.getX()][start.getY()].setMarker(MapMarker.PATH);
		}
	}
	
	private void findRoute() {
		// add each node route to node at the index infront randomly.
		buildPath(marks.get(0), marks.get(1));
		for (int i=2 ; i<marks.size() ; i++) {
			int r = random(i-1)+1;
			buildPath(marks.get(i), marks.get(r));
		}
		
	}
 
	public void initialize() {
		marks = new ArrayList<markPosition>();
		int x,y;
		x=random(width);
		y=random(width);
		marks.add(new markPosition(x, y)); // player start at 0,0 only
		squares[x][y].setMarker(MapMarker.PLAYER);
		while (marks.size() < 4 + GameLogic.getInstance().getCurrentFloor()) { // create 3 monster diffrence pos
			x=random(width);
			y=random(width);
			if (squares[x][y].getMarker()==null) {
				marks.add(new markPosition(x, y));
				squares[x][y].setMarker(MapMarker.MONSTER);
			}
		}
		
		while (marks.size() < 5 + GameLogic.getInstance().getCurrentFloor()) { // create 1 door diffrence pos
			x=random(width);
			y=random(width);
			if (squares[x][y].getMarker()==null) {
				marks.add(new markPosition(x, y));
				squares[x][y].setMarker(MapMarker.DOOR);
			}
		}
		
		findRoute();
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
	
	private int random(int limit) {
		return new Random().nextInt(0, limit);
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