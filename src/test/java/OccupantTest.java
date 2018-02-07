package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.Cavern;
import main.java.Map;
import main.java.Wumpus;

public class OccupantTest {
	private Wumpus wumpus; 
	private Map map;
	private Cavern centerCavernToBeOccupied;
	
	@Before
	public void setup() {
		map = new Map(3, 3);
		wumpus = map.getWumpus();
		centerCavernToBeOccupied = map.getCavernsGrid()[1][1];
	}
	
	@Test
	public void randomtest() {
		int xCoordinateAfterMove;
		int yCoordinateAfterMove;
		
		int northCount = 0;
		int southCount = 0;
		int eastCount = 0;
		int westCount = 0;
		int restCount = 0;
		
		for (int i = 0; i < 1000; i++) {
			moveWumpusToCenter();
			wumpus.moveRandom(map);
			xCoordinateAfterMove = wumpus.getXcoordinate();
			yCoordinateAfterMove = wumpus.getYcoordinate();
			
			if (xCoordinateAfterMove < 1)
				westCount++;
			if (xCoordinateAfterMove > 1)
				eastCount++;
			if (yCoordinateAfterMove < 1)
				northCount++;
			if (yCoordinateAfterMove > 1)
				southCount++;
			if (yCoordinateAfterMove == 1 && xCoordinateAfterMove == 1)
				restCount++;
		}
		
		assertTrue(northCount > 150);
		assertTrue(southCount > 150);
		assertTrue(eastCount > 150);
		assertTrue(westCount > 150);
		assertTrue(restCount > 150);
	}
	
	private void moveWumpusToCenter() {
		map.getCavernsGrid()[wumpus.getYcoordinate()][wumpus.getXcoordinate()].removeOccupant(wumpus);
		wumpus.setXcoordinate(1);
		wumpus.setYcoordinate(1);
		centerCavernToBeOccupied.addOccupant(wumpus);
		
		assertWumpusMovedToCenterCavern();
	}
	
	private void assertWumpusMovedToCenterCavern() {
		assertEquals(1, wumpus.getXcoordinate());
		assertEquals(1, wumpus.getYcoordinate());
		assertTrue(centerCavernToBeOccupied.getOccupants().contains(wumpus));
	}
}
