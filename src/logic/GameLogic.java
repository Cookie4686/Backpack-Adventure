package logic;

import java.util.ArrayList;

import entities.Entity;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.item.Item;

public class GameLogic {
	private static GameLogic instance;
	private ArrayList<Item> inventory;

	public void initializeFight() {
		FightLogic.getInstance().setInFight(true);
		Game.getInstance().clearFloatingItem();
		GameTop.getInstance().useBackpack();

		ArrayList<Entity> entities = new ArrayList<Entity>();
		entities.add(new Entity(null, null, 10, 10, 1, 1, null));
		entities.add(new Entity(null, null, 15, 15, 1, 1, null));
		FightLogic.getInstance().setEntities(entities);
		FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		GameBottom.getInstance().render();
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}
}
