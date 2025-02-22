package game.item.consumable;

import game.util.Effect;

public class FoodWithContainer extends Consumable {
	String container;
	
	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int width,int height) {
		super(name, detail, durability, effect, costActivate, width, height);
		setContainer(container);
	}
	
	public FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int height) {
		super(name, detail, durability, effect, costActivate, height);
		setContainer(container);
	}
	
	@Override
	public void activatePerClick() {
		// TODO Replace this with new container item
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
}
