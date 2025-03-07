package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import entities.Being;
import entities.Entity;
import entities.EntityLoader;
import entities.EntitySpawner;
import entities.Player;
import game.GameBottom;
import game.util.Effect;
import game.util.EffectIcon;
import game.util.EffectType;
import game.util.IconLoader;
import javafx.application.Platform;
import sound.Sfx;
import sound.SfxPlayer;

public class FightLogic {
	private static FightLogic instance;
	private boolean isInFight, isPTurn;
	private Entity target;
	private ArrayList<Entity> entities, entitiesFromSummon;
	private int totalXp;

	public FightLogic() {
		this.isInFight = false;
		this.isPTurn = true;
		this.entities = new ArrayList<Entity>();
		this.entitiesFromSummon = new ArrayList<Entity>();
		totalXp = 0;
	}

	public void entitiesTurn() {
		isPTurn = false;
		if (!entitiesFromSummon.isEmpty()) {
			FightLogic.getInstance().getEntities().addAll(entitiesFromSummon);
			GameBottom.getInstance().render();
			entitiesFromSummon.clear();
		}
		Platform.runLater(() -> {
			new Thread(() -> {
				try {
					ArrayList<Entity> entitiesCopy = new ArrayList<>(entities);
					for (Entity en : entitiesCopy) {
						if (Player.getInstance().getHp() == 0)
							break;
						if (en.getHp() == 0)
							continue;
						entityTurn(en);
						Thread.sleep(500);
					}
					entities = entitiesCopy;
					clearDeadEntities();
					if (entities.isEmpty()) {
						GameLogic.getInstance().endFight();
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.err.println("Thread interrupted: " + e.getMessage());
				} finally {
					Platform.runLater(() -> {
						isPTurn = true;
						if (entities.isEmpty()) {
							GameLogic.getInstance().endFight();
						} else {
							playerTurn();
						}
					});
				}
			}).start();
		});
	}

	public void entityTurn(Entity e) {
		if (Player.getInstance().getHp() == 0) {
			return;
		}
		System.out.println("enemy turn");
		ArrayList<Effect> effects = new ArrayList<>(e.getAllEffect());
		for (Effect ef : effects) {
			activateEffect(ef, e);
		}
		if (e.getHp() == 0) {
			System.out.println("checkAlive");
			e.checkAlive();
			return;
		}
		e.setAllEffect(effects);
		e.activatePerTurn();
		if (!e.isStunned()) {
			Random rand = new Random();

			if (e.getNextTurn() != null) {
				e.moveLeftAndBack();
				useEffect(e.getNextTurn(), e);
				Player.getInstance().render();
			}
			if (Player.getInstance().getHp() == 0) {
				GameLogic.getInstance().gameOver();
			} else {
				e.setNextTurn(e.getAllAttributes().get(rand.nextInt(e.getAllAttributes().size())));
				if ((e.getNextTurn().getType().equals(EffectType.HEAL) && e.getHp() > e.getMaxHp() * 0.6)
						|| (e.getNextTurn().getType().equals(EffectType.SUMMONER)
								&& FightLogic.getInstance().getEntities().size()
										+ FightLogic.getInstance().getEntitiesFromSummon().size() > 3)) {
					e.setNextTurn(e.getAllAttributes().get(0));
				}
				if (!"demon".equals(e.getName())) {
					EffectIcon newIcon = IconLoader.newIcon(e.getNextTurn().getType(), e.getNextTurn().getAmount());
					int index = e.getChildren().indexOf(e.getNextTurnMove());
					Platform.runLater(() -> {
						if (index != -1) {
							e.getChildren().set(index, newIcon);
						}
					});
					e.setNextTurnMove(newIcon);
					e.getNextTurnMove().getFadeIn().play();
				}
			}
		}
	}

	public void playerTurn() {
		if (Player.getInstance().getHp() != 0) {
			isPTurn = true;
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
	}

	public void activateEffect(Effect effect, Being being) {
		switch (effect.getType()) {
		case FIRE	-> {
			being.takeDamage((effect.getAmount()));
			being.setMaxHp(being.getMaxHp() - 10);
		}
		case POISON	-> { being.takeDamage((effect.getAmount() + 10)); }
		case REGEN	-> {
			SfxPlayer.play(Sfx.HEAL);
			being.setHp(being.getHp() + effect.getAmount());
			effect.setAmount(effect.getAmount() / 2);
		}
		default		-> {}
		}
	}

	public void useEffect(Effect ef, Being e) {
		SfxPlayer.playByEffect(ef);
		switch (ef.getType()) {
		case FIRE		-> findEffectAndAdd(Player.getInstance().getAllEffect(), ef.getType(), ef.getAmount(), e);
		case POISON		-> findEffectAndAdd(Player.getInstance().getAllEffect(), ef.getType(), ef.getAmount(), e);
		case DAMAGE		-> doDamage(ef.getAmount(), e, (Being) Player.getInstance());
		case THORN		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount(), e);
		case SHIELD		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount(), e);
		case DODGE		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount(), e);
		case VAMPIRIC	-> e.setHp(e.getHp() + (int) (Player.getInstance().takeDamage(ef.getAmount()) * 0.5));
		case SUMMONER	-> {
			Entity newEntity = EntityLoader.newEntity(EntitySpawner.getNameFromTier(EntitySpawner.getTier()));
			entitiesFromSummon.add(newEntity);
			totalXp += newEntity.getXp();

		} // TODO: implement
		case ANGER		-> findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount(), e);
		case HEAL		-> e.setHp(e.getHp() + ef.getAmount());
		case REGEN		-> {
			if (e instanceof Player)
				findEffectAndAdd(e.getAllEffect(), ef.getType(), ef.getAmount(), e);
			else {
				for (Entity en : entities) {
					if (en.getHp() > 0 && en != e) {
						en.setHp(en.getHp() + ef.getAmount());
						en.render();
					}
				}
			}
		}
		default			-> {}
		}
	}

	private void clearDeadEntities() {
		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getHp() == 0) {
				iterator.remove();
			}
		}
	}

	public static void doDamage(int damage, Being self, Being enemy) {
		Effect rage = findEffect(self.getAllEffect(), EffectType.ANGER);
		Effect thorn = findEffect(enemy.getAllEffect(), EffectType.THORN);
		enemy.takeDamage(damage + (rage == null ? 0 : rage.getAmount()));
		self.takeDamage(thorn == null ? 0 : thorn.getAmount());
	}

	public static Effect findEffect(ArrayList<Effect> efs, EffectType target) {
		for (Effect ef : efs) {
			if (ef.getType().equals(target)) {
				return ef;
			}
		}
		return null;
	}

	public static void findEffectAndAdd(ArrayList<Effect> efs, EffectType target, int amount, Being targetBeing) {
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

	public ArrayList<Entity> getEntitiesFromSummon() {
		return entitiesFromSummon;
	}

	public void setEntitiesFromSummon(ArrayList<Entity> entitiesFromSummon) {
		this.entitiesFromSummon = entitiesFromSummon;
	}

	public int getTotalXp() {
		return totalXp;
	}

	public void setTotalXp(int totalXp) {
		this.totalXp = totalXp;
	}

	public static void setInstance(FightLogic instance) {
		FightLogic.instance = instance;
	}
}
