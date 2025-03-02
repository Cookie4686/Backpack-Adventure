package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import entities.Being;
import entities.Entity;
import entities.Player;
import game.GameBottom;
import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.TurnActivable;
import javafx.application.Platform;
import sound.SfxPlayer;

public class FightLogic {
	private static FightLogic instance;
	private boolean isInFight, isPTurn;
	private Entity target;
	private ArrayList<Entity> entities;

	public FightLogic() {
		this.isInFight = false;
		this.isPTurn = true;
		this.entities = new ArrayList<Entity>();
	}

	public void entitiesTurn() {
		for (Entity en : entities) {
			entityTurn(en);
		}
		
		isPTurn = true;
		
		if (entities.size() == 0) {
			GameLogic.getInstance().endFight();
		} else {
			
			playerTurn();
		}
	}
	

	public void entityTurn(Entity e) {
		System.out.println("enemy turn");
		for (Effect ef : e.getAllEffect()) {
			activateEffect(ef, e);
			if (e.getHp() == 0) {
				e.checkAlive();
				return;
			}
		}
		e.activatePerTurn();
		if (!e.isStunned()) {
			Random rand = new Random();
			
			if (e.getNextTurn() != null) {
				useEffect(e.getNextTurn(), e);
			}
			if (Player.getInstance().getHp() == 0) {
				GameLogic.getInstance().gameOver();
			} else {
				e.setNextTurn(e.getAllAttributes().get(rand.nextInt(e.getAllAttributes().size())));
			}
		}
	}

	public void playerTurn() {
		for (Effect ef : Player.getInstance().getAllEffect()) {
			activateEffect(ef, Player.getInstance());
			Player.getInstance().render();
			if (Player.getInstance().getHp() == 0) {
				GameLogic.getInstance().gameOver();
				return;
			}
		}
		Player.getInstance().activatePerTurn();
	}

	public void activateEffect(Effect ef, Being e) {
		switch (ef.getType()) {
		case FIRE	-> {
			e.setHp(e.getHp() - ef.getAmount() - 10);
			e.setMaxHp(e.getMaxHp() - 10);
		}
		case POISON	-> { e.setHp(e.getHp() - ef.getAmount()); }
		case REGEN	-> {
			e.setHp(e.getHp() + ef.getAmount());
			ef.setAmount(ef.getAmount() / 2);
		}
		default		-> {}
		}
	}

	public void useEffect(Effect ef, Being e) {
		SfxPlayer.playByEffect(ef);
		switch (ef.getType()) {
		case FIRE		-> findEffectAndAdd(Player.getInstance().getAllEffect(), ef.getType(), ef.getAmount());
		case POISON		-> findEffectAndAdd(Player.getInstance().getAllEffect(), ef.getType(), ef.getAmount());
		case DAMAGE		-> doDamage(ef.getAmount(), e, (Being) Player.getInstance());
		case THORN		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount());
		case SHIELD		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount());
		case DODGE		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount());
		case VAMPIRIC	-> e.setHp(e.getHp() + (int) (Player.getInstance().takeDamage(ef.getAmount()) * 0.5));
		case SUMMONER	-> {} // TODO: implement
		case ANGER		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount());
		case HEAL		-> e.setHp(e.getHp() + ef.getAmount());
		case REGEN		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount());
		default			-> {}
		}
	}

	public static void doDamage(int damage, Being self, Being enemy) {
		Effect rage = findEffect(self.getAllEffect(), EffectType.ANGER);
		Effect thorn = findEffect(enemy.getAllEffect(), EffectType.THORN);
		enemy.takeDamage(damage + (rage == null ? 0 : rage.getAmount()));
		self.takeDamage(thorn == null ? 0 : thorn.getAmount());
	}

	public static void findEffectAndAdd(ArrayList<Effect> efs, EffectType target, int amount) {
		for (Effect ef : efs) {
			if (ef.getType().equals(target)) {
				ef.setAmount(ef.getAmount() + amount);
				return;
			}
		}
		efs.add(new Effect(amount, target));
	}

	public static boolean findEffectAndDecrease(ArrayList<Effect> efs, EffectType target, int amount) {
		Iterator<Effect> iterator = efs.iterator();
		while (iterator.hasNext()) {
			Effect effect = iterator.next();
			if (effect.getType().equals(target)) {
				int val = effect.getAmount() - amount;
				if (val <= 0) {
					iterator.remove();
				} else {
					effect.setAmount(val);
				}
				return true;
			}
		}
		return false;
	}

	public static Effect findEffect(ArrayList<Effect> efs, EffectType target) {
		for (Effect ef : efs) {
			if (ef.getType().equals(target)) {
				return ef;
			}
		}
		return null;
	}

	public static FightLogic getInstance() {
		if (instance == null) {
			instance = new FightLogic();
		}
		return instance;
	}

	public boolean isInFight() {
		return isInFight;
	}

	public void setInFight(boolean isInFight) {
		this.isInFight = isInFight;
	}

	public boolean isPTurn() {
		return isPTurn;
	}

	public void setPTurn(boolean isPTurn) {
		this.isPTurn = isPTurn;
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
