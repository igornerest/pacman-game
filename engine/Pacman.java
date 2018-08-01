package engine; 

import scenerendering.ScreenException;

public class Pacman extends Player {
	private boolean isAlive;

	public Pacman(int xPos, int yPos) throws ScreenException {
		super(xPos, yPos, "./images/pacman.png");
		this.isAlive = true;
	}

	@Override
	public void move(Map map) {
		if (canMove(map, super.getNextMovement())) {
			makeMovement(super.getNextMovement());
		} else if (canMove(map, super.getMovement())) {
			makeMovement(super.getMovement());
		}
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void setDeath() {
		this.isAlive = false;
	}

	public boolean touchedGhosts(Player ... comparedPlayers) {
		for(Player comparedPlayer : comparedPlayers)
			if(!(comparedPlayer instanceof Pacman) && this.hasSamePosition(comparedPlayer))
					return true;
		return false;
	}

	private boolean hasSamePosition(Player comparedPlayer) {
		return super.getXPos() == comparedPlayer.getXPos() && super.getYPos() == comparedPlayer.getYPos();
	}
}