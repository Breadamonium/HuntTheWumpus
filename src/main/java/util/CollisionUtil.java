package main.java.util;

import java.util.concurrent.ThreadLocalRandom;

import main.java.map.Map;

public class CollisionUtil {
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
	
	public static boolean encounterBats(Map map) {
		if (checkIfPlayerIsOnBats(map)) {
			int randomNumCol = ThreadLocalRandom.current().nextInt(0, map.getNumberOfColumns());
			int randomNumRow = ThreadLocalRandom.current().nextInt(0, map.getNumberOfRows());
			map.getPlayer().teleport(map, randomNumCol, randomNumRow);
			return true;
		}
		return false;
	}
	
}
