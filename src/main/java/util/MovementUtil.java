package main.java.util;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Occupant;

public class MovementUtil {
	public static void moveNorth(Map map, Occupant occupant) {
		if (0 < occupant.getRow()) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setRow(occupant.getRow() - 1);
			addPlayerToNewCavern(map, occupant);
		}
		else
			System.out.println("Can't move north from here.");
	}
	
	public static void moveSouth(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > (occupant.getRow() + 1)) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setRow(occupant.getRow() + 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move south from here.");
	}
	
	public static void moveEast(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > (occupant.getColumn() + 1)) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setColumn(occupant.getColumn() + 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move east from here.");
	}
	
	public static void moveWest(Map map, Occupant occupant) {
		if (0 < (occupant.getColumn())) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setColumn(occupant.getColumn() - 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move west from here.");
	}
	
	private static void removePlayerFromOldCavern(Map map, Occupant occupant) {
		Cavern[][] cavernGrid = map.getCavernsGrid();
		Cavern cavernToBeVacated = cavernGrid[occupant.getColumn()][occupant.getRow()];
		cavernToBeVacated.removeOccupant(occupant);
	}

	private static void addPlayerToNewCavern(Map map, Occupant occupant) {
		Cavern cavernToBeOccupied = map.getCavernsGrid()[occupant.getColumn()][occupant.getRow()];
		cavernToBeOccupied.addOccupant(occupant);
	}

	public static void rest(Map map, Occupant occupant) {
	}
}
