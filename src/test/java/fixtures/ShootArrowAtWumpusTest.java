package test.java.fixtures;

import java.util.ArrayList;
import java.util.List;

import main.java.Direction;
import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;

public class ShootArrowAtWumpusTest {
	private int xstart;
	private int ystart;
	private String shootdirection;
	private String wumpuslocation;
	private Map map;
	private Player player;
	private Wumpus wumpus;

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

	public Wumpus getWumpus() {
		return wumpus;
	}

	public void setWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}

	public String getWumpuslocation() {
		return wumpuslocation;
	}

	public void setWumpuslocation(String wumpuslocation) {
		this.wumpuslocation = wumpuslocation;
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

	public String doesWumpusdie(){
		map = new Map(5, 5);
		player = map.getPlayer();
		player.setColumn(xstart);
		player.setRow(ystart);
		wumpus = map.getWumpus();
		
		String wumpusLocations = wumpuslocation.replace("(", "");
		wumpusLocations = wumpusLocations.replace(")", "|");
		wumpusLocations = wumpusLocations.replace(",", "|");

		List<String> wumpusColumnsX = new ArrayList<String>();
		List<String> wumpusRowsY = new ArrayList<String>();

		int i = 2;
		for (String s : wumpusLocations.split("|")) {
			if (s.equals("|")) {
			} else {
				if (i % 2 == 0)
					wumpusColumnsX.add(s);
				else
					wumpusRowsY.add(s);
				i++;
			}
		}
		
		String response = "";
		for (int j = 0; j < wumpusColumnsX.size(); j++) {
			wumpus.setColumn(Integer.parseInt((wumpusColumnsX.get(j))));
			wumpus.setRow(Integer.parseInt((wumpusRowsY.get(j))));
			response += player.shootArrow(map, Direction.getDirectionFromLetter("S")) + ",";
		}
		response  = response.substring(0, response.length() - 1);
		return response;
	}
}
