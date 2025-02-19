package game.item;

public class Weapon extends Item {
	public Weapon(String name, int width) {
		super(name, width);
		System.out.println("weapon created");
	}

	public Weapon(String name, int width, int height) {
		super(name, width, height);
		System.out.println("weapon created");
	}
}
