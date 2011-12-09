package map.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class TerrainsTest {

	private static final String SEA_TYPE = "S";
	private static final String INLAND_TYPE ="L";
	private static final String COAST_TYPE = "L";
	
	@Test
	public void terrainsSeaHasCOrrectType() {
		Terrains t = Terrains.SEA;
		assertEquals("Sea has correct type", SEA_TYPE, t.getBroadType());
	}

	@Test
	public void terrainsInlandHasCorrectType() {
		Terrains t = Terrains.INLAND;
		assertEquals("Inland has correct type", INLAND_TYPE, t.getBroadType());
	}

	@Test
	public void terrainsCoastHasCorrectType() {
		Terrains t = Terrains.COAST;
		assertEquals("Coast has correct type", COAST_TYPE, t.getBroadType());
	}

}
