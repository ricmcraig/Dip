package net.craigrm.dip.state.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeasonTest {

	private static final String SPRING_SEASON_STRING = "s";
	private static final String FALL_SEASON_STRING = "f";

	@Test
	public void getSeasonFromSpringString() {
		assertEquals("", Season.SPRING, Season.getSeason(SPRING_SEASON_STRING));
	}

	@Test
	public void getSeasonFromFallString() {
		assertEquals("", Season.FALL, Season.getSeason(FALL_SEASON_STRING));
	}

	@Test
	public void getSeasonFromSpringStringUpperCase() {
		assertEquals("", Season.SPRING, Season.getSeason(SPRING_SEASON_STRING.toUpperCase()));
	}

	@Test
	public void getSeasonFromMoveStringWhiteSpace() {
		assertEquals("", Season.SPRING, Season.getSeason("  " + SPRING_SEASON_STRING + "\t\r\n"));
	}

	@Test(expected=SeasonFormatException.class)
	public void getSeasonFromBadString() {
		Season.getSeason("X");
	}

	@Test
	public void getNextSeasonSpring() {
		assertEquals("", Season.FALL ,Season.SPRING.next());
	}

	@Test
	public void getNextSeasonFall() {
		assertEquals("" ,Season.SPRING, Season.FALL.next());
	}

	@Test
	public void SpringIsNotAdjustmentSeason() {
		assertFalse("" ,Season.SPRING.isAdjustmentSeason());
	}

	@Test
	public void FallIsAdjustmentSeason() {
		assertTrue("" ,Season.FALL.isAdjustmentSeason());
	}

}
