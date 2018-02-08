package main.java.util;

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
	
	public static boolean checkIfPlayerIsDead(Player player, Wumpus wumpus) {
		boolean areInSameColumn = checkIfCoordinatesAreSame(player.getColumn(), wumpus.getColumn());
		boolean areInSameRow = checkIfCoordinatesAreSame(player.getRow(), wumpus.getRow());
		return (areInSameColumn && areInSameRow);
	}
}
