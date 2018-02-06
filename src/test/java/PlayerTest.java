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
		player.move(Direction.SOUTH);
		assertEquals(0, player.getXcoordinate());
		assertEquals(1, player.getYcoordinate());
	}
}
