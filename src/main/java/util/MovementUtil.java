package main.java.util;

import main.java.Cavern;
import main.java.Map;
import main.java.Player;

public class MovementUtil {
	public static void moveNorth(Map map, Player player) {
		if (0 < player.getYcoordinate()) {
			removePlayerFromOldCavern(map, player);
			player.setYcoordinate(player.getYcoordinate() - 1);
			addPlayerToNewCavern(map, player);
		}
		else
			System.out.println("Can't move north from here.");
	}
	
	public static void moveSouth(Map map, Player player) {
		if (map.getNumberOfRows() > (player.getYcoordinate() + 1)) {
			removePlayerFromOldCavern(map, player);
			player.setYcoordinate(player.getYcoordinate() + 1);
			addPlayerToNewCavern(map, player);
		} else
			System.out.println("Can't move south from here.");
	}
	
	private static void removePlayerFromOldCavern(Map map, Player player) {
		Cavern[][] cavernGrid = map.getCavernsGrid();
		Cavern cavernToBeVacated = cavernGrid[player.getYcoordinate()][player.getXcoordinate()];
		cavernToBeVacated.removeOccupant(player);
	}

	private static void addPlayerToNewCavern(Map map, Player player) {
		Cavern cavernToBeOccupied = map.getCavernsGrid()[player.getYcoordinate()][player.getXcoordinate()];
		cavernToBeOccupied.addOccupant(player);
	}
}
