package engine; 

public class Pacman extends Player {

	public Pacman(int xPos, int yPos) {
		super(Preferences.PACMAN_SRC, xPos, yPos);
	}

	private boolean hasSamePosition(Ghost ghost) {
		return this.getXPos() == ghost.getXPos() && this.getYPos() == ghost.getYPos();
	}

	public boolean touchedGhosts(Ghost ... ghosts) {
		for(Ghost ghost : ghosts)
			if(this.hasSamePosition(ghost))
				return true;
		return false;
	}
}