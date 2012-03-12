package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import net.craigrm.dip.gameturn.IPositionDataSource;
import net.craigrm.dip.state.PositionDefinitionException;
import net.craigrm.dip.state.TurnIdentifier;
import net.craigrm.dip.scanners.StandardPositionParser;

import org.junit.Test;

public class PositionParserTest {

	private static final String SIMPLE_POSITION = "./src/test/resources/positions/simple.dippos";
	private static final TurnIdentifier SIMPLE_POSITION_TURN_ID = new TurnIdentifier("1901S");
	private static final int SIMPLE_POSITION_UNITS_SIZE = 1;
	private static final int SIMPLE_POSITION_CONTROL_SIZE = 1;

	private static final String SIMPLE_POSITION_WITH_COMMENTS = "./src/test/resources/positions/simpleComments.dippos";
	private static final int SIMPLE_POSITION_WTH_COMMENTS_UNITS_SIZE = 2;
	private static final int SIMPLE_POSITION_WTH_COMMENTS_CONTROL_SIZE = 1;
	
	private static final String SIMPLE_POSITION_WITH_BLANK_LINES = "./src/test/resources/positions/simpleBlankLines.dippos";
	private static final int SIMPLE_POSITION_WTH_BLANK_LINES_UNITS_SIZE = 2;
	private static final int SIMPLE_POSITION_WTH_BLANK_LINES_CONTROL_SIZE = 1;
	
	private static final String STANDARD_MAP_INITIAL_POSITION = "./src/test/resources/positions/standardInitial.dippos";
	private static final int STANDARD_MAP_INITIAL_POSITION_UNITS_SIZE = 22;
	private static final int STANDARD_MAP_INITIAL_POSITION_CONTROL_SIZE = 22;
	
	private static final String ERROR_NONEXISTENT_FILE = "./src/test/resources/general/nofile.txt";
	private static final String ERROR_EMPTY_FILE = "./src/test/resources/general/emptyfile.txt";
	private static final String ERROR_POSITION_NO_TURN_ID = "./src/test/resources/positions/errorNoTurnID.dippos";
	private static final String ERROR_POSITION_NO_UNITS = "./src/test/resources/positions/errorNoUnits.dippos";
	private static final String ERROR_POSITION_BAD_UNIT = "./src/test/resources/positions/errorBadUnit.dippos";
	private static final String ERROR_POSITION_NO_CONTROL = "./src/test/resources/positions/errorNoControl.dippos";
	private static final String ERROR_POSITION_BAD_CONTROL = "./src/test/resources/positions/errorBadControl.dippos";

	@Test
	public void CanParseSimplePosition() {
		IPositionDataSource spp = new StandardPositionParser(SIMPLE_POSITION);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_TURN_ID, spp.getTurnID());
		assertEquals(SIMPLE_POSITION_UNITS_SIZE, spp.getUnits().size());
		assertEquals(SIMPLE_POSITION_CONTROL_SIZE, spp.getControl().size());
	}
	
	@Test
	public void CanParseSimplePositionWithComments() {
		IPositionDataSource spp = new StandardPositionParser(SIMPLE_POSITION_WITH_COMMENTS);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_TURN_ID, spp.getTurnID());
		assertEquals(SIMPLE_POSITION_WTH_COMMENTS_UNITS_SIZE, spp.getUnits().size());
		assertEquals(SIMPLE_POSITION_WTH_COMMENTS_CONTROL_SIZE, spp.getControl().size());
	}
	
	@Test
	public void CanParseSimplePositionWithBlankLines() {
		IPositionDataSource spp = new StandardPositionParser(SIMPLE_POSITION_WITH_BLANK_LINES);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_TURN_ID, spp.getTurnID());
		assertEquals(SIMPLE_POSITION_WTH_BLANK_LINES_UNITS_SIZE, spp.getUnits().size());
		assertEquals(SIMPLE_POSITION_WTH_BLANK_LINES_CONTROL_SIZE, spp.getControl().size());
	}
	
	@Test
	public void CanParseStandardInitialPosition() {
		IPositionDataSource spp = new StandardPositionParser(STANDARD_MAP_INITIAL_POSITION);
		spp.parsePositionDefinition();
		assertEquals(SIMPLE_POSITION_TURN_ID, spp.getTurnID());
		assertEquals(STANDARD_MAP_INITIAL_POSITION_UNITS_SIZE, spp.getUnits().size());
		assertEquals(STANDARD_MAP_INITIAL_POSITION_CONTROL_SIZE, spp.getControl().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void FailsCorrectlyOnNonExistentFile() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_NONEXISTENT_FILE);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyOnEmptyFile() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_EMPTY_FILE);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyWithNoTurnID() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_POSITION_NO_TURN_ID);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyWithNoUnits() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_POSITION_NO_UNITS);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyBadUnit() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_POSITION_BAD_UNIT);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyNoControl() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_POSITION_NO_CONTROL);
		spp.parsePositionDefinition();
	}
	
	@Test(expected=PositionDefinitionException.class)
	public void FailsCorrectlyBadControl() {
		IPositionDataSource spp = new StandardPositionParser(ERROR_POSITION_BAD_CONTROL);
		spp.parsePositionDefinition();
	}
	
}
