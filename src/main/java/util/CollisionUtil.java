package main.java.util;

import java.util.concurrent.ThreadLocalRandom;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class CollisionUtil {
	public static boolean checkIfPlayerIsDead(Map map) {
		Player player = map.getPlayer();
		Wumpus wumpus = map.getWumpus();
		
		boolean areInSameColumn = checkIfCoordinatesAreSame(player.getColumn(), wumpus.getColumn());
		boolean areInSameRow = checkIfCoordinatesAreSame(player.getRow(), wumpus.getRow());
		boolean playerEnteredPit = checkIfPlayerIsInPit(map);
		return ((areInSameColumn && areInSameRow) || playerEnteredPit);
	}
	
	public static boolean checkIfCoordinatesAreSame(int aCoordinate, int bCoordinate) {
		return (aCoordinate == bCoordinate);
	}
	
	public static boolean checkIfPlayerIsInPit(Map map) {
		int playerX = map.getPlayer().getColumn();
		int playerY = map.getPlayer().getRow();
		return map.getCavernsGrid()[playerX][playerY].getHasPit();
	}
	
	public static boolean checkIfPlayerIsOnBats(Map map) {
		int playerX = map.getPlayer().getColumn();
		int playerY = map.getPlayer().getRow();
		return map.getCavernsGrid()[playerX][playerY].getHasBats();
	}
	
	public static void teleportPlayerToRandomLocation(Map map) {
		int randomNumCol = ThreadLocalRandom.current().nextInt(0, map.getNumberOfColumns());
		int randomNumRow = ThreadLocalRandom.current().nextInt(0, map.getNumberOfRows());
		map.getPlayer().teleport(map, randomNumCol, randomNumRow);
	}
	
}
