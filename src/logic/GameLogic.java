package logic;

import java.util.ArrayList;

import entities.EntityLoader;
import entities.Player;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class GameLogic {
	private static GameLogic instance = null;
	private ArrayList<Item> inventory = null;
	private int currentFloor = 0;

	public void initializeFight() {
		FightLogic.getInstance().setInFight(true);
		Game.getInstance().clearFloatingItem();
		GameTop.getInstance().useBackpack();

		BackgroundSongPlayer.fight(currentFloor);
		// Spawn enemies

		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("werewolf"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("toad"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("bunny"));
		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("frog"));
		FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		GameBottom.getInstance().render();
		
		// Move into fightlogic
		// initial enemies future attack and player turn after that
		FightLogic.getInstance().entitiesTurn();
		
	}

	public void gameOver() {
		if (FightLogic.getInstance().isInFight()) {
			BackgroundSongPlayer.stop();
			SfxPlayer.play(Sfx.GAMEOVER);
			Player.getInstance().die();
		}
		FightLogic.getInstance().setInFight(false);
	}

	public void endFight() {
		if (FightLogic.getInstance().isInFight()) {
			BackgroundSongPlayer.floor(currentFloor);
			
			Item[] items = new Item[5];
			for (int i=0 ; i<5 ; i++) {
				items[i]=ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
			}
			Game.getInstance().addItemsToGame(items);
		}
		FightLogic.getInstance().setInFight(false);
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

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
}
