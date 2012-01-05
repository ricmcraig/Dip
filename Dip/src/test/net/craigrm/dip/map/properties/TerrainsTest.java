package net.craigrm.dip.map.properties;

import static org.junit.Assert.*;
import net.craigrm.dip.map.properties.Terrains;

import org.junit.Test;

public class TerrainsTest {

	private static final String SEA_STRING = "S";
	private static final String INLAND_STRING ="L";
	private static final String COAST_STRING = "L";
	private static final String LAND_STRING ="L";
	
	@Test
	public void terrainsSeaHasCorrectType() {
		Terrains t = Terrains.SEA;
		assertEquals("Sea has correct type", SEA_STRING, t.getBroadType());
	}

	@Test
	public void getTerrainFromSeaString() {
		Terrains t = Terrains.getTerrain(SEA_STRING);
		assertEquals("Should be type Sea", Terrains.SEA, t);
	}

	@Test
	public void terrainsInlandHasCorrectType() {
		Terrains t = Terrains.INLAND;
		assertEquals("Inland has correct type", INLAND_STRING, t.getBroadType());
	}

	@Test
	public void terrainsCoastHasCorrectType() {
		Terrains t = Terrains.COAST;
		assertEquals("Coast has correct type", COAST_STRING, t.getBroadType());
	}

	@Test
	public void getTerrainFromLandString() {
		Terrains t = Terrains.getTerrain(LAND_STRING);
		assertEquals("Should be type INLAND", Terrains.INLAND, t);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setStringTerrainBad() {
		@SuppressWarnings("unused")
		Terrains t = Terrains.getTerrain("X");
	}

}
