package main.java;

public class Wumpus implements Occupant {
	private int currentXcoordinate = 0;
	private int currentYcoordinate = 0;
	
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
