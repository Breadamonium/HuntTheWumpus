package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.Direction;

public class PlayerTest extends OccupantTest {	
	@Test
	public void whenPlayerTriesToMoveSouth_cavernsAndPlayerUpdatedSuccessfully() {		
		cavernToBeOccupied = map.getCavernsGrid()[0][1];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, player);

		player.move(Direction.SOUTH, map);
		
		assertOccupantCoordinates(player, 0, 1);
		assertOccupantMovedCaverns(player, cavernAt0x0y, cavernToBeOccupied);
	}

	@Test
	public void whenPlayerTriesToMoveNorth_cavernsAndPlayerUpdatedSuccessfully() {
		moveOccupantToCavern(player, 1, 3);		
		
		cavernToBeOccupied = map.getCavernsGrid()[1][2];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, player);
		
		player.move(Direction.NORTH, map);
		
		vacatedCavern = map.getCavernsGrid()[1][3];
		assertOccupantCoordinates(player, 1, 2);
		assertOccupantMovedCaverns(player, vacatedCavern, cavernToBeOccupied);
	}

	@Test
	public void whenPlayerTriesToMoveEast_cavernsAndPlayerUpdatedSuccessfully() {
		cavernToBeOccupied = map.getCavernsGrid()[1][0];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, player);
		
		player.move(Direction.EAST, map);
		
		assertOccupantCoordinates(player, 1, 0);
		assertOccupantMovedCaverns(player, cavernAt0x0y, cavernToBeOccupied);
	}
	
	@Test
	public void whenPlayerTriesToMoveWest_cavernsAndPlayerUpdatedSuccessfully() {
		moveOccupantToCavern(player, 3, 0);		
		
		cavernToBeOccupied = map.getCavernsGrid()[2][0];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, player);
		
		player.move(Direction.WEST, map);
		
		vacatedCavern = map.getCavernsGrid()[3][0];
		assertOccupantCoordinates(player, 2, 0);
		assertOccupantMovedCaverns(player, vacatedCavern, cavernToBeOccupied);
	}

	@Test
	public void whenPlayerTriesToRest_restSuccessfully() {
		player.move(Direction.REST, map);
		
		assertOccupantCoordinates(player, 0, 0);
		assertTrue(cavernAt0x0y.getOccupants().contains(player));
	}
	
	@Test
	public void whenPlayerTriesToMoveSouth_butIsInOutsideCavernOnGrid_moveFails() {
		moveOccupantToCavern(player, 1, 3);
	
		player.move(Direction.SOUTH, map);
		
		cavernToBeOccupied = map.getCavernsGrid()[1][3];
		assertOccupantCoordinates(player, 1, 3);
		assertCavernContainsOccupant(cavernToBeOccupied, player);
		assertEquals(SOUTH_MOVEMENT_WARNING, outContent.toString());
	}
	
	@Test
	public void whenPlayerTriesToMoveNorth_butIsInOutsideCavernOnGrid_moveFails() {
		player.move(Direction.NORTH, map);
		
		assertOccupantCoordinates(player, 0, 0);
		assertCavernContainsOccupant(cavernAt0x0y, player);
		assertEquals(NORTH_MOVEMENT_WARNING, outContent.toString());
	}
	
	@Test
	public void whenPlayerTriesToMoveEast_butIsInOutsideCavernOnGrid_moveFails() {
		moveOccupantToCavern(player, 4, 0);
	
		player.move(Direction.EAST, map);
		
		cavernToBeOccupied = map.getCavernsGrid()[4][0];
		assertOccupantCoordinates(player, 4, 0);
		assertCavernContainsOccupant(cavernToBeOccupied, player);
		assertEquals(EAST_MOVEMENT_WARNING, outContent.toString());
	}
	
	@Test
	public void whenPlayerTriesToMoveWest_butIsInOutsideCavernOnGrid_moveFails() {
		player.move(Direction.WEST, map);
		
		assertOccupantCoordinates(player, 0, 0);
		assertCavernContainsOccupant(cavernAt0x0y, player);
		assertEquals(WEST_MOVEMENT_WARNING, outContent.toString());
	}
}
