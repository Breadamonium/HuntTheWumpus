package main.java.util;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Occupant;
import main.java.occupants.Player;

public class MovementUtil {
	public static boolean moveNorth(Map map, Occupant occupant) {
		if (0 < occupant.getRow()) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setRow(occupant.getRow() - 1);
			addPlayerToNewCavern(map, occupant);
			return true;
		}
		else
			if (occupant instanceof Player)
				System.out.println("Can't move north from here.");
		return false;
	}
	
	public static boolean moveSouth(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > (occupant.getRow() + 1)) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setRow(occupant.getRow() + 1);
			addPlayerToNewCavern(map, occupant);
			return true;
		} else
			if (occupant instanceof Player)
				System.out.println("Can't move south from here.");
		return false;
	}
	
	public static boolean moveEast(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > occupant.getColumn()) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setColumn(occupant.getColumn() + 1);
			addPlayerToNewCavern(map, occupant);
			return true;
		} else
			if (occupant instanceof Player)
				System.out.println("Can't move east from here.");
		return false;
	}
	
	public static boolean moveWest(Map map, Occupant occupant) {
		if (0 < occupant.getColumn()) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setColumn(occupant.getColumn() - 1);
			addPlayerToNewCavern(map, occupant);
			return true;
		} else
			if (occupant instanceof Player)
				System.out.println("Can't move west from here.");
		return false;
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

	public static boolean rest(Map map, Occupant occupant) {
		return true;
	}
}
