package engine; 

import scenerendering.ScreenException;

public class GreenGhost extends Ghost {

	public GreenGhost(int xPos, int yPos) throws ScreenException {
		super(xPos, yPos, "./images/green-ghost.png");
	}
}