package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.Direction;

public class DirectionTest {
	@Test
	public void getDirectionFromLetter_returnsCorrectDirectionEnum() {
		assertEquals(Direction.NORTH, Direction.getDirectionFromLetter("N"));
		assertEquals(Direction.SOUTH, Direction.getDirectionFromLetter("S"));
		assertEquals(Direction.EAST, Direction.getDirectionFromLetter("E"));
		assertEquals(Direction.WEST, Direction.getDirectionFromLetter("W"));
	}

	@Test
	public void getDirectionFromNumber_returnsCorrectDirectionEnum() {
		assertEquals(Direction.SOUTH, Direction.getDirectionFromNumber(0));
		assertEquals(Direction.NORTH, Direction.getDirectionFromNumber(1));
		assertEquals(Direction.EAST, Direction.getDirectionFromNumber(2));
		assertEquals(Direction.WEST, Direction.getDirectionFromNumber(3));
	}
}
