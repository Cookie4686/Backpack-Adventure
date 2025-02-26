package entities;

import java.util.ArrayList;
import java.util.Random;

import game.GameBottom;
import game.handler.EntityHandler;
import game.handler.ItemHandler;
import game.util.Effect;
import game.util.EffectType;
import interfaces.ReRenderable;
import interfaces.TurnActivable;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.FightLogic;
import logic.GameLogic;

public class Entity extends Being implements TurnActivable, ReRenderable {
	protected int xp, dangerLV;
	protected boolean stunned;
	//protected ArrayList<String> pic;
	private ImageView imageView;
	private Timeline timeline;
	protected ArrayList<Effect> allAttributes;
	protected Effect nextTurn;

	// will change later
	private Text text;

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
		//this.pic = pic;
		this.allAttributes = allAttributes;
		this.dangerLV = dangerLV;
		this.stunned = false;

		text = new Text();
		setCenter(text);
//		Button button = new Button("Select");
//		button.setOnMouseClicked(event -> EntityHandler.handleMouseClicked(this));
//		setBottom(button);
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
		if(this.getHp() == 0) {
			GameBottom.getInstance().getEnemyBox().getChildren().remove(this);
			FightLogic.getInstance().getEntities().remove(this);
			if(FightLogic.getInstance().getEntities().size() == 0) {
				GameLogic.getInstance().endFight();
				return damaged;
			}
			
			FightLogic.getInstance().setTarget(FightLogic.getInstance().getEntities().getFirst());
		}
		return damaged;
	}
	
	//@Override
//	public void initialize(Image image) {
//
//		imageView = new ImageView(image);
//		imageView.setOnMousePressed(event -> EntityHandler.handleMouseClicked(this));
//		getChildren().add(imageView);
//		
//		
//		
//	}

//	public ArrayList<String> getPic() {
//		return pic;
//	}
//
//	public void setPic(ArrayList<String> pic) {
//		this.pic = pic;
//	}

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

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
		text.setText(String.format("Hp: %s/%s, Df: %s", hp, maxHp, shield));
	}

}
