package main.java.fixtures;

import main.java.Map;

public class MapCreationTest {
	private int xdimension, ydimension;

	public int getXdimension() {
		return xdimension;
	}

	public void setXdimension(int xdimension) {
		this.xdimension = xdimension;
	}

	public int getYdimension() {
		return ydimension;
	}

	public void setYdimension(int ydimension) {
		this.ydimension = ydimension;
	}
	
	public int countRoom(){
		return new Map(xdimension, ydimension).getNumberOfCaverns();
	}
}
