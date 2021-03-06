package main.java.map;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class Map {
	private static final int MAX_NUMBER_OF_CAVERNS = 25;
	
	private Cavern[][] grid;
	private Player player;
	private Wumpus wumpus;
	
	public Map(int columnsX, int rowsY) {
		if (validateColumnsAndRows(columnsX, rowsY)) {
			initializeGridWithAllCaverns(columnsX, rowsY);
			addPlayerToGrid();
			addWumpusToGrid(columnsX, rowsY);
			grid[3][1].setHasPit(true);
			grid[1][2].setHasPit(true);
			grid[4][0].setHasBats(true);
			grid[0][3] = null;
		} else
			throw new RuntimeException("Total number of caverns can't add up to more than 25.");
	}
	
    public Map(int columnsX, int rowsY, int numPits, int numBats) {
        if (validateColumnsAndRows(columnsX, rowsY)) {
            initializeGridWithAllCaverns(columnsX, rowsY);
            addPlayerToGrid();
            addWumpusToGrid(columnsX, rowsY);
            HashSet<Coordinate> specialCaves = new HashSet<Coordinate>();
            Coordinate playerStart = new Coordinate(0,0);
            Coordinate wumpusStart = new Coordinate(columnsX, rowsY);
            specialCaves.add(playerStart);
            specialCaves.add(wumpusStart);
            
            for (int i=0; i<numPits; i++) {
            	int randomNumCol1 = ThreadLocalRandom.current().nextInt(0, this.getNumberOfColumns());
                int randomNumRow1 = ThreadLocalRandom.current().nextInt(0, this.getNumberOfRows());
                Coordinate coordinate = new Coordinate (randomNumCol1, randomNumRow1);
                if (specialCaves.add(coordinate)) {
                	grid[coordinate.x][coordinate.y].setHasPit(true);
                } else {
                	i--;
                }
            }
            for (int j = 0; j<numBats; j++) {
            	int randomNumCol2 = ThreadLocalRandom.current().nextInt(0, this.getNumberOfColumns());
                int randomNumRow2 = ThreadLocalRandom.current().nextInt(0, this.getNumberOfRows());
                Coordinate coordinate = new Coordinate(randomNumCol2, randomNumRow2);
                if (specialCaves.add(coordinate)) {
                	grid[coordinate.x][coordinate.y].setHasBats(true);
                } else {
                	j--;
                }
            }
        } else
            throw new RuntimeException("Total number of caverns can't add up to more than 25.");
    }
	
	public Map() {
	}

	private void addPlayerToGrid() {
		player = new Player(5);
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
	
	public void setPitInCavern(int columnX, int rowY) {
		grid[columnX][rowY].setHasPit(true);
	}
	
	public void setBatsInCavern(int columnX, int rowY) {
		grid[columnX][rowY].setHasBats(true);
	}
	
	public void removePitsAtCavern(int columnX, int rowY) {
		grid[columnX][rowY].setHasPit(false);
	}
	
	public void removeBatsAtCavern(int columnX, int rowY) {
		grid[columnX][rowY].setHasBats(false);
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
	
	public static void printMap(Map map) {
		int columns = map.getNumberOfColumns();
		int rows = map.getNumberOfRows();
		for (int j = 0; j < 2 * rows; j++) {
			if (j % 2 == 0) {
				for (int i = 0; i < columns; i++) {
					System.out.print("___");
				}
			}
			else {
				for (int k = 0; k < columns; k++) {
					System.out.print("|  ");
				}
				System.out.print("|");
			}
			System.out.println("");
		}
	}
}