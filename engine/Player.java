package engine;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player {
	private int xPos; 
	private int yPos;

	private ImageView playerImage;

	private String movement, nextMovement;

	private boolean isAlive;

	public Player(String imgSource, int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos; 
		this.movement = "STOPPED";
		this.nextMovement = "STOPPED";
		this.isAlive = true;

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

	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}

	public String getMovement() {
		return this.movement;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void setDeath() {
		this.isAlive = false;
	}

	private void updatePosition(int xMove, int yMove, int imgRotation, String movement) {
		this.xPos = xMove;
		this.yPos= yMove;
		this.movement = movement;
		updateImagePosition(imgRotation);
	}
	
	protected boolean canMove(Map map, String movement) {
		switch(movement) {
			case "UP":
				return map.isPath(this.xPos, this.yPos - 1);

			case "DOWN":
				return map.isPath(this.xPos, this.yPos + 1);

			case "RIGHT":
				return map.isPath(this.xPos + 1, this.yPos);

			case "LEFT":
				return map.isPath(this.xPos - 1, this.yPos);

			default: 
				return false;
		}
	}

	protected void makeMovement(String movement) {
		switch(movement) {
			case "UP":
				updatePosition(this.xPos, this.yPos - 1, -90, movement);
				break;

			case "DOWN":
				updatePosition(this.xPos, this.yPos + 1, 90, movement);
				break;

			case "RIGHT":
				updatePosition(this.xPos + 1, this.yPos, 0, movement);
				break;

			case "LEFT":
				updatePosition(this.xPos - 1, this.yPos, 180, movement);
				break;
		}
	}

	public void move(Map map) {
		if (canMove(map, this.nextMovement)) {
			makeMovement(this.nextMovement);
		} else if (canMove(map, this.movement)) {
			makeMovement(this.movement);
		}
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