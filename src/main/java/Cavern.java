package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cavern {
	private List<Occupant> occupants = new ArrayList<Occupant>();
	
	public void addOccupant(Occupant occupant) {
		occupants.add(occupant);
	}

	public void removeOccupant(Occupant occupant) {
		occupants.remove(occupant);
	}
	
	public List<Occupant> getOccupants() {
		return occupants;
	}
}
