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
		return type == null ? "" : type.toString();
	}
}
