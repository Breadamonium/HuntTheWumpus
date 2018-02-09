package test.java.fixtures;

import main.java.map.Map;
import main.java.occupants.Player;
import main.java.util.NotificationUtil;

public class PitNotificationTest {
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
		map.setPitInCavern(pitx, pity);
		player = map.getPlayer();
		player.setColumn(playerx);
		player.setRow(playery);
		map.getCavernsGrid()[3][1].setHasPit(false);
		map.getCavernsGrid()[1][2].setHasPit(false);
	}

	public boolean shownotification() {
		return NotificationUtil.checkIfPlayerIsOneAwayFromPit(player, map);
	}
}
