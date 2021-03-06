package net.craigrm.dip.map.properties;

import static org.junit.Assert.*;
import net.craigrm.dip.map.properties.Powers;

import org.junit.Test;

public class PowersTest {

	private static final String AUSTRIA_HUNGARY_STRING = "A";
	private static final String ENGLAND_STRING = "E";
	private static final String FRANCE_STRING = "F";
	private static final String GERMANY_STRING = "G";
	private static final String ITALY_STRING = "I";
	private static final String RUSSIA_STRING = "R";
	private static final String TURKEY_STRING = "T";

	@Test
	public void powerAustriaHungaryHasCorrectID() {
		Powers p = Powers.AUSTRIAHUNGARY;
		assertEquals("Austria-Hungary has correct ID", AUSTRIA_HUNGARY_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromAustriaHungaryString() {
		Powers p = Powers.getPowerFromID(AUSTRIA_HUNGARY_STRING);
		assertEquals("Austria-Hungary power formed correctly from String", Powers.AUSTRIAHUNGARY, p);
	}

	@Test
	public void powerEnglandHasCorrectID() {
		Powers p = Powers.ENGLAND;
		assertEquals("England has correct ID", ENGLAND_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromEnglandString() {
		Powers p = Powers.getPowerFromID(ENGLAND_STRING);
		assertEquals("England power formed correctly from String", Powers.ENGLAND, p);
	}

	@Test
	public void powerFranceHasCorrectID() {
		Powers p = Powers.FRANCE;
		assertEquals("France has correct ID", FRANCE_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromFranceString() {
		Powers p = Powers.getPowerFromID(FRANCE_STRING);
		assertEquals("France power formed correctly from String", Powers.FRANCE, p);
	}

	@Test
	public void powerGermanyHasCorrectID() {
		Powers p = Powers.GERMANY;
		assertEquals("Germany has correct ID", GERMANY_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromGermanyString() {
		Powers p = Powers.getPowerFromID(GERMANY_STRING);
		assertEquals("Germany power formed correctly from String", Powers.GERMANY, p);
	}

	@Test
	public void powerItalyHasCorrectID() {
		Powers p = Powers.ITALY;
		assertEquals("Italy has correct ID", ITALY_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromItalyString() {
		Powers p = Powers.getPowerFromID(ITALY_STRING);
		assertEquals("Italy power formed correctly from String", Powers.ITALY, p);
	}

	@Test
	public void powerRussiaHasCorrectID() {
		Powers p = Powers.RUSSIA;
		assertEquals("Russia has correct ID", RUSSIA_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromRussiaString() {
		Powers p = Powers.getPowerFromID(RUSSIA_STRING);
		assertEquals("Russia power formed correctly from String", Powers.RUSSIA, p);
	}

	@Test
	public void powerTurkeyHasCorrectID() {
		Powers p = Powers.TURKEY;
		assertEquals("Turkey has correct ID", TURKEY_STRING, p.getPowerID());
	}

	@Test
	public void getPowerFromTurkeyString() {
		Powers p = Powers.getPowerFromID(TURKEY_STRING);
		assertEquals("Turkey power formed correctly from String", Powers.TURKEY, p);
	}

	@Test(expected=PowersFormatException.class)
	public void getPowerFromBadString() {
		Powers.getPowerFromID("X");
	}

}
