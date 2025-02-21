package game.item.consumable;

import game.util.EffectType;

public class FoodWithContainer extends Food {
	String container;

	public FoodWithContainer(String name, String detail, String container, int durability, EffectType effectType, int effectPower, int width,int height) {
		super(name, detail, durability, effectType, effectPower, width, height);
		setContainer(container);
	}

	public FoodWithContainer(String name, String detail, String container, int durability, EffectType effectType, int effectPower, int height) {
		super(name, detail, durability, effectType, effectPower, height);
		setContainer(container);
	}
	
	@Override
	public void activatePerClick() {
		super.activatePerClick();
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
}
