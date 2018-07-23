package engine; 

public class Ghost extends Player {

	private String dirVector[];

	public Ghost(int xPos, int yPos, String imgSource) {
		super(imgSource, xPos, yPos);
		dirVector = new String[5];
	}

	@Override
	public void move(Map map) {
		super.makeMovement(this.choosePath(map));
	}

	private String choosePath(Map map) {
		int possibilities = 0;
		if(canMove(map, this.getMovement()))
			dirVector[possibilities++] = this.getMovement();

		if (this.getMovement().equals("UP") || this.getMovement().equals("DOWN")) {
			if(canMove(map, "LEFT"))
				dirVector[possibilities++] = "LEFT";
			if(canMove(map, "RIGHT"))
				dirVector[possibilities++] = "RIGHT";

		} else if (this.getMovement().equals("LEFT") || this.getMovement().equals("RIGHT")) {
			if(canMove(map, "UP"))
				dirVector[possibilities++] = "UP";
			if(canMove(map, "DOWN"))
				dirVector[possibilities++] = "DOWN";
		} else if (this.getMovement().equals("STOPPED")) {
			if(canMove(map, "LEFT"))
				dirVector[possibilities++] = "LEFT";
			if(canMove(map, "RIGHT"))
				dirVector[possibilities++] = "RIGHT";
			if(canMove(map, "UP"))
				dirVector[possibilities++] = "UP";
			if(canMove(map, "DOWN"))
				dirVector[possibilities++] = "DOWN";

		}

		return dirVector[(int) (Math.random() * possibilities)];
	}
}