package logic;

import java.util.ArrayList;
import java.util.Random;

import entities.Being;
import entities.Entity;
import entities.Player;
import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.StatUpdatable;
import interfaces.TurnActivable;
import javafx.application.Platform;

public class GameLogic {
	private static GameLogic instance = null;
	private boolean isPTurn;
	private boolean isInFight;
	private Entity target;
	private ArrayList<Entity> entities;
	public GameLogic() {
		this.isPTurn = true;
		this.isInFight = false;
		this.entities = new ArrayList<Entity>();
	}
	
	public void entitiesTurn() {
		for(Entity en : entities) {
			entityTurn(en);
		}
		//check game over
		isPTurn = true;
		playerTurn();
	}
	public void entityTurn(Entity e) {
		for(Effect ef : e.getAllEffect()) {
			Platform.runLater(()->{
				ActivateEffect(ef,e);
			});
		}
		e.activatePerTurn();
		if(e.isStunned()) return;
		Random rand = new Random();
		Platform.runLater(()->{
			useEffect(e.getNextTurn(),e);
			e.setNextTurn(e.getAllAttributes().get(rand.nextInt(e.getAllAttributes().size())));
		});
	}
	
	public void playerTurn() {
		Player.getInstance().setShield(0);
		for(Effect ef : Player.getInstance().getAllEffect()) {
			Platform.runLater(()->{
				ActivateEffect(ef,(Being) (Player.getInstance()));
			});
		}
		//check game over
		for(Item item : Player.getInstance().getInventory()) {
			if(item instanceof TurnActivable) {
				TurnActivable i = (TurnActivable) item;
				i.activatePerTurn();
			}
		}
	}
	public void ActivateEffect(Effect ef,Being e) {
		switch(ef.getType()) {
		case FIRE:
			e.setHp(e.getHp() - ef.getAmount() - 10);
			e.setMaxHp(e.getMaxHp() - 10);
			break;
		case POISON:
			e.setHp(e.getHp() - ef.getAmount());
			break;
		case REGEN:
			e.setHp(e.getHp() + ef.getAmount());
			ef.setAmount(ef.getAmount()/2);
			break;
		default:
			break;
		}
	}
	
	public void useEffect(Effect ef,Being e) {
		switch(ef.getType()) {
		case FIRE:
			findEffectAndAdd(Player.getInstance().getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case POISON:
			findEffectAndAdd(Player.getInstance().getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case DAMAGE:
			doDamage(ef, e, Player.getInstance());
			break;
		case THORN:
			findEffectAndAdd(e.getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case SHIELD:
			findEffectAndAdd(e.getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case DODGE:
			findEffectAndAdd(e.getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case VAMPIRIC:
			e.setHp(e.getHp() + (int) (Player.getInstance().takeDamage(ef.getAmount()) * 0.5));;
			break;
		case SUMMONER:
			//implement
			break;
		case ANGER:
			findEffectAndAdd(e.getAllEffect(),ef.getType(),ef.getAmount());
			break;
		case HEAL:
			e.setHp(e.getHp() + ef.getAmount());
			break;
		case REGEN:
			findEffectAndAdd(e.getAllEffect(),ef.getType(),ef.getAmount());
			break;
		default:
			break;
		}
	}
	
	public static void doDamage(Effect ef,Being e,Being p) {
		Effect rage = findEffect(e.getAllEffect(),EffectType.ANGER);
		Effect thorn = findEffect(p.getAllEffect(),EffectType.THORN);
		int extra = (rage == null ? 0 : rage.getAmount());
		int retaliate = (thorn == null ? 0 : thorn.getAmount());
		p.takeDamage(ef.getAmount() + extra);
		e.takeDamage(retaliate);
	}
	
	public static void findEffectAndAdd(ArrayList<Effect> efs,EffectType target,int amount) {
		for(Effect ef : efs) {
			if(ef.getType().equals(target)) {
				ef.setAmount(ef.getAmount() + amount);
				return;
			}
		}
		efs.add(new Effect(amount,target));
	}
	
	public static boolean findEffectAndDecrease(ArrayList<Effect> efs,EffectType target,int amount) {
		int i = 0;
		for(Effect ef : efs) {
			if(ef.getType().equals(target)) {
				int val = ef.getAmount() - amount;
				if(val <= 0) {
					efs.remove(i);
				} else {					
					ef.setAmount(val);
				}
				return true;
			}
			i++;
		}
		return false;
	}
	
	public static Effect findEffect(ArrayList<Effect> efs,EffectType target) {
		for(Effect ef : efs) {
			if(ef.getType().equals(target)) {
				return ef;
			}
		}
		return null;
	}
	
	public boolean isPTurn() {
		return isPTurn;
	}


	public void setPTurn(boolean isPTurn) {
		this.isPTurn = isPTurn;
	}


	public boolean isInFight() {
		return isInFight;
	}


	public void setInFight(boolean isInFight) {
		this.isInFight = isInFight;
	}


	public static GameLogic getInstance() {
		if(instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
