package engine;

public class Path extends Tile {
	
	boolean hasFood;

	// Exception caught from Tile constructor
	public Path(boolean hasFood, int x, int y) throws Exception {
		super("./images/cleared.png", x, y);
		this.hasFood = hasFood;
	}
}