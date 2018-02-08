package main.java.occupants;

import java.util.ArrayList;

import main.java.Direction;
import main.java.map.Cavern;
import main.java.map.Map;

public class Player extends Occupant {
	private ArrayList<Arrow> inventory = new ArrayList<Arrow>();
	
	public Player() {
	}
	
	public Player(int startingArrows) {
		for (int j = 0; j<startingArrows; j++) {
			Arrow anArrow = new Arrow();
			inventory.add(anArrow);
		}
	}

	public static int calculateXEnd(Map m, Direction d) {
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

	public static int calculateYEnd(Map m, Direction d) {
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
	
	public boolean pickUpArrows(Map map) {
		int xLocation = map.getPlayer().getColumn();
		int yLocation = map.getPlayer().getRow();
		Cavern currentCave = map.getCavernsGrid()[xLocation][yLocation];
		int arrowsToRefill = currentCave.countArrows();
		if (arrowsToRefill>0) {
			currentCave.removeArrowsHere();
			for (int i = 0; i<arrowsToRefill; i++) {
				Arrow replenished = new Arrow();
				this.inventory.add(replenished);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getNumberOfArrows() {
		return inventory.size();
	}
}