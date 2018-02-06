package main.java;

public class Map {

	private int[][] grid;
	
	private Map() {
	}
	
	public Map(int columns, int rows) {
		grid = new int[columns][rows];
	}
	
	public int[][] getGrid() {
		return grid;
	}
}