package test.java.fixtures;

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
	
	public String countRoom(){
		try {
			return String.valueOf(new Map(xdimension, ydimension).getNumberOfCaverns());
		} catch (NegativeArraySizeException ex) {
			return "Columns or rows can't have a negative value.";
		} catch (ArrayIndexOutOfBoundsException ex) {
			return "Columns or rows must have a minimum value of 1.";
		} catch (RuntimeException ex) {
			return "Map too big. 25 max.";
		}
	}
}
