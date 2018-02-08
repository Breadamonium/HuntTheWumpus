package main.java.util;

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
	
}
