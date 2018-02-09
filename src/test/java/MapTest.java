package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.map.Map;

public class MapTest {
	private Map map;
	private static final int NUMBER_OF_ROWS = 4;
	private static final int NUMBER_OF_COLUMNS = 5;
	
	@Before
	public void setup() {
		map = new Map(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);		
	}
	
	@Test
	public void whenMapIsCreated_columnsAndRowsGeneratedSuccessfully() {
		assertEquals(NUMBER_OF_COLUMNS, map.getNumberOfColumns());
		assertEquals(NUMBER_OF_ROWS, map.getNumberOfRows());
	}
	
	@Test
	public void when4x5mapIsCreated_contains20caverns() {
		assertEquals(20, map.getNumberOfCaverns());
	}
	
	@Test
	public void whenMapIsCreated_playerSpawnsAt0x0y() {
		assertTrue(map.getCavernsGrid()[0][0].getOccupants().contains(map.getPlayer()));
	}

	@Test
	public void whenMapIsCreated_wumpusSpawnsAt4x3y() {
		assertTrue(map.getCavernsGrid()[4][3].getOccupants().contains(map.getWumpus()));
	}

	@Test(expected = RuntimeException.class)
	public void when6x5mapIsCreated_runtimeExceptionIsThrown() {
		map = new Map(6, 5);
	}
	
	@Test
	public void whenMapIsCreated_allCavernsAreInitialized() {
		for (int i = 0; i < map.getNumberOfColumns(); i++)
			for (int j = 0; j < map.getNumberOfRows(); j++)
				if (i != 0 && j != 4)
					assertNotNull(map.getCavernsGrid()[i][j]);
	}
}
