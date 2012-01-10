package net.craigrm.dip.map.properties;

import static org.junit.Assert.*;
import net.craigrm.dip.map.properties.Supply;

import org.junit.Test;

public class SupplyTest {

	private static final String NONE_STRING = "";
	private static final int NONE_VALUE = 0;
	private static final String ONE_STRING = "SC";
	private static final int ONE_VALUE = 1;
	
	@Test
	public void supplyNoneHasCorrectID() {
		Supply s = Supply.NONE;
		assertEquals("NONE has correct ID", NONE_STRING, s.getSupplyID());
	}

	@Test
	public void getSupplyFromNoneString() {
		Supply s = Supply.getSupply(NONE_STRING);
		assertEquals("NONE has correct value", NONE_VALUE, s.getSupplyValue());
	}

	@Test
	public void supplyNoneHasCorrectValue() {
		Supply s = Supply.NONE;
		assertEquals("NONE has correct value", NONE_VALUE, s.getSupplyValue());
	}

	@Test
	public void supplyOneHasCorrectID() {
		Supply s = Supply.ONE;
		assertEquals("One has correct ID", ONE_STRING, s.getSupplyID());
	}

	@Test
	public void getSupplyFromOneString() {
		Supply s = Supply.getSupply(ONE_STRING);
		assertEquals("One has correct value", ONE_VALUE, s.getSupplyValue());
	}

	@Test
	public void supplyOneHasCorrectValue() {
		Supply s = Supply.ONE;
		assertEquals("One has correct value", ONE_VALUE, s.getSupplyValue());
	}

	@Test(expected=IllegalArgumentException.class)
	public void getSupplyFromBadString() {
		@SuppressWarnings("unused")
		Supply s = Supply.getSupply("X");
	}

}
