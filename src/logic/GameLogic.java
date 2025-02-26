package logic;

import java.util.ArrayList;

import entities.Entity;
import entities.EntityLoader;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.item.Item;

public class GameLogic {
	private static GameLogic instance = null;
	private ArrayList<Item> inventory = null;
	private int currentFloor = 0;
	
	public void initializeFight() {
		FightLogic.getInstance().setInFight(true);
		Game.getInstance().clearFloatingItem();
		GameTop.getInstance().useBackpack();

		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("werewolf"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("werewolf"));
		
		FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		GameBottom.getInstance().render();
	}
	
	public void gameOver() {
		
	}
	
	public void endFight() {
		FightLogic.getInstance().setInFight(false);
		
	}

	public ArrayList<Item> getInventory() {
		if (inventory == null) {
			inventory = new ArrayList<Item>();
		}
		return inventory;
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}
}
