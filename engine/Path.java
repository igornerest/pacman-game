package engine;

import scenerendering.ScreenException;

public class Path extends Tile {
	
	private boolean hasFood;

	// Exception caught from Tile constructor
	public Path(boolean hasFood, int x, int y) throws ScreenException {
		super("./images/food.png", x, y);
		this.hasFood = hasFood;

		if(!this.hasFood)
			this.setCleared();
	}

	public void setCleared() throws ScreenException {
		super.setTileImageView("./images/cleared.png");
		hasFood = false;
	}

	public boolean hasFood() {
		return hasFood;
	}
}