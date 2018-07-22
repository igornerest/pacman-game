package engine;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player {
	private int xPos; 
	private int yPos;

	private ImageView playerImage;

	private String movement;

	public Player(String imgSource, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos; 
		this.movement = "STOPPED";
	
		try (FileInputStream fileStream = new FileInputStream(imgSource)) {
			this.playerImage = new ImageView(new Image(fileStream));
			this.playerImage.setPreserveRatio(true);
			this.playerImage.setFitHeight(Preferences.TILE_SIZE);
		} catch (Exception e) {
			System.out.println(e);
		}
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

	private void checknUpdate(Map map, int xMove, int yMove) {
		// checks if there's path to move
		// if so, updates player's positions
		if(map.isPath(xMove, yMove)) {
			this.xPos = xMove;
			this.yPos= yMove;
			updateImagePosition();
		}
	}
	
	public void move(Map map) {
		// player moves everytime AnimationTimer ticks, according to player's movement status
		switch(this.movement) {
			case "UP":
				checknUpdate(map, this.xPos, this.yPos - 1);
				break;

			case "DOWN":
				checknUpdate(map, this.xPos, this.yPos + 1);
				break;

			case "RIGHT":
				checknUpdate(map, this.xPos + 1, this.yPos);
				break;

			case "LEFT":
				checknUpdate(map, this.xPos - 1, this.yPos);
				break;
		}
	}

	// Following methods update player's movement status
	public void setUp() {
		this.movement = "UP";
		this.playerImage.setRotate(-90); 
	}

	public void setDown() {
		this.movement = "DOWN";
		this.playerImage.setRotate(90); 
	}

	public void setRight() {
		this.movement = "RIGHT";
		this.playerImage.setRotate(0); 
	}

	public void setLeft() {
		this.movement = "LEFT";
		this.playerImage.setRotate(180); 
	}
}