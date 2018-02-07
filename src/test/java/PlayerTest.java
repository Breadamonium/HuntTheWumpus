package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.Cavern;
import main.java.Direction;
import main.java.Map;
import main.java.Player;

public class PlayerTest {
	private Map map;
	private Player player;
	private Cavern cavernAt0x0y;
	private Cavern cavernToBeOccupied;
	private Cavern vacatedCavern;
	private static final int NUMBER_5 = 5;
	
	@Before
	public void setup() {
		map = new Map(NUMBER_5, NUMBER_5);
		player = map.getPlayer();
		cavernAt0x0y = map.getCavernsGrid()[0][0];
	}
	
	@Test
	public void whenPlayerSpawns_ensureTheySpawnCorrectly() {
		assertPlayerXYcoordinates(0, 0);
		assertTrue(cavernAt0x0y.getOccupants().contains(player));
	}
	
	@Test
	public void whenPlayerTriesToMoveSouth_cavernsAndPlayerUpdatedSuccessfully() {		
		cavernToBeOccupied = map.getCavernsGrid()[1][0];

		assertPlayerXYcoordinates(0, 0);
		assertTrue(cavernAt0x0y.getOccupants().contains(player));
		assertFalse(cavernToBeOccupied.getOccupants().contains(player));

		player.move(Direction.SOUTH, map);
		
		assertEquals(0, player.getXcoordinate());
		assertEquals(1, player.getYcoordinate());
		assertFalse(cavernAt0x0y.getOccupants().contains(player));
		assertTrue(cavernToBeOccupied.getOccupants().contains(player));
	}

	@Test
	public void whenPlayerTriesToMoveNorth_cavernsAndPlayerUpdatedSuccessfully() {
		movePlayerToCavern(0, 3);		
		
		cavernToBeOccupied = map.getCavernsGrid()[2][0];
		assertFalse(cavernToBeOccupied.getOccupants().contains(player));
		
		player.move(Direction.NORTH, map);
		
		assertPlayerXYcoordinates(0, 2);
		vacatedCavern = map.getCavernsGrid()[3][0];
		assertFalse(vacatedCavern.getOccupants().contains(player));
		assertTrue(cavernToBeOccupied.getOccupants().contains(player));
	}
	
	@Test
	public void whenPlayerTriesToMoveSouth_butIsInOutsideCavernOnGrid_moveFails() {
		movePlayerToCavern(0, 4);
	
		player.move(Direction.SOUTH, map);
		
		assertEquals(0, player.getXcoordinate());
		assertEquals(4, player.getYcoordinate());
		cavernToBeOccupied = map.getCavernsGrid()[4][0];
		assertTrue(cavernToBeOccupied.getOccupants().contains(player));
	}
	
	@Test
	public void whenPlayerTriesToMoveNorth_butIsInOutsideCavernOnGrid_moveFails() {
		assertPlayerXYcoordinates(0, 0);
		assertTrue(cavernAt0x0y.getOccupants().contains(player));
		
		player.move(Direction.NORTH, map);
		
		assertPlayerXYcoordinates(0, 0);
		assertTrue(cavernAt0x0y.getOccupants().contains(player));
	}

	private void movePlayerToCavern(int x, int y) {
		cavernToBeOccupied = map.getCavernsGrid()[y][x];
		cavernAt0x0y.removeOccupant(player);
		player.setXcoordinate(x);
		player.setYcoordinate(y);
		cavernToBeOccupied.addOccupant(player);
		
		assertPlayerMovedToCavern(x, y);
	}
	
	private void assertPlayerMovedToCavern(int x, int y) {
		assertEquals(x, player.getXcoordinate());
		assertEquals(y, player.getYcoordinate());
		assertFalse(cavernAt0x0y.getOccupants().contains(player));
		cavernToBeOccupied = map.getCavernsGrid()[y][x];
		assertTrue(cavernToBeOccupied.getOccupants().contains(player));
	}

	private void assertPlayerXYcoordinates(int x, int y) {
		assertEquals(x, player.getXcoordinate());
		assertEquals(y, player.getYcoordinate());
	}
}
