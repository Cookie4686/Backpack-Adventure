package entities;

import java.util.ArrayList;
import java.util.Arrays;

import component.EnergyOrb;
import component.HpBar;
import game.GameBottom;
import game.backpack.Backpack;
import game.item.Item;
import game.util.Effect;
import game.util.EffectType;
import interfaces.ReStatable;
import interfaces.TurnActivable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.FightLogic;
import logic.GameLogic;
import sound.Sfx;
import sound.SfxPlayer;

public class Player extends Being implements TurnActivable, ReStatable {
	private static Player instance = null;
	private int xp, maxXp, energy, maxEnergy, mana, maxMana, coins, luck;
	//private ArrayList<String> pic;
	private CharacterState currentState = CharacterState.IDLE;
	//private ArrayList<String> idlePaths;
	private Point2D initialPosition;
	private ArrayList<Image> idleFrames;
	private Timeline idleTimeline;
	private ArrayList<Image> attackFrames;
	private Timeline attackTimeline;
	private ArrayList<Image> runFrames;
	private Timeline runTimeline;
	private ArrayList<Image> dieFrames;
	private Timeline dieTimeline;
	private ImageView imageView;
	private Text text;
	private EnergyOrb energyOrb;

	public Player() {
		super();
		this.name = "Player";
		this.hp = this.maxHp = 100;
		this.shield = 0;
		this.xp = 0;
		this.maxXp = 100;
		this.energy = this.maxEnergy = 100;
		this.mana = this.maxMana = 0;
		this.mana = this.maxMana = 0;
		//this.pic = null;
		this.coins = 0;
		this.luck = 0;
		this.allEffect = new ArrayList<Effect>();
		this.imageView = new ImageView();
		
		initialize(null);


		text = new Text();
		idleFrames = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("Frames/player_Idle1.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle2.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle3.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle4.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle5.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle6.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle7.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle8.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle9.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_Idle10.png").toString())
		));
		idleTimeline = createPlayerAnimation(idleFrames,0.1);
		idleTimeline.setCycleCount(Timeline.INDEFINITE);
		idleTimeline.play();
		this.getChildren().add(imageView);
		attackFrames = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("Frames/player_attack1.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack2.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack3.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack4.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack5.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack6.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack7.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack8.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack9.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_attack10.png").toString())
		));
		attackTimeline = createPlayerAnimation(attackFrames,0.05);
		attackTimeline.setCycleCount(1);
		runFrames = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("Frames/player_run1.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run2.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run3.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run4.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run5.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run6.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run7.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run8.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run9.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_run10.png").toString())
		));
		runTimeline = createPlayerAnimation(runFrames,0.075);
		runTimeline.setCycleCount(3);
		dieFrames = new ArrayList<Image>(Arrays.asList(
			new Image(ClassLoader.getSystemResource("Frames/player_die1.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die2.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die3.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die4.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die5.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die6.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die7.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die8.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die9.png").toString()),
			new Image(ClassLoader.getSystemResource("Frames/player_die10.png").toString())
		));
		dieTimeline = createPlayerAnimation(dieFrames,0.075);
		dieTimeline.setCycleCount(1);
	}
	
	private Timeline createPlayerAnimation(ArrayList<Image> images, double frameDuration) {
		Timeline timeline = new Timeline();
		for (int i = 0; i < images.size(); i++) {
	        final int frameIndex = i;
	        KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(frameDuration * i),
	            event -> imageView.setImage(images.get(frameIndex))
	        );
	        timeline.getKeyFrames().add(keyFrame);
	    }
	    imageView.setImage(images.get(0));
	    return timeline;
	}
	public void attack() {
        if (currentState == CharacterState.ATTACKING) {
            stopCurrentAnimation();
        }

        currentState = CharacterState.ATTACKING;
        startAttackAnimation();
    }
	private void startAttackAnimation() {
        attackTimeline.setOnFinished(event -> {
            currentState = CharacterState.IDLE;
            playAnimation("idle");
        });
        playAnimation("attack");
    }

    private void stopCurrentAnimation() {
        if (idleTimeline.getStatus() == Timeline.Status.RUNNING) {
            idleTimeline.stop();
        }
        if (attackTimeline.getStatus() == Timeline.Status.RUNNING) {
            attackTimeline.stop();
        }
        if (runTimeline.getStatus() == Timeline.Status.RUNNING) {
            runTimeline.stop();
        }
    }

    private void playAnimation(String animationName) {
        stopCurrentAnimation();
        switch (animationName) {
            case "idle":
                idleTimeline.play();
                break;
            case "attack":
                attackTimeline.play();
                break;
            case "run":
            	runTimeline.play();
            	break;
            case "die":
            	dieTimeline.play();
            	break;
            default:
                System.out.println("Unknown animation: " + animationName);
        }
    }
    
    public void die() {
    	playAnimation("die");
    }
    
    public void moveLeftAndBack() {
        double currentTranslateX = imageView.getTranslateX();
        Timeline moveTimeline = new Timeline();
        Timeline moveTimeline2 = new Timeline();
        double moveDistance = 200;
        double targetX = currentTranslateX + moveDistance;
        
        moveTimeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.translateXProperty(), currentTranslateX)),
                new KeyFrame(Duration.millis(1000), new KeyValue(imageView.translateXProperty(), currentTranslateX + moveDistance))
        );
        moveTimeline.setCycleCount(1);
        
        moveTimeline2.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.translateXProperty(), currentTranslateX - moveDistance)),
                new KeyFrame(Duration.millis(1000), new KeyValue(imageView.translateXProperty(), currentTranslateX))
        );
        moveTimeline2.setCycleCount(1);
        
        moveTimeline.setOnFinished(event -> {
        	moveTimeline2.play();
        });
        runTimeline.setOnFinished(event -> {
        	playAnimation("idle");
        });
        playAnimation("run");
        moveTimeline.play();
    }
    
	public int takeDamage(int damaged) {
		if (damaged==0) return 0;
		if (FightLogic.findEffectAndDecrease(allEffect, EffectType.DODGE, 1)) {
			SfxPlayer.play(Sfx.DODGE);
			return 0;
		}
		if (Player.getInstance().getShield() >= damaged) {
			Player.getInstance().setShield(Player.getInstance().getShield() - damaged);
			SfxPlayer.play(Sfx.SHIELD);
			damaged = 0;
		} else {
			SfxPlayer.play(Sfx.DAMAGE);
			damaged -= Player.getInstance().getShield();
			Player.getInstance().setShield(0);
			if (Player.getInstance().getHp() - damaged < 0) {
				damaged = Player.getInstance().getHp();
			}
			Player.getInstance().setHp(Player.getInstance().getHp() - damaged);
		}
		if (Player.getInstance().getHp() == 0) {
			GameLogic.getInstance().gameOver();
		}
		
		return damaged;
	}

	
	public void initialize(Image image) {
		energyOrb = new EnergyOrb(this);
		hpBar = new HpBar(this);
		getChildren().setAll(energyOrb, hpBar);
		render();
	}
	
	@Override
	public void render() {
		hpBar.render();
		energyOrb.render();
	}
	
	@Override
	public void activatePerTurn() {
		this.energy = maxEnergy;
		this.shield = 0;
		setHp(getHp()); //reset if overheal
		
		// Visible end turn button
		Backpack.getInstance().render();
		
		for (Item item : GameLogic.getInstance().getInventory()) {
			if (item instanceof TurnActivable) {
				System.out.println("Turn activate");
				((TurnActivable) item).activatePerTurn();
			}
		}
		setMana(getMaxMana());
		render();
	}
	
	@Override
	public void reStatBeforeUpdate() {
		this.maxHp = 100;
		this.maxEnergy = 100;
		this.maxMana = 0;
		this.coins = 0;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp < 0 ? 0 : xp;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public void setMaxXp(int maxXp) {
		this.maxXp = maxXp;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy < 0 ? 0 : energy;
		energyOrb.render();
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy < 0 ? 0 : maxEnergy;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana < 0 ? 0 : (mana > maxMana ? maxMana : mana);
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
}
