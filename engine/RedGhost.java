package engine; 

import scenerendering.ScreenException;

public class RedGhost extends Ghost {

	public RedGhost(int xPos, int yPos) throws ScreenException {
		super(xPos, yPos, "./images/red-ghost.png");
	}
}