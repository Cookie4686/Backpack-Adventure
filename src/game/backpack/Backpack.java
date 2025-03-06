package game.backpack;

import java.util.ArrayList;

import component.Button;
import game.Game;
import game.item.Item;
import game.item.consumable.Potion;
import game.util.ItemPosition;
import game.util.ItemRotation;
import interfaces.ReRenderable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.FightLogic;
import logic.GameLogic;
import logic.handler.ButtonHandler;
import logic.handler.ItemHandler;
import sound.Sfx;
import sound.SfxPlayer;

public class Backpack extends VBox implements ReRenderable {
	private static Backpack instance;
	public static final int WIDTH = 7, HEIGHT = 5;
	private Slot[][] slots;
	private StackPane stackPane;
	private GridPane gridPane;
	private Button endTurnButton;
	private ImageView backpack;

	public Backpack() {
		super();
		slots = new Slot[HEIGHT][WIDTH];
		gridPane = new GridPane();
		StackPane.setAlignment(gridPane, Pos.CENTER);
		gridPane.setBorder(new Border(
				new BorderStroke(Color.AQUA, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMaxSize(Slot.SIZE * WIDTH, Slot.SIZE * HEIGHT);
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				gridPane.add(slots[y][x] = new Slot(), x, y);
				if ((0<y && 4>y) && (1<x && 5>x)) {
					slots[y][x].setUnlocked(true);
				}
			}
		}
		endTurnButton = new Button("End Turn", 64, 16);
		endTurnButton.setOnAction(_ -> ButtonHandler.handleEndTurnButtonOnAction());
		
		backpack = new ImageView(new Image(ClassLoader.getSystemResource(String.format("picture/backpack.png")).toString()));
		backpackResize();
		
		stackPane = new StackPane();
		stackPane.setBorder(new Border(
				new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		stackPane.setAlignment(Pos.CENTER);
		stackPane.getChildren().setAll(backpack,gridPane);
		setAlignment(Pos.CENTER);
		getChildren().setAll(stackPane, endTurnButton);
		setSpacing(8);
		render();
	}

	public void backpackResize() {
		int minX = WIDTH, maxX = 0;
		int minY = HEIGHT, maxY = 0;
		
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (slots[y][x].isUnlocked()) {
					if (x<minX) minX=x;
					if (x>maxX) maxX=x;
					if (y<minY) minY=y;
					if (y>maxY) maxY=y;					
				}
			}
		}
		
		backpack.setFitWidth((Slot.SIZE * (maxX - minX + 1)) + 150);
		backpack.setFitHeight((Slot.SIZE * (maxY - minY + 1)) + 100);
	}
  	
	@Override
	public void render() {
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				slot.render();
			}
		}
		endTurnButton.setVisible(FightLogic.getInstance().isInFight());
	}

	public boolean isPlaceable(int gridX, int gridY, Item item) {
		try {
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					if (!slots[gridY + i][gridX + (isLeft ? i : -i)].isUnlocked())
						return false;
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						if (!slots[y][x].isUnlocked())
							return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean placeItem(int gridX, int gridY, Item item) {
		removeItem(item);
		boolean isPlaceable = isPlaceable(gridX, gridY, item);
		if (isPlaceable) {
			SfxPlayer.play(Sfx.INSIDEOFBACKPACK);
			GameLogic.getInstance().getInventory().add(item);
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					placeItem(slots[gridY + i][gridX + (isLeft ? i : -i)], item);
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						placeItem(slots[y][x], item);
					}
				}
			}
		} else {
			SfxPlayer.play(Sfx.OUTSIDEOFBACKPACK);
			item.moveUpAndDown();
		}
		render();
		return isPlaceable;
	}

	public void replaceItem(Item oldItem, Item newItem) {
		newItem.setTranslateX(oldItem.getTranslateX());
		newItem.setTranslateY(oldItem.getTranslateY());
		newItem.getImageView().setRotate(oldItem.getImageView().getRotate());
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				if (slot.getItem() == oldItem) {
					slot.setItem(newItem);
				}
			}
		}
		GameLogic.getInstance().getInventory().remove(oldItem);
		Game.getInstance().getChildren().remove(oldItem);
		GameLogic.getInstance().getInventory().add(newItem);
		Game.getInstance().getChildren().add(newItem);
	}

	private void placeItem(Slot slot, Item item) {
		if (slot.getItem() != null) {
			if (slot.getItem() instanceof Potion) {
				if (((Potion) item).isStackable(slot.getItem())) {
					((Potion) item)
							.setDurability(((Potion) item).getDurability() + ((Potion) slot.getItem()).getDurability());

					Game.getInstance().getChildren().remove(slot.getItem());
				}
			}

			ItemHandler.setRandomOffGridLocation(slot.getItem());
			removeItem(slot.getItem());
		}
		slot.setItem(item);
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void removeItem(Item item) {
		GameLogic.getInstance().getInventory().remove(item);
		for (Slot[] row : slots) {
			for (Slot slot : row) {
				if (slot.getItem() == item) {
					slot.setItem(null);
				}
			}
		}
	}

	public void hightlight(int gridX, int gridY, Item item) {
		if (isPlaceable(gridX, gridY, item)) {
			if (item.isDiagonal()) {
				boolean isLeft = item.getRotation() == ItemRotation.DIAGONAL_LEFT;
				for (int i = 0; i < item.getItemHeight(); i++) {
					slots[gridY + i][gridX + (isLeft ? i : -i)].highlight();
				}
			} else {
				for (int y = gridY; y < gridY + item.getItemHeight(); y++) {
					for (int x = gridX; x < gridX + item.getItemWidth(); x++) {
						slots[y][x].highlight();
					}
				}
			}
		}
	}

	public ArrayList<ItemPosition> getItemPosition(Item item) {
		ArrayList<ItemPosition> itemPositions = new ArrayList<ItemPosition>();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (slots[y][x].getItem() == item)
					itemPositions.add(new ItemPosition(x, y));
			}
		}
		return itemPositions;
	}

	public Slot[][] getSlots() {
		return slots;
	}

	public static Backpack getInstance() {
		if (instance == null) {
			instance = new Backpack();
		}
		return instance;
	}

	public static void setInstance(Backpack instance) {
		Backpack.instance = instance;
	}
	
}