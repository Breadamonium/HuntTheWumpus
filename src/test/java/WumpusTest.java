package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.Cavern;
import main.java.Direction;
import main.java.Map;
import main.java.Wumpus;
import main.java.Wumpus;

public class WumpusTest {
	private Map map;
	private Wumpus wumpus;
	private Cavern cavernAt4x4y;
	private Cavern cavernToBeOccupied;
	private Cavern vacatedCavern;
	private static final int NUMBER_5 = 5;
	
	@Before
	public void setup() {
		map = new Map(NUMBER_5, NUMBER_5);
		wumpus = map.getWumpus();
		cavernAt4x4y = map.getCavernsGrid()[4][4];
	}

	@Test
	public void whenWumpusSpawns_ensureTheySpawnCorrectly() {
		assertWumpusXYcoordinates(4, 4);
		assertTrue(cavernAt4x4y.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveNorth_cavernsAndWumpusUpdatedSuccessfully() {
		cavernToBeOccupied = map.getCavernsGrid()[3][4];

		assertWumpusXYcoordinates(4, 4);
		assertTrue(cavernAt4x4y.getOccupants().contains(wumpus));
		assertFalse(cavernToBeOccupied.getOccupants().contains(wumpus));

		wumpus.move(Direction.NORTH, map);
		
		assertEquals(4, wumpus.getXcoordinate());
		assertEquals(3, wumpus.getYcoordinate());
		assertFalse(cavernAt4x4y.getOccupants().contains(wumpus));
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveWest_cavernsAndWumpusUpdatedSuccessfully() {
		cavernToBeOccupied = map.getCavernsGrid()[4][3];

		assertWumpusXYcoordinates(4, 4);
		assertTrue(cavernAt4x4y.getOccupants().contains(wumpus));
		assertFalse(cavernToBeOccupied.getOccupants().contains(wumpus));

		wumpus.move(Direction.WEST, map);
		
		assertEquals(3, wumpus.getXcoordinate());
		assertEquals(4, wumpus.getYcoordinate());
		assertFalse(cavernAt4x4y.getOccupants().contains(wumpus));
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveSouth_cavernsAndWumpusUpdatedSuccessfully() {
		moveWumpusToCavernXY(0, 3);		
		
		cavernToBeOccupied = map.getCavernsGrid()[4][0];
		assertFalse(cavernToBeOccupied.getOccupants().contains(wumpus));
		
		wumpus.move(Direction.SOUTH, map);
		
		assertWumpusXYcoordinates(0, 4);
		vacatedCavern = map.getCavernsGrid()[3][0];
		assertFalse(vacatedCavern.getOccupants().contains(wumpus));
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}

	@Test
	public void whenWumpusTriesToMoveEast_cavernsAndWumpusUpdatedSuccessfully() {
		moveWumpusToCavernXY(0, 3);		
		
		cavernToBeOccupied = map.getCavernsGrid()[3][1];
		assertFalse(cavernToBeOccupied.getOccupants().contains(wumpus));
		
		wumpus.move(Direction.EAST, map);
		
		assertWumpusXYcoordinates(1, 3);
		vacatedCavern = map.getCavernsGrid()[3][0];
		assertFalse(vacatedCavern.getOccupants().contains(wumpus));
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToRest_restSuccessfully() {
		assertWumpusXYcoordinates(4, 4);
		assertTrue(cavernAt4x4y.getOccupants().contains(wumpus));
		
		wumpus.move(Direction.REST, map);
		
		assertWumpusXYcoordinates(4, 4);
		assertTrue(cavernAt4x4y.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveSouth_butIsInOutsideCavernOnGrid_moveFails() {
		moveWumpusToCavernXY(0, 4);
	
		wumpus.move(Direction.SOUTH, map);
		
		assertWumpusXYcoordinates(0, 4);
		cavernToBeOccupied = map.getCavernsGrid()[4][0];
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveNorth_butIsInOutsideCavernOnGrid_moveFails() {
		moveWumpusToCavernXY(0, 0);
		
		wumpus.move(Direction.NORTH, map);
		
		assertWumpusXYcoordinates(0, 0);
		cavernToBeOccupied = map.getCavernsGrid()[0][0];
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveEast_butIsInOutsideCavernOnGrid_moveFails() {
		moveWumpusToCavernXY(4, 0);
	
		wumpus.move(Direction.EAST, map);
		
		assertEquals(4, wumpus.getXcoordinate());
		assertEquals(0, wumpus.getYcoordinate());
		cavernToBeOccupied = map.getCavernsGrid()[0][4];
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	@Test
	public void whenWumpusTriesToMoveWest_butIsInOutsideCavernOnGrid_moveFails() {
		moveWumpusToCavernXY(0, 0);
		
		wumpus.move(Direction.WEST, map);
		
		assertEquals(0, wumpus.getXcoordinate());
		assertEquals(0, wumpus.getYcoordinate());
		cavernToBeOccupied = map.getCavernsGrid()[0][0];
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
	}
	
	private void moveWumpusToCavernXY(int x, int y) {
		cavernToBeOccupied = map.getCavernsGrid()[y][x];
		cavernAt4x4y.removeOccupant(wumpus);
		wumpus.setXcoordinate(x);
		wumpus.setYcoordinate(y);
		cavernToBeOccupied.addOccupant(wumpus);
		
		assertWumpusMovedToCavern(x, y);
	}
	
	private void assertWumpusMovedToCavern(int x, int y) {
		assertEquals(x, wumpus.getXcoordinate());
		assertEquals(y, wumpus.getYcoordinate());
		cavernToBeOccupied = map.getCavernsGrid()[y][x];
		assertTrue(cavernToBeOccupied.getOccupants().contains(wumpus));
		assertFalse(cavernAt4x4y.getOccupants().contains(wumpus));
	}

	
	private void assertWumpusXYcoordinates(int x, int y) {
		assertEquals(x, wumpus.getXcoordinate());
		assertEquals(y, wumpus.getYcoordinate());
	}
}
