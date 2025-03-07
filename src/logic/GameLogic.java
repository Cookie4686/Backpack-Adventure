package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


import application.Main;
import entities.Entity;
import entities.EntityLoader;
import entities.EntitySpawner;
import entities.Player;
import game.Game;
import game.GameBottom;
import game.GameHeader;
import game.backpack.Backpack;
import game.item.Item;
import game.itemGenerator.ItemRandomizer;
import game.itemGenerator.ResourceLoader;
import interfaces.ReStatable;
import interfaces.StatUpdatable;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import scene.MenuScene;
import scene.popup.SettingPopup;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class GameLogic {
	private static GameLogic instance;
	private int currentFloor;
	private int currentSubFloor;
	private boolean boss;
	private ArrayList<Item> inventory;
	private MediaPlayer levelupSfx;
	private StackPane announce;

	public GameLogic() {
		super();
		currentFloor = 0;
		currentSubFloor = 0;
		boss = false;
		inventory = new ArrayList<Item>();
		
		announce = new StackPane();
		announce.setAlignment(Pos.CENTER);
		
		ImageView dimmer = new ImageView(new Image(ClassLoader.getSystemResource("picture/dimmer.png").toString()));
		Text levelUp = new Text("Level UP");
		levelUp.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 64));
		levelUp.setFill(Color.WHITE);
		
		announce.getChildren().addAll(dimmer, levelUp);
		
		levelupSfx = new MediaPlayer(new Media(ClassLoader.getSystemResource("sfx/levelup.mp3").toString()));
		levelupSfx.volumeProperty().bind(SettingPopup.getInstance().getSfxSlider().valueProperty());
		levelupSfx.setOnEndOfMedia(()-> {
			Main.root.getChildren().remove(announce);
		});
	}

	public void initializeFight() {
		GameBottom.getInstance().getEnemyBox().getChildren().clear();
		FightLogic.getInstance().setInFight(true);
		Game.getInstance().initializeFight();

		BackgroundSongPlayer.fight(currentFloor);

		// Spawn enemies
		Random rand = new Random();
		int enUb = 3 + currentFloor;
		int enLb = 1 + currentFloor;
		int amount = rand.nextInt((enUb - enLb) + 1) + enLb;
		if(!boss) {
			for(int i = 0 ; i < amount ; i++) {			
				FightLogic.getInstance().getEntities().add(EntityLoader.newEntity(EntitySpawner.getNameFromTier(EntitySpawner.getTier())));
			}
		} else {
			FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("demon"));
		}
//		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("mushroom"));
//		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("bunny"));
//		FightLogic.getInstance().getEntities().add(EntityLoader.newEntity("frog"));
		FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		GameBottom.getInstance().render();
		for(Entity en : FightLogic.getInstance().getEntities()) {
			FightLogic.getInstance().setTotalXp(FightLogic.getInstance().getTotalXp() + en.getXp());
		}
		// Move into fightlogic
		// initial enemies future attack and player turn after that
		PauseTransition pause = new PauseTransition(Duration.seconds(1.3));
		pause.setOnFinished(_ -> {
			FightLogic.getInstance().entitiesTurn();
		});
		pause.play();
	}

	public void gameOver() {
		MenuScene.setGameStarted(false);
		if (FightLogic.getInstance().isInFight()) {
			BackgroundSongPlayer.stop();
			SfxPlayer.play(Sfx.GAMEOVER);
			Player.getInstance().die();
		}
		// FightLogic.getInstance().setInFight(false);
		System.out.println("over");
	}

	public void endFight() {
		Iterator<Entity> iterator = FightLogic.getInstance().getEntities().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getHp() == 0) {
				iterator.remove();
			}
		}
		
		if (FightLogic.getInstance().isInFight()) {
			Player.getInstance().setXp(Player.getInstance().getXp() + FightLogic.getInstance().getTotalXp());
			FightLogic.getInstance().setTotalXp(0);
			
			BackgroundSongPlayer.floor(currentFloor);
			Player.getInstance().getAllEffect().clear();
			
			
			Item[] items = new Item[5];
			for (int i = 0; i < 5; i++) {
				items[i] = ResourceLoader.newItem(ItemRandomizer.getRandomItemName());
			}
			Game.getInstance().addItemsToGame(items);
			
			Player.getInstance().reStatBeforeUpdate();
			GameLogic.updateBackpackItems();
			Player.getInstance().render();
			
			System.out.println(Player.getInstance().getHp());
			Player.getInstance().setShield(0);
			Player.getInstance().setHp(Player.getInstance().getHp());
			FightLogic.getInstance().setInFight(false);
			GameHeader.getInstance().render();
			Backpack.getInstance().render();
			
			if (Backpack.getInstance().isLevelup()) {
				levelupSfx.stop();
				Main.root.getChildren().add(announce);
				levelupSfx.play();
			}
		}
	}
	
	public boolean isLimitReached() {
		int count=0;
		Backpack.getInstance().render();
		for (Item item:inventory) {
			if (item.isNewItem()) count++;
			if (count>=3) return true;
		}
		return false; //can pick only 3 items
	}

	public static void  updateBackpackItems() {
		for (Item item : GameLogic.getInstance().getInventory()) {
			if (item instanceof ReStatable) {
				((ReStatable) item).reStatBeforeUpdate();
			}
		}
		for (Item item : GameLogic.getInstance().getInventory()) {
			if (item instanceof StatUpdatable) {
				((StatUpdatable) item).statUpdate();
			}
		}
		for (Item item : GameLogic.getInstance().getInventory()) {
			item.updateTooltip();
		}
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	public static void setInstance(GameLogic instance) {
		GameLogic.instance = instance;
	}

	public int getCurrentSubFloor() {
		return currentSubFloor;
	}

	public void setCurrentSubFloor(int currentSubFloor) {
		this.currentSubFloor = currentSubFloor;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	
	
}
