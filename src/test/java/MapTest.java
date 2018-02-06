package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.Map;

public class MapTest {
	private Map map;
	private static final int NUMBER_OF_ROWS = 6;
	private static final int NUMBER_OF_COLUMNS = 5;
	
	@Test
	public void sdhgdg() {
		map = new Map(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
		int[][] grid = map.getGrid();
		assertEquals(NUMBER_OF_COLUMNS, getNumberOfColumns(grid));
		assertEquals(NUMBER_OF_ROWS, getNumberOfRows(grid));
	}
	
	public int getNumberOfColumns(int[][] grid) {
		return grid.length;
	}

	public int getNumberOfRows(int[][] grid) {
		return grid[0].length;
	}
}
