package engine;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player {
	private int xPos; 
	private int yPos;

	private ImageView playerImage;

	public Player(String imgSource, int xPos, int yPos) throws Exception {
		this.xPos = xPos;
		this.yPos = yPos; 
	
		this.playerImage = new ImageView(new Image(new FileInputStream(imgSource)));
		this.playerImage.setPreserveRatio(true);
		this.playerImage.setFitHeight(Preferences.TILE_SIZE);
		updateImagePosition();
	} 

	private void updateImagePosition() {
		// sets x and y ImageView positions according to current Player positions
		this.playerImage.setY(this.yPos * Preferences.TILE_SIZE);
		this.playerImage.setX(this.xPos * Preferences.TILE_SIZE);
	}

	public ImageView getPlayerImage() {
		return this.playerImage;
	}

	public void moveUp() {
		this.yPos --;
		updateImagePosition();
	}

	public void moveDown() {
		this.yPos ++;
		updateImagePosition();
	}

	public void moveRight() {
		this.xPos++;
		updateImagePosition();
	}

	public void moveLeft() {
		this.xPos--;
		updateImagePosition();
	}
}