package main.java;

import java.awt.Color;
import java.util.Scanner;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;
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
			
			isPlayerDead = NotificationUtil.checkIfPlayerIsDead(player, wumpus);
			if (isPlayerDead)
				break;
			
			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				System.out.println("** I can smell the Wumpus. **\n");
				if (!gavePlayerAnExtraTurn) {
					gavePlayerAnExtraTurn = true;
					continue;
				}
			}
			
			moveWumpus();
			
			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				System.out.println("** I can smell the Wumpus. **\n");
				gavePlayerAnExtraTurn = false;
				continue;
			}

			isPlayerDead = NotificationUtil.checkIfPlayerIsDead(player, wumpus);
		}
		scan.close();
		
		System.out.println("\n=========== Game Over ============");
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
}
