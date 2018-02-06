package main.java;

import main.java.util.MovementUtil;

public class Player {
	private int currentXcoordinate = 0;
	private int currentYcoordinate = 0;
	
	public void move(Direction direction, Map map) {
		if (Direction.SOUTH == direction) 
			MovementUtil.moveSouth(map, this);
		else if (Direction.NORTH == direction)
			MovementUtil.moveNorth(map, this);
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