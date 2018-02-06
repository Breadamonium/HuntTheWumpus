package main.java;

public class Player {
	private int currentXcoordinate = 0;
	private int currentYcoordinate = 0;
	
	public void move(Direction direction) {
		if (Direction.SOUTH == direction)
			currentYcoordinate++;
	}
	
	public int getXcoordinate() {
		return currentXcoordinate;
	}
	
	public int getYcoordinate() {
		return currentYcoordinate;
	}
}