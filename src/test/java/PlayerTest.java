package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.Direction;
import main.java.Map;
import main.java.Player;

public class PlayerTest {
	private Map map;
	private Player player;
	private static final int NUMBER_5 = 5;
	
	@Before
	public void setup() {
		map = new Map(NUMBER_5, NUMBER_5);
		player = map.getPlayer();
	}
	
	@Test
	public void whenPlayerSpawns_ensureTheySpawnAt0x0y() {
		assertEquals(0, player.getXcoordinate());
		assertEquals(0, player.getYcoordinate());
	}
	
	@Test
	public void whenPlayerTriesToMoveSouth_moveSuccessful() {
		player.move(Direction.SOUTH, map);
		assertEquals(0, player.getXcoordinate());
		assertEquals(1, player.getYcoordinate());
	}

	@Test
	public void whenPlayerTriesToMoveNorth_moveSuccessful() {
		player.setYcoordinate(3);
		player.move(Direction.NORTH, map);
		assertEquals(2, player.getYcoordinate());
	}
	
	@Test
	public void whenPlayerTriesToMoveSouth_butIsBlocked_moveFails() {
		player.setYcoordinate(4);
		player.move(Direction.SOUTH, map);
		assertEquals(4, player.getYcoordinate());
	}
}
