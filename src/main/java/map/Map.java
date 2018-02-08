package main.java.map;

import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class Map {
	private static final int MAX_NUMBER_OF_CAVERNS = 25;
	
	private Cavern[][] grid;
	private Player player;
	private Wumpus wumpus;
	
	@SuppressWarnings("unused")
	private Map() {
	}
	
	public Map(int columnsX, int rowsY) {
		if (validateColumnsAndRows(columnsX, rowsY)) {
			initializeGridWithAllCaverns(columnsX, rowsY);
			addPlayerToGrid();
			addWumpusToGrid(columnsX, rowsY);
		} else
			throw new RuntimeException();
	}
	
	private void addPlayerToGrid() {
		player = new Player();
		grid[player.getColumn()][player.getRow()].addOccupant(player);
	}

	private void addWumpusToGrid(int columnsX, int rowsY) {
		wumpus = new Wumpus();
		wumpus.setColumn(columnsX - 1);
		wumpus.setRow(rowsY - 1);
		grid[wumpus.getColumn()][wumpus.getRow()].addOccupant(wumpus);
	}

	private void initializeGridWithAllCaverns(int columnsX, int rowY) {
		grid = new Cavern[columnsX][rowY];
		for (int column = 0; column < columnsX; column++)
			for (int row = 0; row < rowY; row++)
				grid[column][row] = new Cavern();
	}
	
	public Cavern[][] getCavernsGrid() {
		return grid;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Wumpus getWumpus() {
		return wumpus;
	}
	
	public int getNumberOfColumns() {
		return grid.length;
	}

	public int getNumberOfRows() {
		return grid[0].length;
	}
	
	public int getNumberOfCaverns() {
		return grid[0].length * grid.length;
	}
	
	public boolean validateColumnsAndRows(int columnsX, int rowsY) {
		return (columnsX * rowsY) <= MAX_NUMBER_OF_CAVERNS;
	}
}