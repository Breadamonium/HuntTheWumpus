package main.java;

public enum Direction {
	SOUTH("S"), NORTH("N"), EAST("E"), WEST("W");
	
	private String directionCode;

	private Direction(String directionCode) {
		this.directionCode = directionCode;
	}
	
	public String getCode() {
		return directionCode;
	}
}
