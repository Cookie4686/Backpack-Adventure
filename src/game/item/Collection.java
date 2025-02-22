package game.item;

public class Collection extends Item {
	private int amount;
	
	public Collection(String name, String detail, int amount, int width, int height) {
		super(name, detail, width, height);
		setAmount(amount);
	}

	public Collection(String name, String detail, int amount, int height) {
		super(name, detail, height);
		setAmount(amount);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = (amount<0)? 0 : amount;
	}
}
