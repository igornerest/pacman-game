package engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import scenerendering.ScreenException;

public abstract class Player {
	private int xPos; 
	private int yPos;

	private ImageView playerImageView;

	private String movement;
	private String nextMovement;

	public Player(int xPos, int yPos, String imgSource) throws ScreenException {
		this.xPos = xPos;
		this.yPos = yPos; 
		this.movement = "STOPPED";
		this.nextMovement = "STOPPED";

		this.playerImageView = new ImageView(new Image(imgSource));
		if(this.playerImageView.getImage() == null)
			throw new ScreenException("Error when loading Player Image");

		this.playerImageView.setPreserveRatio(true);
		this.playerImageView.setFitHeight(20);
	
		updateImagePosition(0);
	} 

	private void updateImagePosition(int imageRotation) {
		// sets x and y ImageView positions according to current Player positions
		this.playerImageView.setY(this.yPos * 20);
		this.playerImageView.setX(this.xPos * 20);
		this.playerImageView.setRotate(imageRotation);
	}

	public ImageView getPlayerImageView() {
		return this.playerImageView;
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

	public String getNextMovement() {
		return this.nextMovement;
	}

	public abstract void move(Map map);
	
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

			case "STOPPED":
				this.movement = "STOPPED";
				break;
		}
	}

	private void updatePosition(int xMove, int yMove, int imgRotation, String movement) {
		this.xPos = xMove;
		this.yPos= yMove;
		this.movement = movement;
		updateImagePosition(imgRotation);
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