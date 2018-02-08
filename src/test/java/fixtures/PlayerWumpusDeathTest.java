package test.java.fixtures;

import main.java.occupants.Player;
import main.java.occupants.Wumpus;
import main.java.util.NotificationUtil;

public class PlayerWumpusDeathTest {
	private int playerx;
	private int playery;
	private int wumpusx;
	private int wumpusy;
	private Player player;
	private Wumpus wumpus;

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
		player = new Player();
		player.setColumn(playerx);
		player.setRow(playery);
		wumpus = new Wumpus();
		wumpus.setColumn(wumpusx);
		wumpus.setRow(wumpusy);
	}
	
	public boolean doesplayerdie() {
		return NotificationUtil.checkIfPlayerIsDead(player, wumpus);
	}
}
