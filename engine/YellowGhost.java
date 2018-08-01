package engine;

import scenerendering.ScreenException;

public class YellowGhost extends Ghost {

	public YellowGhost(int xPos, int yPos) throws ScreenException {
		super(xPos, yPos, "./images/yellow-ghost.png");
	}
}