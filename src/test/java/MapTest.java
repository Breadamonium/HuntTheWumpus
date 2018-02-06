package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.Map;

public class MapTest {
	private Map map;
	private static final int NUMBER_OF_ROWS = 4;
	private static final int NUMBER_OF_COLUMNS = 5;
	
	@Before
	public void setup() {
		map = new Map(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);		
	}
	
	@Test
	public void whenMapIsCreated_columnsAndRowsGeneratedSuccessfully() {
		assertEquals(NUMBER_OF_COLUMNS, map.getNumberOfColumns());
		assertEquals(NUMBER_OF_ROWS, map.getNumberOfRows());
	}
	
	@Test
	public void when4x5mapIsCreated_contains20caverns() {
		assertEquals(20, map.getNumberOfCaverns());
	}

	@Test(expected = RuntimeException.class)
	public void when6x5mapIsCreated_runtimeExceptionIsThrown() {
		map = new Map(6, 5);
	}
}
