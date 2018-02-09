package test.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.map.Map;
import main.java.util.CollisionUtil;

public class CollisionUtilTest {
	Map test1;
	@Before
	public void setup() {
		test1 = new Map(5,5);	
	}
	
	@Test
	public void testCheckIfPlayerIsInPit() {		
		test1.setPitInCavern(3, 1);
		test1.getPlayer().teleport(test1, 3, 1);
		assertTrue(CollisionUtil.checkIfPlayerIsInPit(test1));
		test1.removePitsAtCavern(3, 1);
		assertFalse(CollisionUtil.checkIfPlayerIsInPit(test1));
	}

	@Test
	public void testCheckIfPlayerIsOnBats() {
		test1.setBatsInCavern(1, 2);
		test1.getPlayer().teleport(test1, 1, 2);
		assertTrue(CollisionUtil.checkIfPlayerIsOnBats(test1));
		test1.removeBatsAtCavern(1, 2);
		assertFalse(CollisionUtil.checkIfPlayerIsOnBats(test1));
	}

}
