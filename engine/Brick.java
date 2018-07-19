package engine;

public class Brick extends Tile {

	// Exception caught from Tile constructor
	public Brick(int x, int y) throws Exception {
		super("./images/brick.png", x, y);
	}
}