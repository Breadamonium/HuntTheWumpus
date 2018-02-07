package main.java;

import main.java.util.MovementUtil;

public class Player implements Occupant {
	private int currentXcoordinate = 0;
	private int currentYcoordinate = 0;
	
	public void move(Direction direction, Map map) {
		if (Direction.SOUTH == direction) 
			MovementUtil.moveSouth(map, this);
		else if (Direction.NORTH == direction)
			MovementUtil.moveNorth(map, this);
	}
	
	@Override
	public int getXcoordinate() {
		return currentXcoordinate;
	}
	
	@Override
	public int getYcoordinate() {
		return currentYcoordinate;
	}

	@Override
	public void setXcoordinate(int currentXcoordinate) {
		this.currentXcoordinate = currentXcoordinate;
	}

	@Override
	public void setYcoordinate(int currentYcoordinate) {
		this.currentYcoordinate = currentYcoordinate;
	}
	
	
}