package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Occupant;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class OccupantTest {
	protected static final String NORTH_MOVEMENT_WARNING = "Can't move north from here.\r\n";
	protected static final String SOUTH_MOVEMENT_WARNING = "Can't move south from here.\r\n";
	protected static final Object EAST_MOVEMENT_WARNING = "Can't move east from here.\r\n";
	protected static final Object WEST_MOVEMENT_WARNING = "Can't move west from here.\r\n";

	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	protected Map map;
	protected Player player;
	protected Wumpus wumpus; 
	protected Cavern cavernAt0x0y;
	protected Cavern cavernAt4x3y;
	protected Cavern surroundedCavern;
	protected Cavern cavernToBeOccupied;
	protected Cavern vacatedCavern;
	
	@Before
	public void setup() {
		map = new Map(5, 4);
		wumpus = map.getWumpus();
		player = map.getPlayer();
		
		cavernAt0x0y = map.getCavernsGrid()[0][0];
		cavernAt4x3y = map.getCavernsGrid()[4][3];
	
		assertOccupantSpawnedCorrectly(player, 0, 0);
		assertOccupantSpawnedCorrectly(wumpus, 4, 3);
		
		surroundedCavern = map.getCavernsGrid()[1][1];
		
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	@Test
	public void whenOccupantTriesRandomMove_moveSuccessful() {
		int columnAfterMove;
		int rowAfterMove;
		
		int northCount = 0;
		int southCount = 0;
		int eastCount = 0;
		int westCount = 0;
		int restCount = 0;
		
		for (int i = 0; i < 1000; i++) {
			moveOccupantToCavern(wumpus, 1, 1);
			wumpus.moveRandomly(map);
			columnAfterMove = wumpus.getColumn();
			rowAfterMove = wumpus.getRow();
			
			if (columnAfterMove < 1)
				westCount++;
			if (columnAfterMove > 1)
				eastCount++;
			if (rowAfterMove < 1)
				northCount++;
			if (rowAfterMove > 1)
				southCount++;
			if (rowAfterMove == 1 && columnAfterMove == 1)
				restCount++;
		}
		
		assertTrue(northCount > 150);
		assertTrue(southCount > 150);
		assertTrue(eastCount > 150);
		assertTrue(westCount > 150);
		assertTrue(restCount > 150);
	}
	
	public void assertOccupantSpawnedCorrectly(Occupant occupant, int columnX, int rowY) {
		Cavern spawnCavern = map.getCavernsGrid()[columnX][rowY];
		assertOccupantCoordinates(occupant, columnX, rowY);
		assertCavernContainsOccupant(spawnCavern, occupant);
	}
	
	protected void assertOccupantCoordinates(Occupant occupant, int columnX, int columnY) {
		assertEquals(columnX, occupant.getColumn());
		assertEquals(columnY, occupant.getRow());
	}
	
	protected void assertCavernContainsOccupant(Cavern cavern, Occupant occupant) {
		assertTrue(cavern.getOccupants().contains(occupant));
	}

	protected void assertCavernDoesntContainsOccupant(Cavern cavern, Occupant occupant) {
		assertFalse(cavern.getOccupants().contains(occupant));
	}
	
	protected void assertOccupantMovedCaverns(Occupant occupant, Cavern vacatedCavern, Cavern newlyOccupiedCavern) {
		assertFalse(vacatedCavern.getOccupants().contains(occupant));
		assertTrue(newlyOccupiedCavern.getOccupants().contains(occupant));
	}
	
	protected void moveOccupantToCavern(Occupant occupant, int columnX, int rowY) {
		cavernToBeOccupied = map.getCavernsGrid()[columnX][rowY];
		cavernAt4x3y.removeOccupant(occupant);
		occupant.setColumn(columnX);
		occupant.setRow(rowY);
		cavernToBeOccupied.addOccupant(occupant);
		
		assertOccupantMovedToCavern(occupant, columnX, rowY);
	}
	
	private void assertOccupantMovedToCavern(Occupant occupant, int columnX, int columnY) {
		assertEquals(columnX, occupant.getColumn());
		assertEquals(columnY, occupant.getRow());
		assertTrue(cavernToBeOccupied.getOccupants().contains(occupant));
		assertFalse(cavernAt4x3y.getOccupants().contains(occupant));
	}
}
