
package net.craigrm.dip.map;

import static org.junit.Assert.*;

import net.craigrm.dip.map.ProvinceIdentifier;

import org.junit.Test;

public class IdentifierTest {

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedNullString() {
		new ProvinceIdentifier(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedEmptyString() {
		new ProvinceIdentifier("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedBadString() {
		new ProvinceIdentifier("X");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedBadCoastalString() {
		new ProvinceIdentifier("ABC(NX)");
	}

	@Test
	public void constructorPassedGoodString() {
		new ProvinceIdentifier("ABC");
	}

	@Test
	public void constructorPassedGoodLowerCaseString() {
		new ProvinceIdentifier("abc");
	}

	@Test
	public void constructorPassedGoodLowerCaseCoastalString() {
		new ProvinceIdentifier("abc(nc)");
	}

	@Test
	public void getIDSameCase() {
		final String ID_STRING = "ABC";
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		assertEquals("Internal ID same as that passed to constructor", ID_STRING, i.getID());
	}

	@Test
	public void getIDDifferentCase() {
		final String ID_STRING = "ABC";
		ProvinceIdentifier i = new ProvinceIdentifier("abc");
		assertEquals("Internal ID same as that passed to constructor, ignoring case", ID_STRING, i.getID());
	}

	@Test
	public void doesNotEqualNull() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		assertFalse("Non null Identifier is not equal to null", i.equals(null));
	}

	@Test
	public void doesNotEqualNonIdentifier() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		String s = "ABC";
		assertFalse("Identifier is not equal to non Identifier", i.equals(s));
	}

	@Test
	public void doesNotEqualDifferentIdentifier() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		ProvinceIdentifier i2 = new ProvinceIdentifier("XYZ");
		assertFalse("Identifier is not equal to different Identifier", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierValue() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		ProvinceIdentifier i2 = new ProvinceIdentifier("ABC");
		assertTrue("Identifier is equal to different Identifier with same value", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierValueIgnoringCase() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		ProvinceIdentifier i2 = new ProvinceIdentifier("abc");
		assertTrue("Identifier is equal to different Identifier with same value", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierObject() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		ProvinceIdentifier i2 = i;
		assertTrue("Identifier is equal to same Identifier", i.equals(i2));
	}

	@Test
	public void equalsReflexive() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		assertTrue("equals method is reflexive", i.equals(i));
	}

	@Test
	public void equalsSymmetric() {
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		ProvinceIdentifier e = new ProvinceIdentifier("ABC");
		ProvinceIdentifier n = new ProvinceIdentifier("XYZ");
		assertTrue("equals method is symmetric", i.equals(e) && e.equals(i));
		assertTrue("equals method is symmetric", !i.equals(n) && !n.equals(i));
	}

	@Test
	public void equalsTransitive() {
		ProvinceIdentifier e1 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier e2 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier e3 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier n = new ProvinceIdentifier("XYZ");
		assertTrue("equals method is transitive", e1.equals(e2) && e2.equals(e3) && e1.equals(e3));
		assertTrue("equals method is transitive", e1.equals(e2) && !e2.equals(n) && !e1.equals(n));
	}

	@Test
	public void hashCodeSameForEqualIdentifiers() {
		ProvinceIdentifier e1 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier e2 = new ProvinceIdentifier("ABC");
		assertTrue("Identifiers are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void hashCodeSameForEqualIdentifiersIgnoringCase() {
		ProvinceIdentifier e1 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier e2 = new ProvinceIdentifier("abc");
		assertTrue("Identifiers are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void compareTo() {
		ProvinceIdentifier a1 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier a2 = new ProvinceIdentifier("ABC");
		ProvinceIdentifier x = new ProvinceIdentifier("XYZ");
		assertTrue("ABC compareTo ABC is equal",a1.compareTo(a2) == 0);
		assertTrue("ABC compareTo XYZ is less", a1.compareTo(x) < 0);
		assertTrue("XYZ compareTo ABC is qreater", x.compareTo(a1) > 0);
	}

	@Test
	public void toStringSameCase() {
		final String ID_STRING = "ABC";
		ProvinceIdentifier i = new ProvinceIdentifier("ABC");
		assertEquals("toString is internal ID", ID_STRING, i.toString());
	}

	@Test
	public void toStringDifferentCase() {
		final String ID_STRING = "ABC";
		ProvinceIdentifier i = new ProvinceIdentifier("abc");
		assertEquals("toString is internal ID, ignoring case", ID_STRING, i.toString());
	}

}
