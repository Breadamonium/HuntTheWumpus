package test.java.fixtures;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.occupants.Wumpus;
import main.java.util.CollisionUtil;

public class PlayerWumpusDeathTest {
	private int playerx;
	private int playery;
	private int wumpusx;
	private int wumpusy;
	private Player player;
	private Wumpus wumpus;
	private Map map = new Map(5, 5);

	public int getPlayerx() {
		return playerx;
	}

	public void setPlayerx(int playerx) {
		this.playerx = playerx;
	}

	public int getPlayery() {
		return playery;
	}

	public void setPlayery(int playery) {
		this.playery = playery;
	}

	public int getWumpusx() {
		return wumpusx;
	}

	public void setWumpusx(int wumpusx) {
		this.wumpusx = wumpusx;
	}

	public int getWumpusy() {
		return wumpusy;
	}

	public void setWumpusy(int wumpusy) {
		this.wumpusy = wumpusy;
	}

	public void execute() {
		player = map.getPlayer();
		player.setColumn(playerx);
		player.setRow(playery);
		wumpus = map.getWumpus();
		wumpus.setColumn(wumpusx);
		wumpus.setRow(wumpusy);
	}
	
	public boolean doesplayerdie() {
		return CollisionUtil.checkIfPlayerIsDead(map);
	}
}
