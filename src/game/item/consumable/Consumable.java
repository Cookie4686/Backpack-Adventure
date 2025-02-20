package game.item.consumable;

import game.item.Item;
import game.util.EffectType;
import interfaces.Clickable;

public abstract class Consumable extends Item implements Clickable {
	private int durability;
	private EffectType effectType;
	private int effectPower;

	public Consumable(String name, String detail, int durability, EffectType effectType, int effectPower, int width, int height) {
		super(name, detail, width, height);
		setDurability(durability);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}

	public Consumable(String name, String detail, int durability, EffectType effectType, int effectPower, int height) {
		super(name, detail, height);
		setDurability(durability);
		setEffectType(effectType);
		setEffectPower(effectPower);
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public EffectType getEffectType() {
		return effectType;
	}

	public int getEffectPower() {
		return effectPower;
	}

	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}

	public void setEffectPower(int effectPower) {
		this.effectPower = effectPower;
	}
}
