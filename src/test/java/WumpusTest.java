package test.java;

import org.junit.Test;

import main.java.Direction;

public class WumpusTest extends OccupantTest {
	
	@Test
	public void whenWumpusTriesToMoveNorth_cavernsAndWumpusUpdatedSuccessfully() {
		cavernToBeOccupied = map.getCavernsGrid()[4][2];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, wumpus);

		wumpus.move(Direction.NORTH, map);
		
		assertOccupantCoordinates(wumpus, 4, 2);
		assertOccupantMovedCaverns(wumpus, cavernAt4x3y, cavernToBeOccupied);
	}
	
	@Test
	public void whenWumpusTriesToMoveWest_cavernsAndWumpusUpdatedSuccessfully() {
		cavernToBeOccupied = map.getCavernsGrid()[3][3];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, wumpus);

		wumpus.move(Direction.WEST, map);
		
		assertOccupantCoordinates(wumpus, 3, 3);
		assertOccupantMovedCaverns(wumpus, cavernAt4x3y, cavernToBeOccupied);
	}
	
	@Test
	public void whenWumpusTriesToMoveSouth_cavernsAndWumpusUpdatedSuccessfully() {
		moveOccupantToCavern(wumpus, 2, 0);		
		
		cavernToBeOccupied = map.getCavernsGrid()[2][1];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, wumpus);
		
		wumpus.move(Direction.SOUTH, map);
		
		vacatedCavern = map.getCavernsGrid()[2][0];
		assertOccupantCoordinates(wumpus, 2, 1);
		assertOccupantMovedCaverns(wumpus, vacatedCavern, cavernToBeOccupied);
	}

	@Test
	public void whenWumpusTriesToMoveEast_cavernsAndWumpusUpdatedSuccessfully() {
		moveOccupantToCavern(wumpus, 2, 0);		
		
		cavernToBeOccupied = map.getCavernsGrid()[3][0];
		assertCavernDoesntContainsOccupant(cavernToBeOccupied, wumpus);
		
		wumpus.move(Direction.EAST, map);
		
		vacatedCavern = map.getCavernsGrid()[2][0];
		assertOccupantCoordinates(wumpus, 3, 0);
		assertOccupantMovedCaverns(wumpus, vacatedCavern, cavernToBeOccupied);
	}
	
	@Test
	public void whenWumpusTriesToRest_restSuccessfully() {
		wumpus.move(Direction.REST, map);
		
		assertOccupantCoordinates(wumpus, 4, 3);
		assertCavernContainsOccupant(cavernAt4x3y, wumpus);
	}
	
	@Test
	public void whenWumpusTriesToMoveSouth_butIsInOutsideCavernOnGrid_moveFails() {
		wumpus.move(Direction.SOUTH, map);
		
		assertOccupantCoordinates(wumpus, 4, 3);
		assertCavernContainsOccupant(cavernAt4x3y, wumpus);
	}
	
	@Test
	public void whenWumpusTriesToMoveNorth_butIsInOutsideCavernOnGrid_moveFails() {
		moveOccupantToCavern(wumpus, 0, 0);
		
		wumpus.move(Direction.NORTH, map);
		
		cavernToBeOccupied = map.getCavernsGrid()[0][0];
		assertOccupantCoordinates(wumpus, 0, 0);
		assertCavernContainsOccupant(cavernToBeOccupied, wumpus);
	}
	
	@Test
	public void whenWumpusTriesToMoveEast_butIsInOutsideCavernOnGrid_moveFails() {
		wumpus.move(Direction.EAST, map);
		
		assertOccupantCoordinates(wumpus, 4, 3);
		assertCavernContainsOccupant(cavernAt4x3y, wumpus);
	}
	
	@Test
	public void whenWumpusTriesToMoveWest_butIsInOutsideCavernOnGrid_moveFails() {
		moveOccupantToCavern(wumpus, 0, 0);
		
		wumpus.move(Direction.WEST, map);
		
		cavernToBeOccupied = map.getCavernsGrid()[0][0];
		assertOccupantCoordinates(wumpus, 0, 0);
		assertCavernContainsOccupant(cavernToBeOccupied, wumpus);
	}
}
