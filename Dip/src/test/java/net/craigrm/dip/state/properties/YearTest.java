package net.craigrm.dip.state.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class YearTest {

	@Test
	public void getCorrectYearNumber() {
		assertEquals("Year number should correspind to constructor parameter", 1901, new Year("1901").getYearNumber());
	}

	@Test(expected=YearFormatException.class)
	public void makeYearFromBadString() {
		new Year("X");
	}

	@Test
	public void DifferenceBetweenCurrentYearAndNextYearIsOne() {
		Year year = new Year("1901");
		assertEquals("Next year should be one more than current year", 1, year.next().getYearNumber() - year.getYearNumber());
	}

	@Test
	public void YearsConstructedFromSameStringValueAreEqual() {
		Year year1 = new Year("1901");
		Year year2 = new Year("1901");
		assertTrue("Two years should be equal", year1.equals(year2));
		assertTrue("Two years should be equal", year2.equals(year1));
	}

	@Test
	public void YearsConstructedFromDifferentStringValuesAreNotEqual() {
		Year year1 = new Year("1901");
		Year year2 = new Year("1902");
		assertFalse("Two years should not be equal", year1.equals(year2));
		assertFalse("Two years should not be equal", year2.equals(year1));
	}

	@Test
	public void AYearIsNotEqualToNull() {
		Year year = new Year("1901");
		assertFalse("Two years should not be equal", year.equals(null));
	}

}
