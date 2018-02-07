package main.java.util;

import main.java.Cavern;
import main.java.Map;
import main.java.Occupant;

public class MovementUtil {
	public static void moveNorth(Map map, Occupant occupant) {
		if (0 < occupant.getYcoordinate()) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setYcoordinate(occupant.getYcoordinate() - 1);
			addPlayerToNewCavern(map, occupant);
		}
		else
			System.out.println("Can't move north from here.");
	}
	
	public static void moveSouth(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > (occupant.getYcoordinate() + 1)) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setYcoordinate(occupant.getYcoordinate() + 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move south from here.");
	}
	
	public static void moveEast(Map map, Occupant occupant) {
		if (map.getNumberOfRows() > (occupant.getXcoordinate() + 1)) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setXcoordinate(occupant.getXcoordinate() + 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move east from here.");
	}
	
	public static void moveWest(Map map, Occupant occupant) {
		if (0 < (occupant.getXcoordinate())) {
			removePlayerFromOldCavern(map, occupant);
			occupant.setXcoordinate(occupant.getXcoordinate() - 1);
			addPlayerToNewCavern(map, occupant);
		} else
			System.out.println("Can't move west from here.");
	}
	
	private static void removePlayerFromOldCavern(Map map, Occupant occupant) {
		Cavern[][] cavernGrid = map.getCavernsGrid();
		Cavern cavernToBeVacated = cavernGrid[occupant.getYcoordinate()][occupant.getXcoordinate()];
		cavernToBeVacated.removeOccupant(occupant);
	}

	private static void addPlayerToNewCavern(Map map, Occupant occupant) {
		Cavern cavernToBeOccupied = map.getCavernsGrid()[occupant.getYcoordinate()][occupant.getXcoordinate()];
		cavernToBeOccupied.addOccupant(occupant);
	}

	public static void rest(Map map, Occupant occupant) {
	}
}
