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

	private void move(Map map, int xMove, int yMove) {
		// checks if there's path to move
		// if so, updates player's positions
		if(map.isPath(xMove, yMove)) {
			this.xPos = xMove;
			this.yPos= yMove;
			updateImagePosition();
		}
	}

	public void moveUp(Map map) {
		move(map, this.xPos, this.yPos - 1);
	}

	public void moveDown(Map map) {
		move(map, this.xPos, this.yPos + 1);
	}

	public void moveRight(Map map) {
		System.out.println(map.upa(this.xPos + 1, this.yPos));
		move(map, this.xPos + 1, this.yPos);
	}

	public void moveLeft(Map map) {
		move(map, this.xPos - 1, this.yPos);
	}


}