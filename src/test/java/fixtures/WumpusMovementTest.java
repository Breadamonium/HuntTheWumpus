package test.java.fixtures;

import static org.junit.Assert.assertTrue;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Wumpus;

public class WumpusMovementTest {
	private int endx;
	private int endy;
	private int minTimes;
	private int maxTimes;
	private Map map;
	private Wumpus wumpus;
	private Cavern centerCavernToBeOccupied;
	
	public void execute() {
		map = new Map(5, 5);
		centerCavernToBeOccupied = map.getCavernsGrid()[2][2];
		wumpus = map.getWumpus();
	}
	
	public int getEndx() {
		return endx;
	}
	
	public void setEndx(int endx) {
		this.endx = endx;
	}
	
	public int getEndy() {
		return endy;
	}
	
	public void setEndy(int endy) {
		this.endy = endy;
	}
	
	public int getMinTimes() {
		return minTimes;
	}
	
	public void setMinTimes(int minTimes) {
		this.minTimes = minTimes;
	}
	
	public int getMaxTimes() {
		return maxTimes;
	}
	
	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}
	
	public boolean simulate() {
		int xCoordinateAfterMove;
		int yCoordinateAfterMove;

		int count = 0;
		
		for (int i = 0; i < 1000; i++) {
			moveWumpusToCenter();
			wumpus.moveRandomly(map);
			xCoordinateAfterMove = wumpus.getColumn();
			yCoordinateAfterMove = wumpus.getRow();
			
			if (yCoordinateAfterMove == endy && xCoordinateAfterMove == endx)
				count++;
		}
		
		return count >= minTimes && count <= maxTimes;
	}
	
	private void moveWumpusToCenter() {
		map.getCavernsGrid()[wumpus.getColumn()][wumpus.getRow()].removeOccupant(wumpus);
		wumpus.setRow(2);
		wumpus.setColumn(2);
		centerCavernToBeOccupied.addOccupant(wumpus);
	}
}
