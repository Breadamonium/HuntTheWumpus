package main.java;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	private static boolean isWumpusDead = false;

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
		boolean givePlayerAnExtraTurn = false;
		while (!isPlayerDead || !isWumpusDead) {
			resetHazardVisuals();

			movePlayer();
			if (isWumpusDead)
				break;

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
			if (isPlayerDead)
				break;

			if (CollisionUtil.checkIfPlayerIsOnBats(map)) {
				visual.getGrid()[0][4].setBackground(Color.GREEN);
				visual.getGrid()[0][4].setForeground(Color.WHITE);
				visual.getGrid()[0][4].setText("BATS");
				JOptionPane.showMessageDialog(visual, "Encountered bats. Teloporting...", "Encountered Bats",
						JOptionPane.WARNING_MESSAGE);
				CollisionUtil.teleportPlayerToRandomLocation(map);
				visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
				visual.getGrid()[player.getRow()][player.getColumn()].setForeground(Color.WHITE);
				visual.getGrid()[player.getRow()][player.getColumn()].setText("PLAYER");
			}
			
			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
			if (isPlayerDead)
				break;

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				JOptionPane.showMessageDialog(visual, "Wumpus nearby.", "Wumpus Nearby", JOptionPane.WARNING_MESSAGE);
				if (!givePlayerAnExtraTurn) {
					givePlayerAnExtraTurn = true;
					continue;
				}
			}
			
			if (NotificationUtil.checkIfPlayerIsOneAwayFromPit(player, map))
				JOptionPane.showMessageDialog(visual, "Pit nearby.", "Pit Nearby", JOptionPane.WARNING_MESSAGE);

			if (NotificationUtil.checkIfPlayerIsOneAwayFromBats(player, map))
				JOptionPane.showMessageDialog(visual, "Bats nearby.", "Bats Nearby", JOptionPane.WARNING_MESSAGE);

			moveWumpus();

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				JOptionPane.showMessageDialog(visual, "Wumpus nearby.", "Wumpus Nearby", JOptionPane.WARNING_MESSAGE);
				givePlayerAnExtraTurn = false;
				continue;
			}

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
		}
		scan.close();

		if (isPlayerDead) {
			visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLACK);
			visual.getGrid()[player.getRow()][player.getColumn()].setForeground(Color.WHITE);
			visual.getGrid()[player.getRow()][player.getColumn()].setText("DEAD");
		}
		JOptionPane.showMessageDialog(visual, "Game Over", "Game Over", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}

	private static void resetHazardVisuals() {
		visual.getGrid()[1][3].setBackground(Color.YELLOW);
		visual.getGrid()[1][3].setForeground(Color.BLACK);
		visual.getGrid()[1][3].setText("PIT");
		visual.getGrid()[2][1].setBackground(Color.YELLOW);
		visual.getGrid()[2][1].setForeground(Color.BLACK);
		visual.getGrid()[2][1].setText("PIT");
		visual.getGrid()[0][4].setBackground(Color.GREEN);
		visual.getGrid()[0][4].setForeground(Color.WHITE);
		visual.getGrid()[0][4].setText("BATS");
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setBackground(Color.RED);
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setForeground(Color.WHITE);
		visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setText("WUMPUS");
	}

	private static String queryDirectionToMovePlayer() {
		System.out.println("Move Player North(N), South(S), East(E), West(W), Rest (R), or Shoot Arrow(A)?");
		return scan.nextLine();
	}

	private static void movePlayer() {
		int columnBeforeMove = player.getColumn();
		int rowBeforeMove = player.getRow();

		String direction = queryDirectionToMovePlayer();
		if (!direction.equals("A")) {
			boolean wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
			if (!wasOccupantMovementSuccessful) {
				while (!wasOccupantMovementSuccessful) {
					JOptionPane.showMessageDialog(visual, "Invalid move.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
					direction = queryDirectionToMovePlayer();
					wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
				}
			}
		} else {
			System.out.println("Shoot North(N), South(S), East(E) or West(W)?");
			direction = scan.nextLine();
			Direction convertedDirection = Direction.getDirectionFromLetter(direction);
			boolean arrowShot = player.shootArrow(map, convertedDirection);
			if (!arrowShot) {
				JOptionPane.showMessageDialog(visual, "No arrows to shoot.", "No Arrows", JOptionPane.WARNING_MESSAGE);
			} else {
				if (player.checkIsInStraightPath(player.getColumn(), player.getRow(), map.getWumpus().getColumn(), map.getWumpus().getRow(), convertedDirection)) {
					JOptionPane.showMessageDialog(visual, "You have shot the Wumpus! You win!", "Winner",
							JOptionPane.WARNING_MESSAGE);
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setBackground(Color.BLACK);
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setForeground(Color.WHITE);
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setText("DEAD");
					isWumpusDead = true;
				} else {
					JOptionPane.showMessageDialog(visual, "Your arrow missed the Wumpus. " + player.getNumberOfArrows() + " left.", "Miss",
							JOptionPane.WARNING_MESSAGE);
				}
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