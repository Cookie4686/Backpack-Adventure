package logic;

import java.util.ArrayList;

import entities.EntityLoader;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import sound.ThemeSongLoader;

public class GameLogic {
	private static GameLogic instance = null;
	private ArrayList<Item> inventory = null;
	private int currentFloor = 0;

	public void initializeFight() {
		FightLogic.getInstance().setInFight(true);
		Game.getInstance().clearFloatingItem();
		GameTop.getInstance().useBackpack();


		// Spawn enemies

		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("nightmare"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("fireDragon"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("demon"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("frog"));
		FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		GameBottom.getInstance().render();
		
		// Move into fightlogic
		// initial enemies future attack and player turn after that
		FightLogic.getInstance().entitiesTurn();
		
		
		// ThemeSongLoader.play("boss1");
	}

	public void gameOver() {

	}

	public void endFight() {
		FightLogic.getInstance().setInFight(false);
		ThemeSongLoader.stop();
		
		Item[] items = new Item[6];
		for (int i=0 ; i<6 ; i++) {
			items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
		}
		Game.getInstance().addItemsToGame(items);
	}

	public ArrayList<Item> getInventory() {
		if (inventory == null) {
			inventory = new ArrayList<Item>();
		}
		return inventory;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}
}
