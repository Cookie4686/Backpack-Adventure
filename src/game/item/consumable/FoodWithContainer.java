package game.item.consumable;

import game.util.Effect;

public class FoodWithContainer extends Consumable {
	String container;
	
	public FoodWithContainer(String name, String detail, String container, int durability, Effect effect, int width,int height) {
		super(name, detail, durability, effect, width, height);
		setContainer(container);
	}
	
	public FoodWithContainer(String name, String detail, String container, int durability, Effect effect, int height) {
		super(name, detail, durability, effect, height);
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
