package test.java.fixtures;

import main.java.Direction;
import main.java.map.Map;
import main.java.occupants.Player;

public class ShootArrowTest {
	private int xstart;
	private int ystart;
	private String shootdirection;
	private Map map;
	private Player player;

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

	public String getShootdirection() {
		return shootdirection;
	}

	public void setShootdirection(String shootdirection) {
		this.shootdirection = shootdirection;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void execute() {
		map = new Map(5, 5);
		player = map.getPlayer();
		player.setColumn(xstart);
		player.setRow(ystart);
	}

	public int xresult() {
		return player.calculateXEnd(map, Direction.getDirectionFromLetter(shootdirection));
	}

	public int yresult() {
		return player.calculateYEnd(map, Direction.getDirectionFromLetter(shootdirection));
	}
}
