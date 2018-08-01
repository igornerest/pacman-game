package engine; 

import scenerendering.ScreenException;

public class BlueGhost extends Ghost {

	public BlueGhost(int xPos, int yPos) throws ScreenException {
		super(xPos, yPos, "./images/blue-ghost.png");
	}
}