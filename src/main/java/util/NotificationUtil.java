package main.java.util;

import java.util.ArrayList;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class NotificationUtil {
	public static boolean checkIfPlayerIsOneAwayFromWumpus(Player player, Wumpus wumpus) {
		int playerColumn = player.getColumn();
		int playerRow = player.getRow();
        int wumpusColumn = wumpus.getColumn();
        int wumpusRow = wumpus.getRow();
		
		boolean areInSameColumn = checkIfCoordinatesAreSame(playerColumn, wumpusColumn);
		boolean areInSameRow = checkIfCoordinatesAreSame(playerRow, wumpusRow);
		
		boolean wumpusIsNorth = areInSameColumn && checkIfCoordinatesAreSame(playerRow - 1, wumpusRow);
		boolean wumpusIsSouth = areInSameColumn && checkIfCoordinatesAreSame(playerRow + 1, wumpusRow);
		boolean wumpusIsEast = areInSameRow && checkIfCoordinatesAreSame(playerColumn + 1, wumpusColumn);
        boolean wumpusIsWest = areInSameRow && checkIfCoordinatesAreSame(playerColumn - 1, wumpusColumn);
        return (wumpusIsNorth || wumpusIsEast || wumpusIsSouth || wumpusIsWest);
	}

	public static boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
	
	public static boolean checkIfPlayerIsOneAwayFromPit(Player player, Map map) {
		ArrayList<Cavern> cavernsToCheck = new ArrayList<Cavern>();
		int playerColumn = player.getColumn();
		int playerRow = player.getRow();
		if (player.getColumn() > 0)
			cavernsToCheck.add(map.getCavernsGrid()[playerColumn - 1][playerRow]);
		if ((player.getColumn() + 1) < map.getNumberOfColumns())
			cavernsToCheck.add(map.getCavernsGrid()[playerColumn + 1][playerRow]);
		if ((player.getRow() + 1) < map.getNumberOfRows())
			cavernsToCheck.add(map.getCavernsGrid()[playerColumn][playerRow + 1]);
		if (player.getRow() > 0)
			cavernsToCheck.add(map.getCavernsGrid()[playerColumn][playerRow - 1]);
		for (Cavern oneCavern : cavernsToCheck) 
			if (oneCavern.getHasPit()) 
				return true;
		return false;
	}
}
