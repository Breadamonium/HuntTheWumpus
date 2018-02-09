package test.java.fixtures;

import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Arrow;
import main.java.occupants.Player;

public class PickupArrowTest {
	private int playerx;
	private int playery;
	private int arrowx;
	private int arrowy;
	private Map map;
	private Player player;
	private Arrow arrow;

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

	public int getArrowx() {
		return arrowx;
	}

	public void setArrowx(int arrowx) {
		this.arrowx = arrowx;
	}

	public int getArrowy() {
		return arrowy;
	}

	public void setArrowy(int arrowy) {
		this.arrowy = arrowy;
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

	public Arrow getArrow() {
		return arrow;
	}

	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
	}

	public void execute() {
		map = new Map(5, 5);
		map.getCavernsGrid()[3][1].setHasPit(false);
		map.getCavernsGrid()[1][2].setHasPit(false);
		map.getCavernsGrid()[4][0].setHasBats(false);
		map.getCavernsGrid()[0][3] = new Cavern();
		
		player = map.getPlayer();
		player.setColumn(playerx);
		player.setRow(playery);
		
		arrow = new Arrow();
		arrow.setColumn(arrowx);
		arrow.setRow(arrowy);
		
		map.getCavernsGrid()[arrowx][arrowy].addOccupant(arrow);
	}
	
	public boolean doesplayerpickuparrow() {
		return player.pickUpArrows(map);
	}
}
