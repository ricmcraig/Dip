package map.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowersTest {

	private static final String AUSTRIA_HUNGARY_ID = "A";
	private static final String ENGLAND_ID = "E";
	private static final String FRANCE_ID = "F";
	private static final String GERMANY_ID = "G";
	private static final String ITALY_ID = "I";
	private static final String RUSSIA_ID = "R";
	private static final String TURKEY_ID = "T";
	private static final String NONE_ID = "";

	@Test
	public void powerAustriaHungaryHasCorrectID() {
		Powers p = Powers.AUSTRIAHUNGARY;
		assertEquals("Austria-Hungary has correct ID", AUSTRIA_HUNGARY_ID, p.getPowerID());
	}

	@Test
	public void powerEnglandHasCorrectID() {
		Powers p = Powers.ENGLAND;
		assertEquals("England has correct ID", ENGLAND_ID, p.getPowerID());
	}

	@Test
	public void powerFranceHasCorrectID() {
		Powers p = Powers.FRANCE;
		assertEquals("France has correct ID", FRANCE_ID, p.getPowerID());
	}

	@Test
	public void powerGermanyHasCorrectID() {
		Powers p = Powers.GERMANY;
		assertEquals("Germany has correct ID", GERMANY_ID, p.getPowerID());
	}

	@Test
	public void powerItalyHasCorrectID() {
		Powers p = Powers.ITALY;
		assertEquals("Italy has correct ID", ITALY_ID, p.getPowerID());
	}

	@Test
	public void powerRussiaHasCorrectID() {
		Powers p = Powers.RUSSIA;
		assertEquals("Russia has correct ID", RUSSIA_ID, p.getPowerID());
	}

	@Test
	public void powerTurkeyHasCorrectID() {
		Powers p = Powers.TURKEY;
		assertEquals("Turkey has correct ID", TURKEY_ID, p.getPowerID());
	}

	@Test
	public void powerNoneHasCorrectID() {
		Powers p = Powers.NONE;
		assertEquals("None has correct ID", NONE_ID, p.getPowerID());
	}

}
