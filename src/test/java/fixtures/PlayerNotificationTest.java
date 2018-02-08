package test.java.fixtures;

import main.java.Direction;
import main.java.map.Cavern;
import main.java.map.Map;
import main.java.occupants.Occupant;
import main.java.occupants.Player;

public class PlayerNotificationTest {
	private int playerx;
	private int playery;
	private String move;
	private Player player;
	private Map map;
	private Cavern cavernToBeOccupied;
	private Cavern cavernAt0x0y;

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

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}
	
	public void execute() {
		map = new Map(5, 5);
		cavernAt0x0y = map.getCavernsGrid()[0][0];
		player = new Player();
		player.setColumn(playerx);
		player.setRow(playery);
		moveOccupantToCavern(player);
	}

	public boolean shownotification() {
		if (player.move(Direction.getDirectionFromLetter(move), map))
			return false;
		return true;
	}
	
	private void moveOccupantToCavern(Occupant occupant) {
		cavernToBeOccupied = map.getCavernsGrid()[occupant.getColumn()][occupant.getRow()];
		cavernAt0x0y.removeOccupant(occupant);
		occupant.setColumn(occupant.getColumn());
		occupant.setRow(occupant.getRow());
		cavernToBeOccupied.addOccupant(occupant);
	}
}
