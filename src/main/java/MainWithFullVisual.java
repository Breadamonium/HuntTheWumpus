package main.java;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;
import main.java.util.CollisionUtil;
import main.java.util.NotificationUtil;
import main.java.visuals.MapVisual;

public class MainWithFullVisual {
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
		visual.getGrid()[map.getNumberOfRows() - 1][map.getNumberOfColumns() - 1].setText("WUMPUS");
		visual.getGrid()[1][3].setBackground(Color.YELLOW);
		visual.getGrid()[1][3].setForeground(Color.BLACK);
		visual.getGrid()[1][3].setText("PIT");
		visual.getGrid()[2][1].setBackground(Color.YELLOW);
		visual.getGrid()[2][1].setForeground(Color.BLACK);
		visual.getGrid()[2][1].setText("PIT");
		visual.getGrid()[0][4].setBackground(Color.GREEN);
		visual.getGrid()[0][4].setForeground(Color.WHITE);
		visual.getGrid()[0][4].setText("BATS");
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
				showDialog("Encountered bats. Teleporting...", "Encountered Bats");
				CollisionUtil.teleportPlayerToRandomLocation(map);
				visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
				visual.getGrid()[player.getRow()][player.getColumn()].setForeground(Color.WHITE);
				visual.getGrid()[player.getRow()][player.getColumn()].setText("PLAYER");
			}

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
			if (isPlayerDead)
				break;

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				showDialog("Wumpus nearby.", "Wumpus Nearby");
				if (!givePlayerAnExtraTurn) {
					givePlayerAnExtraTurn = true;
					continue;
				}
			}

			if (NotificationUtil.checkIfPlayerIsOneAwayFromPit(player, map))
				showDialog("Pit nearby.", "Pit Nearby");

			if (NotificationUtil.checkIfPlayerIsOneAwayFromBats(player, map))
				showDialog("Bats nearby.", "Bats Nearby");

			moveWumpus();

			if (NotificationUtil.checkIfPlayerIsOneAwayFromWumpus(player, wumpus)) {
				showDialog("Wumpus nearby.", "Wumpus Nearby");
				givePlayerAnExtraTurn = false;
				continue;
			}

			isPlayerDead = CollisionUtil.checkIfPlayerIsDead(map);
			if (isPlayerDead)
				break;
		}
		scan.close();

		if (isPlayerDead) {
			visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLACK);
			visual.getGrid()[player.getRow()][player.getColumn()].setForeground(Color.WHITE);
			visual.getGrid()[player.getRow()][player.getColumn()].setText("DEAD");
		}
		showDialog("Game Over", "Game Over");
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
					showDialog("Invalid move.", "Invalid Move");
					direction = queryDirectionToMovePlayer();
					wasOccupantMovementSuccessful = player.move(Direction.getDirectionFromLetter(direction), map);
				}
			}
		} else {
			System.out.println("Shoot North(N), South(S), East(E) or West(W)?");
			direction = scan.nextLine();
			Direction convertedDirection = Direction.getDirectionFromLetter(direction);
			boolean arrowShot = player.shootArrow(map, convertedDirection);
			if (!arrowShot) 
				showDialog("No arrows to shoot.", "No Arrows");
			else {
				if (player.checkIsInStraightPath(player.getColumn(), player.getRow(), map.getWumpus().getColumn(),
						map.getWumpus().getRow(), convertedDirection)) {
					showDialog("You have shot the Wumpus! You win!", "Winner");
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setBackground(Color.BLACK);
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setForeground(Color.WHITE);
					visual.getGrid()[wumpus.getRow()][wumpus.getColumn()].setText("DEAD");
					isWumpusDead = true;
				} else {
					String message = "Your arrow missed the Wumpus." + player.getNumberOfArrows() + " left.";
					showDialog(message, "Miss");
				}
			}
		}
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setBackground(null);
		visual.getGrid()[rowBeforeMove][columnBeforeMove].setText("");
		visual.getGrid()[player.getRow()][player.getColumn()].setBackground(Color.BLUE);
		visual.getGrid()[player.getRow()][player.getColumn()].setText("PLAYER");
		int numberOfArrowsBeforePickup = player.getNumberOfArrows();
		boolean pickedUpArrow = player.pickUpArrows(map);
		if (pickedUpArrow) {
			int numberOfArrowsPickedUp = player.getNumberOfArrows() - numberOfArrowsBeforePickup;
			String message = "Picked up " + numberOfArrowsPickedUp + " arrow(s). You now have " + 
					player.getNumberOfArrows() + " arrows.";
			String title = "Picked Up Arrow(s)";
			showDialog(message, title);
		}
	}

	private static void showDialog(String message, String title) {
		final JOptionPane msg = new JOptionPane(message, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
		final JDialog dlg = msg.createDialog(visual, title);
		dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dlg.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		        super.componentShown(e);
		        final Timer t = new Timer(1500,new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dlg.setVisible(false);
					}
		        });
		        t.start();
		    }
		});
		dlg.setVisible(true);
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