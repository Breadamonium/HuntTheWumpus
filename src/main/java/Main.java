package main.java;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class Main {
	private static Map map;
	private static Player player;
	private static Wumpus wumpus; 
	
	public static void main(String[] args) {
		map = new Map(5, 4);
		player = map.getPlayer();
		wumpus = map.getWumpus();
		
		boolean isPlayerDead = checkIfPlayerIsDead();
		do {
			System.out.println("Moving Player... ");
			System.out.printf("\tPlayer Before: (%d,%d)\tWumpus Before: (%d,%d)\n", player.getColumn(), player.getRow(), wumpus.getColumn(), wumpus.getRow());
			player.moveRandomly(map);
			System.out.printf("\tPlayer After: (%d,%d)\tWumpus After: (%d,%d)\n", player.getColumn(), player.getRow(), wumpus.getColumn(), wumpus.getRow());
			
			if (checkIfPlayerIsOneAwayFromWumpus()) {
				System.out.println("** I can smell the Wumpus. **");
				System.out.println("\n==========================\n");
				continue;
			}
			
			System.out.println("Moving Wumpus...");
			System.out.printf("\tPlayer Before: (%d,%d)\tWumpus Before: (%d,%d)\n", player.getColumn(), player.getRow(), wumpus.getColumn(), wumpus.getRow());
			wumpus.moveRandomly(map);
			System.out.printf("\tPlayer After: (%d,%d)\tWumpus After: (%d,%d)\n", player.getColumn(), player.getRow(), wumpus.getColumn(), wumpus.getRow());

			isPlayerDead = checkIfPlayerIsDead();
			
			System.out.println("\n==========================\n");
		} while (!isPlayerDead);
		
		System.out.println("\nGame Over");
	}

	public static boolean checkIfPlayerIsDead() {
		boolean areInSameColumn = checkIfCoordinatesAreSame(player.getColumn(), wumpus.getColumn());
		boolean areInSameRow = checkIfCoordinatesAreSame(player.getRow(), wumpus.getRow());
		return (areInSameColumn && areInSameRow);
	}

	public static boolean checkIfPlayerIsOneAwayFromWumpus() {
		int playerColumn = player.getColumn();
		int playerRow = player.getRow();
        int wumpusColumn = wumpus.getColumn();
        int wumpusRow = wumpus.getRow();
		
		boolean areInSameColumn = checkIfCoordinatesAreSame(playerColumn, wumpusColumn);
		boolean areInSameRow = checkIfCoordinatesAreSame(playerRow, wumpusRow);
		
		boolean wumpusIsNorth = areInSameColumn && checkIfCoordinatesAreSame(playerRow - 1, wumpusRow);
		boolean wumpusIsSouth = areInSameColumn && checkIfCoordinatesAreSame(playerRow + 1, wumpusRow);
		boolean wumpusIsEast = areInSameRow && checkIfCoordinatesAreSame(playerColumn + 1, wumpusRow);
        boolean wumpusIsWest = areInSameRow && checkIfCoordinatesAreSame(playerColumn - 1, wumpusColumn);
        return (wumpusIsNorth || wumpusIsEast || wumpusIsSouth || wumpusIsWest);
	}

	public static boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
}
