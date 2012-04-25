package net.craigrm.dip.orders.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdjustmentTypeTest {

	private static final String BUILD_ADJUSTMENT_SINGULAR_STRING = "Build";
	private static final String BUILD_ADJUSTMENT_PLURAL_STRING = "Builds";
	private static final String DISBAND_ADJUSTMENT_SINGULAR_STRING = "Disband";
	private static final String DISBAND_ADJUSTMENT_PLURAL_STRING = "Disbands";

	@Test
	public void getAdjustmentTypeFromBuildSingluarString() {
		assertEquals("Should return BUILD type for \"Build\" string", AdjustmentType.BUILD, AdjustmentType.getAdjustmentType(BUILD_ADJUSTMENT_SINGULAR_STRING));
	}

	@Test
	public void getAdjustmentTypeFromBuildPluralString() {
		assertEquals("Should return BUILD type for \"Builds\" string", AdjustmentType.BUILD, AdjustmentType.getAdjustmentType(BUILD_ADJUSTMENT_PLURAL_STRING));
	}

	@Test
	public void getAdjustmentTypeFromDisbandSingluarString() {
		assertEquals("Should return DISBAND type for \"Disband\" string", AdjustmentType.DISBAND, AdjustmentType.getAdjustmentType(DISBAND_ADJUSTMENT_SINGULAR_STRING));
	}

	@Test
	public void getAdjustmentTypeFromDisbandPluralString() {
		assertEquals("Should return DISBAND type for \"Disbands\" string", AdjustmentType.DISBAND, AdjustmentType.getAdjustmentType(DISBAND_ADJUSTMENT_PLURAL_STRING));
	}

	@Test
	public void getAdjustmentTypeFromBuildStringUpperCase() {
		assertEquals("Match should be case indpendent", AdjustmentType.BUILD, AdjustmentType.getAdjustmentType(BUILD_ADJUSTMENT_SINGULAR_STRING.toUpperCase()));
	}

	@Test
	public void getAdjustmentTypeFromMoveStringWhiteSpace() {
		assertEquals("Match should ignore leading and trailing whitespace", AdjustmentType.BUILD, AdjustmentType.getAdjustmentType("  " + BUILD_ADJUSTMENT_PLURAL_STRING + "\t\r\n"));
	}

	@Test(expected=AdjustmentTypeFormatException.class)
	public void getAdjustmentTypeFromBadString() {
		AdjustmentType.getAdjustmentType("X");
	}

}
