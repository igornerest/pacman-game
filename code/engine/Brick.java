package engine;

import scenerendering.ScreenException;

public class Brick extends Tile {

	// Exception caught from Tile constructor
	public Brick(int x, int y) throws ScreenException {
		super("./images/brick.png", x, y);
	}
}