package net.craigrm.dip.state.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTypeTest {

	private static final String ARMY_UNIT_TYPE_STRING = "A";
	private static final String FLEET_UNIT_TYPE_STRING = "F";

	@Test
	public void getUnitTypeFromMoveString() {
		assertEquals("", UnitType.ARMY, UnitType.getType(ARMY_UNIT_TYPE_STRING));
	}

	@Test
	public void getUnitTypeFromHoldString() {
		assertEquals("", UnitType.FLEET, UnitType.getType(FLEET_UNIT_TYPE_STRING));
	}

	@Test
	public void getUnitTypeFromMoveStringUpperCase() {
		assertEquals("", UnitType.ARMY, UnitType.getType(ARMY_UNIT_TYPE_STRING.toUpperCase()));
	}

	@Test
	public void getUnitTypeFromMoveStringWhiteSpace() {
		assertEquals("", UnitType.ARMY, UnitType.getType("  " + ARMY_UNIT_TYPE_STRING + "\t\r\n"));
	}

	@Test(expected=UnitTypeFormatException.class)
	public void getUnitTypeFromBadString() {
		UnitType.getType("X");
	}


}
