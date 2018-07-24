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

	private String sortMovement(Map map, String ... movements) {
		int possibilities = 0;

		for (String movement : movements)
			if(this.canMove(map, movement))
				dirVector[possibilities++] = movement;

		return dirVector[(int) (Math.random() * possibilities)];
	}

	private String choosePath(Map map) {
		if(this.getMovement().equals("UP") || this.getMovement().equals("DOWN"))
			return sortMovement(map, this.getMovement(), "LEFT", "RIGHT");
		else if(this.getMovement().equals("LEFT") || this.getMovement().equals("RIGHT"))
			return sortMovement(map, this.getMovement(), "UP", "DOWN");
		else 
			return sortMovement(map,this.getMovement(), "UP", "DOWN", "LEFT", "RIGHT");	
	}
}