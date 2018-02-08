package main.java.occupants;

import java.util.concurrent.ThreadLocalRandom;

import main.java.Direction;
import main.java.map.Map;
import main.java.util.MovementUtil;

public abstract class Occupant {
	int currentColumn = 0;
	int currentRow = 0;
	
	public int getColumn() {
		return currentColumn;
	}

	public int getRow() {
		return currentRow;
	}

	public void setColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}

	public void setRow(int currentRow) {
		this.currentRow = currentRow;
	}
	
	public void teleport(Map map, int xEnd, int yEnd) {
		int currentX = this.currentColumn;
		int currentY = this.currentRow;
		map.getCavernsGrid()[currentX][currentY].removeOccupant(this);
		this.setColumn(xEnd);
		this.setRow(yEnd);
		map.getCavernsGrid()[xEnd][yEnd].addOccupant(this);
	}
	
	public boolean checkIsInStraightPath(int xCoordA, int yCoordA, int xCoordB, int yCoordB) {
		return checkIfCoordinatesAreSame(xCoordA, xCoordB) || checkIfCoordinatesAreSame(yCoordA, yCoordB);
	}
	
	public boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
	
	
	public boolean move(Direction direction, Map map) {
		if (Direction.SOUTH == direction) 
			return MovementUtil.moveSouth(map, this);
		else if (Direction.NORTH == direction) {
			return MovementUtil.moveNorth(map, this);
		} else if (Direction.EAST == direction)
			return MovementUtil.moveEast(map, this);
		else if (Direction.WEST == direction)
			return MovementUtil.moveWest(map, this);
		else if (Direction.REST == direction)
			return MovementUtil.rest(map, this);
		else
			throw new RuntimeException();
	}
	
	public boolean moveRandomly(Map map) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
		Direction nextMove = Direction.getDirectionFromNumber(randomNum);
		System.out.println("\tMoving " + nextMove + "...");
		return move(nextMove, map);
	}
}
