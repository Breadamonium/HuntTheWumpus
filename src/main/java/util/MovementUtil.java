package main.java.util;

import main.java.Direction;
import main.java.Map;
import main.java.Player;

public class MovementUtil {
	public static void moveNorth(Map map, Player player) {
		if (0 < player.getYcoordinate()) 
			player.setYcoordinate(player.getYcoordinate() - 1);
		else
			System.out.println("Can't move north from here.");
	}
	
	public static void moveSouth(Map map, Player player) {
		if (map.getNumberOfRows() > (player.getYcoordinate() + 1))
			player.setYcoordinate(player.getYcoordinate() + 1);
		else
			System.out.println("Can't move south from here.");
	}
}
