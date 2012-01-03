package map;

import static org.junit.Assert.*;

import map.properties.Powers;
import map.properties.Supply;
import map.properties.Terrains;

import org.junit.Test;

public class ProvinceTest {

	@Test
	public void doesNotEqualNull() {
		Province p = makeProvince("ABC");
		assertFalse("Non null Province is not equal to null", p.equals(null));
	}

	@Test
	public void doesNotEqualNonProvince() {
		Province p = makeProvince("ABC");
		String s = "ABC";
		assertFalse("Province is not equal to non Province", p.equals(s));
	}

	@Test
	public void doesNotEqualDifferentProvince() {
		Province p = makeProvince("ABC");
		Province p2 = makeProvince("XYZ");
		assertFalse("Province is not equal to different Province", p.equals(p2));
	}

	@Test
	public void equalsSameProvinceValue() {
		Province p = makeProvince("ABC");
		Province p2 = makeProvince("ABC");
		assertTrue("Province is equal to different Province with same value", p.equals(p2));
	}

	@Test
	public void equalsSameProvinceObject() {
		Province p = makeProvince("ABC");
		Province p2 = p;
		assertTrue("Province is equal to same Province", p.equals(p2));
	}

	@Test
	public void equalsReflexive() {
		Province p = makeProvince("ABC");
		assertTrue("equals method is reflexive", p.equals(p));
	}

	@Test
	public void equalsSymmetric() {
		Province p = makeProvince("ABC");
		Province e = makeProvince("ABC");
		Province n = makeProvince("XYZ");
		assertTrue("equals method is symmetric", p.equals(e) && e.equals(p));
		assertTrue("equals method is symmetric", !p.equals(n) && !n.equals(p));
	}

	@Test
	public void equalsTransitive() {
		Province e1 = makeProvince("ABC");
		Province e2 = makeProvince("ABC");
		Province e3 = makeProvince("ABC");
		Province n = makeProvince("XYZ");
		assertTrue("equals method is transitive", e1.equals(e2) && e2.equals(e3) && e1.equals(e3));
		assertTrue("equals method is transitive", e1.equals(e2) && !e2.equals(n) && !e1.equals(n));
	}

	@Test
	public void hashCodeSameForEqualProvinces() {
		Province e1 = makeProvince("ABC");
		Province e2 = makeProvince("ABC");
		assertTrue("Provinces are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void compareTo() {
		Province a1 = makeProvince("ABC");
		Province a2 = makeProvince("ABC");
		Province x = makeProvince("XYZ");
		assertTrue("ABC compareTo ABC is equal",a1.compareTo(a2) == 0);
		assertTrue("ABC compareTo XYZ is less", a1.compareTo(x) < 0);
		assertTrue("XYZ compareTo ABC is qreater", x.compareTo(a1) > 0);
	}

	private Province makeProvince(String id) {
		return new Province(new Identifier(id), Terrains.INLAND, Supply.NONE, Powers.NONE, id, new Aliases("()"), new Neighbours("()"));
	}
}
