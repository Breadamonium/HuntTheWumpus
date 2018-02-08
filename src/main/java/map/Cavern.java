package main.java.map;

import java.util.ArrayList;
import java.util.List;

import main.java.occupants.Arrow;
import main.java.occupants.Occupant;

public class Cavern {
	private List<Occupant> occupants = new ArrayList<Occupant>();
	private boolean hasPit;
	private boolean hasBats;
	
	public void addOccupant(Occupant occupant) {
		occupants.add(occupant);
	}

	public void removeOccupant(Occupant occupant) {
		occupants.remove(occupant);
	}
	
	public List<Occupant> getOccupants() {
		return occupants;
	}

	public boolean getHasPit() {
		return hasPit;
	}

	public void setHasPit(boolean hasPit) {
		this.hasPit = hasPit;
	}
	
	public int countArrows() {
		int arrowCount = 0;
		for (Occupant something : occupants) {
			if (something instanceof Arrow) {
				arrowCount++;
			}
		}
		return arrowCount;
	}
	
	public void removeArrowsHere() {
		occupants.removeIf(i -> i instanceof Arrow);
	}

	public boolean getHasBats() {
		return hasBats;
	}

	public void setHasBats(boolean hasBats) {
		this.hasBats = hasBats;
	}
}