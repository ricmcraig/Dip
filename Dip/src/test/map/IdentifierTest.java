
package map;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdentifierTest {

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedNullString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedEmptyString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedBadString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("X");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorPassedBadCoastalString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("ABC(NX)");
	}

	@Test
	public void constructorPassedGoodString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("ABC");
	}

	@Test
	public void constructorPassedGoodLowerCaseString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("abc");
	}

	@Test
	public void constructorPassedGoodLowerCaseCoastalString() {
		@SuppressWarnings("unused")
		Identifier i = new Identifier("abc(nc)");
	}

	@Test
	public void getIDSameCase() {
		final String ID_STRING = "ABC";
		Identifier i = new Identifier("ABC");
		assertEquals("Internal ID same as that passed to constructor", ID_STRING, i.getID());
	}

	@Test
	public void getIDDifferentCase() {
		final String ID_STRING = "ABC";
		Identifier i = new Identifier("abc");
		assertEquals("Internal ID same as that passed to constructor, ignoring case", ID_STRING, i.getID());
	}

	@Test
	public void doesNotEqualNull() {
		Identifier i = new Identifier("ABC");
		assertFalse("Non null Identifier is not equal to null", i.equals(null));
	}

	@Test
	public void doesNotEqualNonIdentifier() {
		Identifier i = new Identifier("ABC");
		String s = "ABC";
		assertFalse("Identifier is not equal to non Identifier", i.equals(s));
	}

	@Test
	public void doesNotEqualDifferentIdentifier() {
		Identifier i = new Identifier("ABC");
		Identifier i2 = new Identifier("XYZ");
		assertFalse("Identifier is not equal to different Identifier", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierValue() {
		Identifier i = new Identifier("ABC");
		Identifier i2 = new Identifier("ABC");
		assertTrue("Identifier is equal to different Identifier with same value", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierValueIgnoringCase() {
		Identifier i = new Identifier("ABC");
		Identifier i2 = new Identifier("abc");
		assertTrue("Identifier is equal to different Identifier with same value", i.equals(i2));
	}

	@Test
	public void equalsSameIdentifierObject() {
		Identifier i = new Identifier("ABC");
		Identifier i2 = i;
		assertTrue("Identifier is equal to same Identifier", i.equals(i2));
	}

	@Test
	public void equalsReflexive() {
		Identifier i = new Identifier("ABC");
		assertTrue("equals method is reflexive", i.equals(i));
	}

	@Test
	public void equalsSymmetric() {
		Identifier i = new Identifier("ABC");
		Identifier e = new Identifier("ABC");
		Identifier n = new Identifier("XYZ");
		assertTrue("equals method is symmetric", i.equals(e) && e.equals(i));
		assertTrue("equals method is symmetric", !i.equals(n) && !n.equals(i));
	}

	@Test
	public void equalsTransitive() {
		Identifier e1 = new Identifier("ABC");
		Identifier e2 = new Identifier("ABC");
		Identifier e3 = new Identifier("ABC");
		Identifier n = new Identifier("XYZ");
		assertTrue("equals method is transitive", e1.equals(e2) && e2.equals(e3) && e1.equals(e3));
		assertTrue("equals method is transitive", e1.equals(e2) && !e2.equals(n) && !e1.equals(n));
	}

	@Test
	public void hashCodeSameForEqualIdentifiers() {
		Identifier e1 = new Identifier("ABC");
		Identifier e2 = new Identifier("ABC");
		assertTrue("Identifiers are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void hashCodeSameForEqualIdentifiersIgnoringCase() {
		Identifier e1 = new Identifier("ABC");
		Identifier e2 = new Identifier("abc");
		assertTrue("Identifiers are equal", e1.equals(e2));
		assertTrue("HashCodes are equal", e1.hashCode() == e2.hashCode());
	}

	@Test
	public void compareTo() {
		Identifier a1 = new Identifier("ABC");
		Identifier a2 = new Identifier("ABC");
		Identifier x = new Identifier("XYZ");
		assertTrue("ABC compareTo ABC is equal",a1.compareTo(a2) == 0);
		assertTrue("ABC compareTo XYZ is less", a1.compareTo(x) < 0);
		assertTrue("XYZ compareTo ABC is qreater", x.compareTo(a1) > 0);
	}

	@Test
	public void toStringSameCase() {
		final String ID_STRING = "ABC";
		Identifier i = new Identifier("ABC");
		assertEquals("toString is internal ID", ID_STRING, i.toString());
	}

	@Test
	public void toStringDifferentCase() {
		final String ID_STRING = "ABC";
		Identifier i = new Identifier("abc");
		assertEquals("toString is internal ID, ignoring case", ID_STRING, i.toString());
	}

}
