package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import net.craigrm.dip.gameturn.IPositionParser;
import net.craigrm.dip.state.Season;
import net.craigrm.dip.state.Year;
import net.craigrm.dip.scanners.StandardPositionParser;

import org.junit.Test;

public class PositionParserTest {

	private static final String SIMPLE_POSITION = "./src/test/resources/positions/simple.dippos";
	private static final Year SIMPLE_POSITION_YEAR = new Year("1901");
	private static final Season SIMPLE_POSITION_SEASON = Season.SPRING;
	private static final int SIMPLE_POSITION_UNITS_SIZE = 1;

	private static final String SIMPLE_POSITION_WITH_COMMENTS = "./src/test/resources/positions/simpleComments.dippos";
	private static final int SIMPLE_POSITION_WTH_COMMENTS_UNITS_SIZE = 2;
	
	private static final String STANDARD_MAP_INITIAL_POSITION = "./src/test/resources/positions/standardInitial.dippos";
	private static final int STANDARD_MAP_INITIAL_POSITION_UNITS_SIZE = 22;
	
	private static final String ERROR_NONEXISTENT_FILE = "./src/test/resources/general/nofile.txt";
	private static final String ERROR_EMPTY_FILE = "./src/test/resources/general/emptyfile.txt";
	private static final String ERROR_POSITION_NO_TURN_ID = "./src/test/resources/positions/errorNoTurnID.dippos";
	private static final String ERROR_POSITION_NO_UNITS = "./src/test/resources/positions/errorNoUnits.dippos";
	private static final String ERROR_POSITION_BAD_UNIT = "./src/test/resources/positions/errorBadUnit.dippos";

	@Test
	public void CanParseSimplePosition(){
		IPositionParser spp = new StandardPositionParser(SIMPLE_POSITION);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_YEAR, spp.getYear());
		assertEquals(SIMPLE_POSITION_SEASON, spp.getSeason());
		assertEquals(SIMPLE_POSITION_UNITS_SIZE, spp.getUnits().size());
	}
	
	@Test
	public void CanParseSimplePositionWithComments(){
		IPositionParser spp = new StandardPositionParser(SIMPLE_POSITION_WITH_COMMENTS);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_YEAR, spp.getYear());
		assertEquals(SIMPLE_POSITION_SEASON, spp.getSeason());
		assertEquals(SIMPLE_POSITION_WTH_COMMENTS_UNITS_SIZE, spp.getUnits().size());
	}
	
	@Test
	public void CanParseStandardInitialPosition(){
		IPositionParser spp = new StandardPositionParser(STANDARD_MAP_INITIAL_POSITION);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_YEAR, spp.getYear());
		assertEquals(SIMPLE_POSITION_SEASON, spp.getSeason());
		assertEquals(STANDARD_MAP_INITIAL_POSITION_UNITS_SIZE, spp.getUnits().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyOnNonExistentFile(){
		IPositionParser spp = new StandardPositionParser(ERROR_NONEXISTENT_FILE);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyOnEmptyFile(){
		IPositionParser spp = new StandardPositionParser(ERROR_EMPTY_FILE);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyWithNoTurnID(){
		IPositionParser spp = new StandardPositionParser(ERROR_POSITION_NO_TURN_ID);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyWithNoUnits(){
		IPositionParser spp = new StandardPositionParser(ERROR_POSITION_NO_UNITS);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyBadUnit(){
		IPositionParser spp = new StandardPositionParser(ERROR_POSITION_BAD_UNIT);
		spp.parsePositionDefinition();
	}
	
}
