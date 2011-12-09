package map;

import static org.junit.Assert.*;

import map.properties.Powers;
import map.properties.Supply;
import map.properties.Terrains;

import org.junit.Test;

public class ProvinceTest {

	private static final String SUPPLY_NONE_STRING = "";
	private static final String SUPPLY_ONE_STRING = "SC";
	private static final String POWER_AUSTRIA_HUNGARY_STRING = "A";
	private static final String POWER_TURKEY_STRING = "T";
	private static final String POWER_NONE_STRING = "";
	private static final String TERRAIN_SEA_STRING = "S";
	private static final String TERRAIN_LAND_STRING = "L";
	
	// Basic getters and setters not tested.
	
	@Test
	public void setStringSupplyNone() {
		Province p = new Province();
		p.setSupply(SUPPLY_NONE_STRING);
		assertEquals("Should be supply none", Supply.NONE, p.getSupply());
	}

	@Test
	public void setStringSupplyOne() {
		Province p = new Province();
		p.setSupply(SUPPLY_ONE_STRING);
		assertEquals("Should be supply one", Supply.ONE, p.getSupply());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setStringSupplyBad() {
		Province p = new Province();
		p.setSupply("X");
	}

	@Test
	public void setStringPowerAustriaHungary() {
		Province p = new Province();
		p.setOwner(POWER_AUSTRIA_HUNGARY_STRING);
		assertEquals("Should be Owner Austria-Hungary", Powers.AUSTRIAHUNGARY, p.getOwner());
	}

	@Test
	public void setStringPowerTurkey() {
		Province p = new Province();
		p.setOwner(POWER_TURKEY_STRING);
		assertEquals("Should be Owner Turkey", Powers.TURKEY, p.getOwner());
	}

	@Test
	public void setStringPowerNone() {
		Province p = new Province();
		p.setOwner(POWER_NONE_STRING);
		assertEquals("Should be Owner none", Powers.NONE, p.getOwner());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setStringPowerBad() {
		Province p = new Province();
		p.setOwner("X");
	}

	@Test
	public void setStringTerrainSea() {
		Province p = new Province();
		p.setType(TERRAIN_SEA_STRING);
		assertEquals("Should be type Sea", Terrains.SEA, p.getType());
	}

	@Test
	public void setStringTerrainLand() {
		Province p = new Province();
		p.setType(TERRAIN_LAND_STRING);
		assertEquals("Should be type Inland", Terrains.INLAND, p.getType());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setStringTerrainBad() {
		Province p = new Province();
		p.setType("X");
	}

	@Test
	public void doesNotEqualNull() {
		Province p = new Province("ABC");
		assertFalse("Non null Province is not equal to null", p.equals(null));
	}

	@Test
	public void doesNotEqualNonProvince() {
		Province p = new Province("ABC");
		String s = "ABC";
		assertFalse("Province is not equal to non Province", p.equals(s));
	}

	@Test
	public void doesNotEqualDifferentProvince() {
		Province p = new Province("ABC");
		Province p2 = new Province("XYZ");
		assertFalse("Province is not equal to different Province", p.equals(p2));
	}

	@Test
	public void equalsSameProvinceValue() {
		Province p = new Province("ABC");
		Province p2 = new Province("ABC");
		assertTrue("Province is equal to different Province with same value", p.equals(p2));
	}

	@Test
	public void equalsSameProvinceObject() {
		Province p = new Province("ABC");
		Province p2 = p;
		assertTrue("Province is equal to same Province", p.equals(p2));
	}

	@Test
	public void equalsReflexive() {
		Province p = new Province("ABC");
		assertTrue("equals method is reflexive", p.equals(p));
	}

	@Test
	public void equalsSymmetric() {
		Province p = new Province("ABC");
		Province e = new Province("ABC");
		Province n = new Province("XYZ");
		assertTrue("equals method is symmetric", p.equals(e) && e.equals(p));
		assertTrue("equals method is symmetric", !p.equals(n) && !n.equals(p));
	}

	@Test
	public void equalsTransitive() {
		Province e1 = new Province("ABC");
		Province e2 = new Province("ABC");
		Province e3 = new Province("ABC");
		Province n = new Province("XYZ");
		assertTrue("equals method is transitive", e1.equals(e2) && e2.equals(e3) && e1.equals(e3));
		assertTrue("equals method is transitive", e1.equals(e2) && !e2.equals(n) && !e1.equals(n));
	}

	@Test
	public void hashCodeSameForEqualProvinces() {
		Province e1 = new Province("ABC");
		Province e2 = new Province("ABC");
		assertTrue("Provinces are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void compareTo() {
		Province a1 = new Province("ABC");
		Province a2 = new Province("ABC");
		Province x = new Province("XYZ");
		assertTrue("ABC compareTo ABC is equal",a1.compareTo(a2) == 0);
		assertTrue("ABC compareTo XYZ is less", a1.compareTo(x) < 0);
		assertTrue("XYZ compareTo ABC is qreater", x.compareTo(a1) > 0);
	}

}
