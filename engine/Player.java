package engine;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player {
	private int xPos; 
	private int yPos;

	private ImageView playerImage;

	private String movement, nextMovement;

	public Player(String imgSource, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos; 
		this.movement = "STOPPED";
		this.nextMovement = "STOPPED";

		try (FileInputStream fileStream = new FileInputStream(imgSource)) {
			this.playerImage = new ImageView(new Image(fileStream));
			this.playerImage.setPreserveRatio(true);
			this.playerImage.setFitHeight(Preferences.TILE_SIZE);
		} catch (Exception e) {
			System.out.println(e);
		}
		updateImagePosition(0);
	} 

	private void updateImagePosition(int imageRotation) {
		// sets x and y ImageView positions according to current Player positions
		this.playerImage.setY(this.yPos * Preferences.TILE_SIZE);
		this.playerImage.setX(this.xPos * Preferences.TILE_SIZE);
		this.playerImage.setRotate(imageRotation);
	}

	public ImageView getPlayerImage() {
		return this.playerImage;
	}

	private boolean checknUpdate(Map map, int xMove, int yMove, int imgRotation) {
		// checks if there's path to move
		// if so, updates player's positions
		if(map.isPath(xMove, yMove)) {
			this.xPos = xMove;
			this.yPos= yMove;
			updateImagePosition(imgRotation);
			return true;
		}
		return false;
	}
	
	private boolean tryMovement(Map map, String movement) {
		switch(movement) {
			case "UP":
				return checknUpdate(map, this.xPos, this.yPos - 1, -90);

			case "DOWN":
				return checknUpdate(map, this.xPos, this.yPos + 1, 90);

			case "RIGHT":
				return checknUpdate(map, this.xPos + 1, this.yPos, 0);

			case "LEFT":
				return checknUpdate(map, this.xPos - 1, this.yPos, 180);

			default: 
				return false;
		}
	}

	public void move(Map map) {
		if(tryMovement(map, this.nextMovement))
			this.movement = this.nextMovement;
		else
			tryMovement(map, this.movement);
	}

	// Following methods update player's movement status
	public void setUp() {
		this.nextMovement = "UP"; 
	}

	public void setDown() {
		this.nextMovement = "DOWN";
	}

	public void setRight() {
		this.nextMovement = "RIGHT";
	}

	public void setLeft() {
		this.nextMovement = "LEFT"; 
	}
}