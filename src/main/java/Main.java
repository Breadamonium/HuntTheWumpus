package main.java;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;
import main.java.util.CollisionUtil;
import main.java.util.NotificationUtil;
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
		while (!isPlayerDead) {
			movePlayer();

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
			if (isPlayerDead)
				break;

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				JOptionPane.showMessageDialog(visual, "I can smell the Wumpus.", "Wumpus Nearby",
						JOptionPane.WARNING_MESSAGE);
				if (!gavePlayerAnExtraTurn) {
					gavePlayerAnExtraTurn = true;
					continue;
				}
			}
			
			if (NotificationUtil.checkIfPlayerIsOneAwayFromPit(player, map)) 
				JOptionPane.showMessageDialog(visual, "Pit Nearby.", "Pit Nearby", JOptionPane.WARNING_MESSAGE);

			moveWumpus();

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				JOptionPane.showMessageDialog(visual, "I can smell the Wumpus.", "Wumpus Nearby",
						JOptionPane.WARNING_MESSAGE);
				gavePlayerAnExtraTurn = false;
				continue;
			}

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
		}
		scan.close();

		visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLACK);
		visual.getGrid()[player.getRow()][player.getColumn()].setForeground(Color.WHITE);
		visual.getGrid()[player.getRow()][player.getColumn()].setText("DEAD");
		JOptionPane.showMessageDialog(visual, "Game Over", "Game Over", JOptionPane.WARNING_MESSAGE);
	}

	private static String queryDirectionToMovePlayer() {
		System.out.println("Move Player North(N), South(S), East(E), West(W), or Rest(R)?");
		return scan.nextLine();
	}

	private static void movePlayer() {
		int columnBeforeMove = player.getColumn();
		int rowBeforeMove = player.getRow();
		
		String direction = queryDirectionToMovePlayer();
		boolean wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
		if (!wasOccupantMovementSuccessful) {
			while (!wasOccupantMovementSuccessful) {
				System.out.println("Move unsuccessful. Please try again.");
				direction = queryDirectionToMovePlayer();
				wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
			}
		}
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setBackground(null);
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setText("");
		visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
		visual.getGrid()[player.getRow()][player.getColumn()].setText("PLAYER");
	}
	
	private static void moveWumpus() {
		int columnBeforeMove = wumpus.getColumn();
		int rowBeforeMove = wumpus.getRow();
		
		boolean wasOccupantMovementSuccessful = wumpus.moveRandomly(map);
		if (!wasOccupantMovementSuccessful)
			while (!wasOccupantMovementSuccessful)
				wasOccupantMovementSuccessful = wumpus.moveRandomly(map);
		
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setBackground(null);
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setText("");
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setBackground(Color.RED);
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setText("WUMPUS");
		
	}

	public static boolean checkIfPlayerIsOneAwayFromBats(Map map) {
		ArrayList<Cavern> cavernsToCheck = new ArrayList<Cavern>();
		int playerColumn = player.getColumn();
		int playerRow = player.getRow();
		cavernsToCheck.add(map.getCavernsGrid()[playerColumn - 1][playerRow]);
		cavernsToCheck.add(map.getCavernsGrid()[playerColumn + 1][playerRow]);
		cavernsToCheck.add(map.getCavernsGrid()[playerColumn][playerRow + 1]);
		cavernsToCheck.add(map.getCavernsGrid()[playerColumn][playerRow - 1]);
		boolean alert = false;
		for (Cavern oneCavern : cavernsToCheck) {
			if (oneCavern.getHasBats()) {
				alert = true;
			}
		}
		return alert;
	}

	public static boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
}
