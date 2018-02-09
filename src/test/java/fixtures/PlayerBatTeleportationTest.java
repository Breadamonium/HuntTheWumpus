package test.java.fixtures;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.util.CollisionUtil;

public class PlayerBatTeleportationTest {
	private int playerx;
	private int playery;
	private int batx;
	private int baty;
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

	public int getBatx() {
		return batx;
	}

	public void setBatx(int batx) {
		this.batx = batx;
	}

	public int getBaty() {
		return baty;
	}

	public void setBaty(int baty) {
		this.baty = baty;
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
		map.setBatsInCavern(batx, baty);
		player = map.getPlayer();
		player.setColumn(playerx);
		player.setRow(playery);
	}
	
	public boolean doesplayergetmovedrandomly() {
		return CollisionUtil.checkIfPlayerIsOnBats(map);
	}
}
