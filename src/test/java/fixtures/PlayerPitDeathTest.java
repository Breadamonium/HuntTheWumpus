package test.java.fixtures;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Player;
import main.java.util.CollisionUtil;

public class PlayerPitDeathTest {
	private int playerx;
	private int playery;
	private int pitx;
	private int pity;
	private Map map;
	private Player player;

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

	public int getPitx() {
		return pitx;
	}

	public void setPitx(int pitx) {
		this.pitx = pitx;
	}

	public int getPity() {
		return pity;
	}

	public void setPity(int pity) {
		this.pity = pity;
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
		map.getCavernsGrid()[0][3] = new Cavern();
		map.setPitInCavern(pitx, pity);
		player = map.getPlayer();
		player.setColumn(playerx);
		player.setRow(playery);
	}

	public boolean doesplayerdie() {
		return CollisionUtil.checkIfPlayerIsInPit(map);
	}
}
