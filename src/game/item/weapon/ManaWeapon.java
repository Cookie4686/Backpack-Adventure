package game.item.weapon;

public class ManaWeapon extends Weapon{
	public ManaWeapon(String name, String detail, int damage, int costActivate, int width, int height) {
		super(name, detail, damage, costActivate, width, height);
	}

	public ManaWeapon(String name, String detail, int damage, int costActivate, int width) {
		super(name, detail, damage, costActivate, width);
	}
	
	@Override
	public void activatePerClick() {
		// TODO: decrease mana point in player by costActivate
		
		//TODO: damage enemy getDamage() amount
		//TODO: add effectType to enemy with effectPower amount;
	}
	
}
