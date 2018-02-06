package main.java;

public class Player {
	private int currentXcoordinate = 0;
	private int currentYcoordinate = 0;
	
	public void move(Direction direction, Map map) {
		if (Direction.SOUTH == direction) {
			if (map.getNumberOfRows() > (currentYcoordinate + 1))
				currentYcoordinate++;
			else
				System.out.println("Can't move south from here.");
		} else if (Direction.NORTH == direction) {
			if (0 < currentYcoordinate)
				currentYcoordinate--;
			else
				System.out.println("Can't move north from here.");
		}
	}
	
	public int getXcoordinate() {
		return currentXcoordinate;
	}
	
	public int getYcoordinate() {
		return currentYcoordinate;
	}

	public void setXcoordinate(int currentXcoordinate) {
		this.currentXcoordinate = currentXcoordinate;
	}

	public void setYcoordinate(int currentYcoordinate) {
		this.currentYcoordinate = currentYcoordinate;
	}
	
	
}