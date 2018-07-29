package engine;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Path extends Tile {
	
	boolean hasFood;

	// Exception caught from Tile constructor
	public Path(boolean hasFood, int x, int y) throws Exception {
		super(Preferences.PATH_FOOD_SRC, x, y);
		this.hasFood = hasFood;

		if(!this.hasFood)
			this.setCleared();
	}

	public void setCleared() {
		hasFood = false;
		this.setTileImageView(Preferences.PATH_CLEARED_SRC);
	}

	public boolean hasFood() {
		return hasFood;
	}
}