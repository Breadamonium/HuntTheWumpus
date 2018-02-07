package main.java;

import main.java.util.MovementUtil;
import java.util.concurrent.ThreadLocalRandom;

public interface Occupant {
	public int getXcoordinate();
	public int getYcoordinate();
	public void setXcoordinate(int currentXcoordinate);
	public void setYcoordinate(int currentYcoordinate);
	
	public default void move(Direction direction, Map map) {
		if (Direction.SOUTH == direction) 
			MovementUtil.moveSouth(map, this);
		else if (Direction.NORTH == direction)
			MovementUtil.moveNorth(map, this);
		else if (Direction.EAST == direction)
			MovementUtil.moveEast(map, this);
		else if (Direction.WEST == direction)
			MovementUtil.moveWest(map, this);
		else if (Direction.REST == direction)
			MovementUtil.rest(map, this);
		else
			throw new RuntimeException();
	}
	
	public default void moveRandom(Map map) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
		Direction nextMove = Direction.getDirectionFromNumber(randomNum);
		move(nextMove, map);
	}
}
