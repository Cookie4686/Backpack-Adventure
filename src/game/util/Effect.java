package game.util;

public class Effect {
	private int amount;
	private EffectType type;

	public Effect(int amount, EffectType type) {
		super();
		this.amount = amount;
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public EffectType getType() {
		return type;
	}

	public void setType(EffectType type) {
		this.type = type;
	}
	
	public String getTypeName() {
		switch (type) {
			case ANGER: return "ANGER";
			case DAMAGE: return "DAMAGE";
			case DODGE: return "DODGE";
			case ENERGY: return "ENERGY";
			case FIRE: return "FIRE";
			case HEAL: return "HEAL";
			case LUCK: return "LUCK";
			case POISON: return "POISON";
			case REGEN: return "REGEN";
			case SHIELD: return "SHIELD";
			case STUNTED: return "STUNTED";
			case SUMMONER: return "SUMMONER";
			case THORN: return "THORN";
			case VAMPIRIC: return "VAMPIRIC";
			default: return "";
		}
	}
}
