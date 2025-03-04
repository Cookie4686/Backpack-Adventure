package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import component.HpBar;
import game.GameBottom;
import game.util.Effect;
import game.util.EffectIcon;
import game.util.EffectType;
import game.util.IconLoader;
import game.util.MobTier;
import interfaces.TurnActivable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import logic.FightLogic;
import logic.GameLogic;
import logic.handler.EntityHandler;
import sound.Sfx;
import sound.SfxPlayer;

public class Entity extends Being implements TurnActivable {
	protected int xp;
	protected MobTier dangerLV;
	protected boolean stunned;
	private Timeline timeline;
	private ImageView imageView;
	protected ArrayList<Effect> allAttributes;
	protected Effect nextTurn;
	private double desiredX;
	private double originalX;
    private boolean isMoving = false;
    private static final double MOVE_DURATION = 0.3;
    private EffectIcon nextTurnMove;
    
	public Entity(String name, int maxHpLb, int xpLb, MobTier dangerLV,
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
		//nextTurnMove = new EffectIcon(null);
		nextTurnMove = new EffectIcon(null);
		getChildren().setAll(nextTurnMove, hpBar, imageView);
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
			SfxPlayer.play(Sfx.DEAD);
			checkAlive();
		}
		render();
		
		return damaged;
	}
	
	public void checkAlive() {
		for(Entity e : FightLogic.getInstance().getEntities()) {
			if(e.getHp() > 0) {					
				FightLogic.getInstance().setTarget(e);
				break;
			}
		}
		Platform.runLater(()->{
			this.die();
			GameBottom.getInstance().removeEntity();
			//FightLogic.getInstance().getEntities().remove(this);
			GameBottom.getInstance().render();
		});
		
		for(Entity e : FightLogic.getInstance().getEntities()) {
			if(e.getHp() > 0) return;
		}
		GameLogic.getInstance().endFight();
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

	public MobTier getDangerLV() {
		return dangerLV;
	}

	public void setDangerLV(MobTier dangerLV) {
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
	
	public boolean isMoving(){
        return isMoving;
    }
	public double getDesiredX() {
        return desiredX;
    }

    public void setDesiredX(double desiredX) {
        this.desiredX = desiredX;
    }

    public void moveTo(double newX) {
        if (isMoving) return;
        isMoving = true;
        setDesiredX(newX);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(MOVE_DURATION), this);
        transition.setToX(newX);

        transition.setOnFinished(event -> {
            isMoving = false;
        });

        transition.play();
    }

    public void die() {
        setVisible(false);
        setManaged(false);
    }

    public double getCurrentX() {
        return getTranslateX();
    }

    public void setCurrentX(double x) {
        setTranslateX(x);
    }

	public double getOriginalX() {
		return originalX;
	}

	public void setOriginalX(double originalX) {
		this.originalX = originalX;
	}
    
	public void moveLeftAndBack() {
        double currentTranslateX = imageView.getTranslateX();
        Timeline moveTimeline = new Timeline();
        double moveDistance = -20;
        double targetX = currentTranslateX + moveDistance;
        
        moveTimeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.translateXProperty(), currentTranslateX)),
                new KeyFrame(Duration.millis(100), new KeyValue(imageView.translateXProperty(), currentTranslateX + moveDistance)),
                new KeyFrame(Duration.millis(200), new KeyValue(imageView.translateXProperty(), currentTranslateX)) 
        );
        moveTimeline.setCycleCount(1);
        moveTimeline.play();
    }

	public EffectIcon getNextTurnMove() {
		return nextTurnMove;
	}

	public void setNextTurnMove(EffectIcon nextTurnMove) {
		this.nextTurnMove = nextTurnMove;
	}
    
	
}
