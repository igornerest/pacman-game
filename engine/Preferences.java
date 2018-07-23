package engine;

public abstract class Preferences {

	public static final String MAIN_TEXT = "PACMAN!\n"
									+ "Algum texto explicativo\n"
									+ "outro texto explicativo\n\n\n"
									+ "Selecione seu nivel e pressione ENTER!";

	public static final int N_LEVELS = 5;	// number of levels

	public static final int TILE_SIZE = 20;	// size of each tile that composes the map

	public static final long NSPF = 150000000;

	// source of each player's images
	public static final String PACMAN_SRC 		= "./images/pacman.png";
	public static final String YELLOW_GHOST_SRC = "./images/yellow-ghost.png";
	public static final String BLUE_GHOST_SRC 	= "./images/blue-ghost.png";
	public static final String RED_GHOST_SRC 	= "./images/red-ghost.png";
	public static final String GREEN_GHOST_SRC 	= "./images/green-ghost.png";

	// main icon image
	public static final String ICON_SRC	= "./images/pacman-icon.png";

	public static final String LEVEL_SRC[] = new String[] {
		"./maps/level1.txt",
		"./maps/level2.txt",
		"./maps/level3.txt",
		"./maps/level4.txt",
		"./maps/level5.txt",
	};

	public static final String LEVEL_NAME[] = new String[] {
		"Level 1",
		"Level 2",
		"Level 3",
		"Level 4",
		"Level 5"
	};

}