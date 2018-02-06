package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.Map;

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
		Object[][] grid = map.getGrid();
		assertEquals(NUMBER_OF_COLUMNS, getNumberOfColumns(grid));
		assertEquals(NUMBER_OF_ROWS, getNumberOfRows(grid));
	}
	
	@Test
	public void when4x5mapIsCreated_contains20caverns() {
		assertEquals(20, map.getNumberOfCaverns());
	}

	@Test(expected = RuntimeException.class)
	public void when6x5mapIsCreated_runtimeExceptionIsThrown() {
		map = new Map(6, 5);
	}
	
	private int getNumberOfColumns(Object[][] grid) {
		return grid.length;
	}

	private int getNumberOfRows(Object[][] grid) {
		return grid[0].length;
	}
}
