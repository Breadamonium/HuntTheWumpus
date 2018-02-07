package test.java.fixtures;

import main.java.Cavern;
import main.java.Direction;
import main.java.Map;
import main.java.Occupant;
import main.java.Player;

public class PlayerMovementTest {
	private int xstart, ystart;
	private String move;
	private Map map = new Map(5, 5);
	private Player player = map.getPlayer();
	private Cavern cavernToBeOccupied;
	private Cavern cavernAt0x0y = map.getCavernsGrid()[0][0];
	
	public int getXstart() {
		return xstart;
	}

	public void setXstart(int xstart) {
		this.xstart = xstart;
	}

	public int getYstart() {
		return ystart;
	}

	public void setYstart(int ystart) {
		this.ystart = ystart;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}
	
	public int xresult(){
		player.setXcoordinate(xstart);
		player.setYcoordinate(ystart);
		movePlayerToCavernXY(xstart, ystart);
		player.move(Direction.valueOf(move), map);
		return player.getXcoordinate();
	}

	public int yresult(){
		player.setXcoordinate(xstart);
		player.setYcoordinate(ystart);
		movePlayerToCavernXY(xstart, ystart);
		player.move(Direction.valueOf(move), map);
		return player.getYcoordinate();
	}
	
	private void movePlayerToCavernXY(int x, int y) {
		cavernToBeOccupied = map.getCavernsGrid()[y][x];
		resetCavernToBeOccupied();
		cavernAt0x0y.removeOccupant(player);
		player.setXcoordinate(x);
		player.setYcoordinate(y);
		cavernToBeOccupied.addOccupant(player);
	}

	private void resetCavernToBeOccupied() {
		for (Occupant occupant : cavernToBeOccupied.getOccupants())
			cavernToBeOccupied.removeOccupant(occupant);
	}
}
