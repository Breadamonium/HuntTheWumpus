package test.java.fixtures;

import main.java.Direction;
import main.java.map.Map;
import main.java.occupants.Player;

public class PlayerMovementTest {
	private int xstart;
	private int ystart;
	private String move;
	private Map map = new Map(5, 5);
	
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
	
	public void execute() {
		Player player = map.getPlayer();
		player.setColumn(xstart);
		player.setRow(ystart);
		map.getCavernsGrid()[0][0].removeOccupant(player);
		map.getCavernsGrid()[ystart][xstart].addOccupant(player);
		player.move(Direction.getDirectionFromLetter(move.substring(0, 1)), map);
	}
	
	public int xresult(){
		return map.getPlayer().getColumn();
	}

	public int yresult(){
		return map.getPlayer().getRow();
	}
}
