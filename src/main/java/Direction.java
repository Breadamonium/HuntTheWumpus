package main.java;

public enum Direction {
	SOUTH("S", 0), NORTH("N", 1), EAST("E" , 2), WEST("W" , 3), REST("R", 4);
	
	private String directionCode;
	private int directionNumber;
	
	private Direction(String directionCode, int directionNumber) {
		this.directionCode = directionCode;
		this.directionNumber = directionNumber;
	}
	
	public String getCode() {
		return directionCode;
	}
	
	public int getNumber() {
		return directionNumber;
	}
	
	public static Direction getDirectionFromNumber(int num) {
		for (Direction direction : Direction.values()) {
			if (direction.getNumber() == num) 
				return direction;
		}
		return null;
	}
	
	public static Direction getDirectionFromLetter(String letter) {
		for (Direction direction : Direction.values()) {
			if (direction.getCode().equals(letter))
				return direction;
		}
		return null;
	}
}