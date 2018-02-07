package main.java;

import java.util.ArrayList;
import java.util.List;

public class Map {

	private static final int MAX_NUMBER_OF_CAVERNS = 25;
	private Cavern[][] grid;
	private Player player;
	
	private Map() {
	}
	
	public Map(int columns, int rows) {
		if (validateColumnsAndRows(columns, rows)) {
			initializeAllCaverns(columns, rows);
			player = new Player();
			grid[player.getYcoordinate()][player.getXcoordinate()].addOccupant(player);
		} else
			throw new RuntimeException();
	}

	private void initializeAllCaverns(int columns, int rows) {
		grid = new Cavern[columns][rows];
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < rows; j++)
				grid[i][j] = new Cavern();
	}
	
	public Cavern[][] getCavernsGrid() {
		return grid;
	}

	public Player getPlayer() {
		return player;
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
	
	public boolean validateColumnsAndRows(int columns, int rows) {
		return (columns * rows) <= MAX_NUMBER_OF_CAVERNS;
	}
}