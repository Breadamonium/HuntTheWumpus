package main.java;

import java.awt.Color;
import java.util.Scanner;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;
import main.java.visuals.MapVisual;

public class Main {
	private static MapVisual visual;
	private static Map map;
	private static Player player;
	private static Wumpus wumpus; 
	private static Scanner scan;
	
	public static void main(String[] args) {
		
		map = new Map(5, 4);
		player = map.getPlayer();
		wumpus = map.getWumpus();
		
		visual = new MapVisual(map.getNumberOfColumns(), map.getNumberOfRows());
		visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setBackground(Color.RED);
		visual.setVisible(true);

		scan = new Scanner(System.in);

		boolean isPlayerDead = false;
		boolean gavePlayerAnExtraTurn = false;
		do {
			System.out.printf("Player Before: (%d,%d)\n", player.getColumn(), player.getRow());
			movePlayer();
			System.out.printf("Player After: (%d,%d)\n", player.getColumn(), player.getRow());
			
			if (checkIfPlayerIsOneAwayFromWumpus()) {
				System.out.println("** I can smell the Wumpus. **\n");
				if (!gavePlayerAnExtraTurn) {
					gavePlayerAnExtraTurn = true;
					continue;
				}
			}
			
			System.out.printf("Wumpus Before: (%d,%d)\n", wumpus.getColumn(), wumpus.getRow());
			moveWumpus();
			System.out.printf("Wumpus After: (%d,%d)\n\n", wumpus.getColumn(), wumpus.getRow());
			
			if (checkIfPlayerIsOneAwayFromWumpus()) {
				System.out.println("** I can smell the Wumpus. **\n");
				gavePlayerAnExtraTurn = false;
				continue;
			}

			isPlayerDead = checkIfPlayerIsDead();
		} while (!isPlayerDead);
		scan.close();
		
		System.out.println("\n=========== Game Over ============");
	}

	private static String queryDirectionToMovePlayer() {
		System.out.println("\tMove Player North(N), South(S), East(E), West(W), or Rest(R)?");
		return scan.nextLine();
	}

	private static void movePlayer() {
		int columnBeforeMove = player.getColumn();
		int rowBeforeMove = player.getRow();
		
		String direction = queryDirectionToMovePlayer();
		boolean wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
		if (!wasOccupantMovementSuccessful) {
			while (!wasOccupantMovementSuccessful) {
				System.out.println("\tMove unsuccessful. Please try a different direction.");
				direction = queryDirectionToMovePlayer();
				wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
			}
		}
		visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setBackground(null);
	}
	
	private static void moveWumpus() {
		boolean wasOccupantMovementSuccessful = wumpus.moveRandomly(map);
		if (!wasOccupantMovementSuccessful)
			while (!wasOccupantMovementSuccessful)
				wasOccupantMovementSuccessful = wumpus.moveRandomly(map);
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
		boolean wumpusIsEast = areInSameRow && checkIfCoordinatesAreSame(playerColumn + 1, wumpusColumn);
        boolean wumpusIsWest = areInSameRow && checkIfCoordinatesAreSame(playerColumn - 1, wumpusColumn);
        return (wumpusIsNorth || wumpusIsEast || wumpusIsSouth || wumpusIsWest);
	}
	
	public static boolean checkIsPlayerOneAway(int xCol, int yRow) {
		int playerColumn = player.getColumn();
		int playerRow = player.getRow();
		
		boolean areInSameColumn = checkIfCoordinatesAreSame(playerColumn, xCol);
		boolean areInSameRow = checkIfCoordinatesAreSame(playerRow, yRow);
		
		boolean isNorth = areInSameColumn && checkIfCoordinatesAreSame(playerRow - 1, yRow);
		boolean isSouth = areInSameColumn && checkIfCoordinatesAreSame(playerRow + 1, yRow);
		boolean isEast = areInSameRow && checkIfCoordinatesAreSame(playerColumn + 1, xCol);
        boolean isWest = areInSameRow && checkIfCoordinatesAreSame(playerColumn - 1, xCol);
        return (isNorth || isEast || isSouth || isWest);
	}

	public static boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
}
