package main.java.occupants;

import java.util.ArrayList;

import main.java.Direction;
import main.java.map.Map;

public class Player extends Occupant {
	private ArrayList<Arrow> inventory = new ArrayList<Arrow>();
	
	private int calculateXEnd(Map m, Direction d) {
		if (d.equals(Direction.EAST)) {
			return m.getNumberOfColumns() - 1;
		}
		else if (d.equals(Direction.WEST)) {
			return 0;
		}
		else {
			return m.getPlayer().getColumn();
		}
	}

	private int calculateYEnd(Map m, Direction d) {
		if (d.equals(Direction.NORTH)) {
			return 0;
		}
		else if (d.equals(Direction.SOUTH)) {
			return m.getNumberOfRows() - 1;
		}
		else {
			return m.getPlayer().getRow();
		}
	}
	public boolean shootArrow(Map map, Direction direction) {
		if (inventory.size()>0) {
			Arrow toShoot = inventory.get(0);
			int xEnd = calculateXEnd(map, direction);
			int yEnd = calculateYEnd(map, direction);
			toShoot.teleport(map, xEnd, yEnd);
			inventory.remove(0);
			return true;
		}
		else {
			return false;
		}
	}
}