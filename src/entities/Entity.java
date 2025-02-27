package entities;

import java.util.ArrayList;
import java.util.Random;

import component.HpBar;
import game.GameBottom;
import game.util.Effect;
import game.util.EffectType;
import interfaces.TurnActivable;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.FightLogic;
import logic.GameLogic;
import logic.handler.EntityHandler;

public class Entity extends Being implements TurnActivable {
	protected int xp, dangerLV;
	protected boolean stunned;
	private Timeline timeline;
	private ImageView imageView;
	protected ArrayList<Effect> allAttributes;
	protected Effect nextTurn;

	public Entity(String name, int maxHpLb, int xpLb, int dangerLV,
			ArrayList<Effect> allAttributes) {
		super();
		int maxHpUb = (int) (maxHpLb * 1.5);
		int xpUb = (int) (xpLb * 1.5);
		this.name = name;
		Random rand = new Random();
		this.hp = this.maxHp = rand.nextInt((maxHpUb - maxHpLb) + 1) + maxHpLb;
		this.shield = 0;
		this.xp = rand.nextInt((xpUb - xpLb) + 1) + xpLb;
		this.allAttributes = allAttributes;
		this.dangerLV = dangerLV;
		this.stunned = false;
	}

	// @Override
	public void initialize() {
		imageView.setCursor(Cursor.CROSSHAIR);
		imageView.setPickOnBounds(true);
		imageView.setOnMousePressed(event -> EntityHandler.handleMouseClicked(this));
		hpBar = new HpBar(this);
		getChildren().setAll(imageView, hpBar);
		render();
	}

	@Override
	public void render() {
		hpBar.render();
	}

	public int takeDamage(int damaged) {
		if (FightLogic.findEffectAndDecrease(allEffect, EffectType.DODGE, 1)) {
			return 0;
		}
		if (this.getShield() >= damaged) {
			this.setShield(this.getShield() - damaged);
			damaged = 0;
		} else {
			damaged -= this.getShield();
			this.setShield(0);
			if (this.getHp() - damaged < 0) {
				damaged = this.getHp();
			}
			this.setHp(this.getHp() - damaged);
		}
		if (this.getHp() == 0) {
			GameBottom.getInstance().getEnemyBox().getChildren().remove(this);
			FightLogic.getInstance().getEntities().remove(this);
			if (FightLogic.getInstance().getEntities().size() == 0) {
				GameLogic.getInstance().endFight();
				return damaged;
			}

			FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		}
		return damaged;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp < 0 ? 0 : xp;
	}

	public ArrayList<Effect> getAllAttributes() {
		return allAttributes;
	}

	public void setAllAttributes(ArrayList<Effect> allAttributes) {
		this.allAttributes = allAttributes;
	}

	public int getDangerLV() {
		return dangerLV;
	}

	public void setDangerLV(int dangerLV) {
		this.dangerLV = dangerLV;
	}

	public Effect getNextTurn() {
		return nextTurn;
	}

	public void setNextTurn(Effect nextTurn) {
		this.nextTurn = nextTurn;
	}

	public boolean isStunned() {
		return stunned;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}

	@Override
	public void activatePerTurn() {
		// TODO Auto-generated method stub
		shield = 0;
	}

	public void activatePerClick() {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			// add target frame on top
		});
	}
	public ImageView getImageView() {
		if(imageView == null) {
			imageView = new ImageView();
		}
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
}
