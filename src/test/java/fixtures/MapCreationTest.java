package test.java.fixtures;

import main.java.Map;

public class MapCreationTest {
	private String xdimension, ydimension;

	public String getXdimension() {
		return xdimension;
	}

	public void setXdimension(String xdimension) {
		this.xdimension = xdimension;
	}

	public String getYdimension() {
		return ydimension;
	}

	public void setYdimension(String ydimension) {
		this.ydimension = ydimension;
	}
	
	public String countRoom(){
		try {
			return String.valueOf(new Map(Integer.valueOf(xdimension), Integer.valueOf(ydimension)).getNumberOfCaverns());
		} catch (Exception ex) {
			return "Unacceptable entry.";
		}
	}
}
