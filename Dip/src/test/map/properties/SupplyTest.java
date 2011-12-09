package map.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class SupplyTest {

	private static final String NONE_ID = "";
	private static final int NONE_VALUE = 0;
	private static final String ONE_ID = "SC";
	private static final int ONE_VALUE = 1;
	
	@Test
	public void supplyNoneHasCorrectID() {
		Supply s = Supply.NONE;
		assertEquals("None has corect ID", NONE_ID, s.getSupplyID());
	}

	@Test
	public void supplyNoneHasCorrectValue() {
		Supply s = Supply.NONE;
		assertEquals("None has corect value", NONE_VALUE, s.getSupplyValue());
	}

	@Test
	public void supplyOneHasCorrectID() {
		Supply s = Supply.ONE;
		assertEquals("One has corect ID", ONE_ID, s.getSupplyID());
	}

	@Test
	public void supplyOneHasCorrectValue() {
		Supply s = Supply.ONE;
		assertEquals("One has corect value", ONE_VALUE, s.getSupplyValue());
	}

}
