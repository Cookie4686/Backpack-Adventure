package logic;

import java.util.ArrayList;

import entities.EntityLoader;
import entities.Player;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.item.Item;
import sound.ThemeSongLoader;

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
		Player.getInstance().activatePerTurn();
		// ThemeSongLoader.play("boss1");
	}

	public void gameOver() {

	}

	public void endFight() {
		FightLogic.getInstance().setInFight(false);
		ThemeSongLoader.stop();
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
