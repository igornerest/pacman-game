package engine;

import scenerendering.ScreenException;

public class Ghost extends Player {

	private String dirVector[];

	public Ghost(int xPos, int yPos, String imgSource) throws ScreenException {
		super(xPos, yPos, imgSource);
		dirVector = new String[5];
	}

	@Override
	public void move(Map map) {
		super.makeMovement(this.choosePath(map));
	}

	private String choosePath(Map map) {
		switch(super.getMovement()) {
			case "UP":
				return sortMovement(map, super.getMovement(), "LEFT", "RIGHT");

			case "DOWN":
				return sortMovement(map, super.getMovement(), "LEFT", "RIGHT");

			case "LEFT":
				return sortMovement(map, super.getMovement(), "UP", "DOWN");

			case "RIGHT":
				return sortMovement(map, super.getMovement(), "UP", "DOWN");

			default:
				return sortMovement(map, "UP", "DOWN", "LEFT", "RIGHT");
		}
	}

	private String sortMovement(Map map, String ... movements) {
		int possibilities = 0;

		for (String movement : movements)
			if (super.canMove(map, movement))
				dirVector[possibilities++] = movement;

		if(possibilities == 0)
			return "STOPPED";
		else	
			return dirVector[(int) (Math.random() * possibilities)];
	}
}