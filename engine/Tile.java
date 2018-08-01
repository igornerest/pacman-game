package engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import scenerendering.ScreenException;

public abstract class Tile {

	private ImageView tileImageView;

	// method may throw an exception if the source of the Image is wrong
	public Tile(String imgSource, int x, int y) throws ScreenException {
		this.tileImageView = new ImageView();
		setTileImageView(imgSource);

		// setting x and y positions according to the tile size (20)
		this.tileImageView.setX(x * 20);
		this.tileImageView.setY(y * 20);
		this.tileImageView.setPreserveRatio(true);
		this.tileImageView.setFitHeight(20);
	}

	protected void setTileImageView (String imgSource) throws ScreenException {
		this.tileImageView.setImage(new Image(imgSource));

		if(this.tileImageView.getImage() == null)
			throw new ScreenException("Error when loading images");
	}

	public ImageView getTileImageView () {
		return this.tileImageView;
	}
}