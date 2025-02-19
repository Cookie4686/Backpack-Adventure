package game.item;


public abstract class Wareable extends Item{
	final private int initialShield;
	private int shield;
	private int increaseShield;
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, int width, int height) {
		super(name, detail, width, height);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
	}
	
	public Wareable(String name, String detail, int initialShield, int increaseShield, int width) {
		super(name, detail, width);
		this.initialShield = (initialShield<0)? 0 : initialShield;
		setShield(initialShield);
		setIncreaseShield(increaseShield);
	}

	public abstract void activateStart();
	
	protected int increasePerAdjacent() {
		
		//TODO: return increaseShield * each Wareable type adjacent to this
		
		return increaseShield;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = (shield<0)? 0 : shield;
	}

	public int getInitialShield() {
		return initialShield;
	}

	public void setIncreaseShield(int increaseShield) {
		this.increaseShield = (increaseShield<0)? 0 : increaseShield;
	}
	
	
}
