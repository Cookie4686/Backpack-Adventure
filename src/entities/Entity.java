package entities;

import java.util.ArrayList;
import java.util.Random;

import game.util.Effect;
import game.util.EffectType;
import interfaces.Clickable;
import interfaces.TurnActivable;
import javafx.application.Platform;
import logic.GameLogic;

public class Entity extends Being implements TurnActivable, Clickable{
	protected int xp, dangerLV;
	protected boolean stunned;
	protected ArrayList<String> pic;
	protected ArrayList<Effect> allAttributes;
	protected Effect nextTurn;

	public Entity(String name, ArrayList<String> pic, int maxHpLb, int xpLb, int dangerLV, int dodge, ArrayList<Effect> allAttributes) {
		super();
		int maxHpUb = (int) (maxHpLb*1.5);
		int xpUb = (int) (xpLb*1.5);
		this.name = name;
		Random rand = new Random();
		this.hp = this.maxHp = rand.nextInt((maxHpUb - maxHpLb) + 1) + maxHpLb;
		this.shield = 0;
		this.xp = rand.nextInt((xpUb - xpLb) + 1) + xpLb;
		this.pic = pic;
		this.allAttributes = allAttributes;
		this.dangerLV = dangerLV;
		this.dodge = 0;
		this.stunned = false;
	}

	public ArrayList<String> getPic() {
		return pic;
	}

	public void setPic(ArrayList<String> pic) {
		this.pic = pic;
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

	@Override
	public void activatePerClick() {
		// TODO Auto-generated method stub
		Platform.runLater(()->{
			//add target frame on top
		});
	}
}
