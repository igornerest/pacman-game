package engine; 

public class Pacman extends Player {

	public Pacman(int xPos, int yPos) throws Exception {
		super(Preferences.PACMAN_SRC, xPos, yPos);
	}
}