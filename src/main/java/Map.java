package main.java;

public class Map {

	private static final int MAX_NUMBER_OF_CAVERNS = 25;
	private Object[][] grid;
	private Player player;
	
	private Map() {
	}
	
	public Map(int columns, int rows) {
		if (validateColumnsAndRows(columns, rows)) {
			grid = new Object[columns][rows];
			player = new Player();
		} else
			throw new RuntimeException();
	}
	
	public Object[][] getGrid() {
		return grid;
	}

	public Player getPlayer() {
		return player;
	}
	
	public int getNumberOfCaverns() {
		return grid[0].length * grid.length;
	}
	
	public boolean validateColumnsAndRows(int columns, int rows) {
		return (columns * rows) <= MAX_NUMBER_OF_CAVERNS;
	}
}