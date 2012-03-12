package net.craigrm.dip.scanners;

import static org.junit.Assert.*;

import net.craigrm.dip.gameturn.IOrdersDataSource;
import net.craigrm.dip.orders.OrdersDefinitionException;
import net.craigrm.dip.state.TurnIdentifier;

import org.junit.Test;

public class OrdersParserTest {

	private static final String SIMPLE_ORDER = "./src/test/resources/orders/simpleOrders.dipord";
	private static final TurnIdentifier SIMPLE_ORDER_TURN_ID = new TurnIdentifier("1901S");
	private static final int STANDARD_ORDERS_SIZE = 1;

	private static final String SIMPLE_ORDERS_WITH_COMMENTS = "./src/test/resources/orders/simpleOrdersWithComments.dipord";
	private static final int SIMPLE_ORDERS_WITH_COMMENTS_SIZE = 2;

	private static final String SIMPLE_ORDERS_WITH_BLANK_LINES = "./src/test/resources/orders/simpleOrdersWithBlankLines.dipord";
	private static final int SIMPLE_ORDERS_WITH_BLANK_LINES_SIZE = 2;

	private static final String SIMPLE_ORDERS_HOLDS = "./src/test/resources/orders/simpleHolds.dipord";
	private static final int SIMPLE_ORDERS_HOLDS_SIZE = 14;

	private static final String SIMPLE_ORDERS_MOVES = "./src/test/resources/orders/simpleMoves.dipord";
	private static final int SIMPLE_ORDERS_MOVES_SIZE = 14;

	private static final String SIMPLE_ORDERS_SUPPORT_HOLDS = "./src/test/resources/orders/simpleSupportHolds.dipord";
	private static final int SIMPLE_ORDERS_SUPPORT_HOLDS_SIZE = 14;

	private static final String SIMPLE_ORDERS_SUPPORT_MOVES = "./src/test/resources/orders/simpleSupportMoves.dipord";
	private static final int SIMPLE_ORDERS_SUPPORT_MOVES_SIZE = 14;

	private static final String STANDARD_ORDERS_INITIAL_ORDERS = "./src/test/resources/orders/standardInitialOrders.dipord";
	private static final int STANDARD_ORDERS_INITIAL_ORDERS_SIZE = 22;

	private static final String SIMPLE_ORDERS_BAD_ORDER = "./src/test/resources/orders/simpleOrdersBadOrders.dipord";
	private static final int SIMPLE_ORDERS_BAD_ORDER_SIZE = 2;

	private static final String ERROR_NONEXISTENT_FILE = "./src/test/resources/general/nofile.txt";
	private static final String ERROR_EMPTY_FILE = "./src/test/resources/general/emptyfile.txt";
	private static final String ERROR_ORDERS_NO_TURN_ID = "./src/test/resources/orders/errorOrdersNoTurnID.dipord";
	private static final String ERROR_ORDERS_BAD_TURN_ID = "./src/test/resources/orders/errorOrdersBadTurnID.dipord";
	private static final String ERROR_ORDERS_NO_POWER = "./src/test/resources/orders/errorOrdersNoPower.dipord";
	private static final String ERROR_ORDERS_BAD_POWER = "./src/test/resources/orders/errorOrdersBadPower.dipord";

	@Test
	public void canParseSimpleOrder() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDER);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(STANDARD_ORDERS_SIZE, sop.getOrders().size());
	}

	@Test
	public void canParseSimpleOrderWithComments() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_WITH_COMMENTS);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_WITH_COMMENTS_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseSimpleOrderWithBlankLines() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_WITH_BLANK_LINES);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_WITH_BLANK_LINES_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseSimpleHoldOrders() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_HOLDS);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_HOLDS_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseSimpleMoveOrders() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_MOVES);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_MOVES_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseSimpleSupportHoldsOrders() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_SUPPORT_HOLDS);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_SUPPORT_HOLDS_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseSimpleSupportMovesOrders() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_SUPPORT_MOVES);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_SUPPORT_MOVES_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void canParseStandardInitialOrders() {
		IOrdersDataSource sop = new StandardOrdersParser(STANDARD_ORDERS_INITIAL_ORDERS);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(STANDARD_ORDERS_INITIAL_ORDERS_SIZE, sop.getOrders().size());
	}
	
	@Test
	public void completesDespiteBadOrder() {
		IOrdersDataSource sop = new StandardOrdersParser(SIMPLE_ORDERS_BAD_ORDER);
		assertEquals(SIMPLE_ORDER_TURN_ID, sop.getTurnID());
		assertEquals(SIMPLE_ORDERS_BAD_ORDER_SIZE, sop.getOrders().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void failsCorrectlyOnNonExistentFile() {
		new StandardOrdersParser(ERROR_NONEXISTENT_FILE);
	}
	
	@Test(expected=OrdersDefinitionException.class)
	public void failsCorrectlyOnEmptyFile() {
		new StandardOrdersParser(ERROR_EMPTY_FILE);
	}
	
	@Test(expected=OrdersDefinitionException.class)
	public void failsCorrectlyWithNoTurnID() {
		new StandardOrdersParser(ERROR_ORDERS_NO_TURN_ID);
	}
	
	@Test(expected=OrdersDefinitionException.class)
	public void failsCorrectlyWithBadTurnID() {
		new StandardOrdersParser(ERROR_ORDERS_BAD_TURN_ID);
	}
	
	@Test(expected=OrdersDefinitionException.class)
	public void failsCorrectlyWithNoPower() {
		new StandardOrdersParser(ERROR_ORDERS_NO_POWER);
	}
	
	@Test(expected=OrdersDefinitionException.class)
	public void failsCorrectlyBadPower() {
		new StandardOrdersParser(ERROR_ORDERS_BAD_POWER);
	}
	
}
