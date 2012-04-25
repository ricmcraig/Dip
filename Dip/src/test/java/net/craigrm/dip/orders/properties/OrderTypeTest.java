package net.craigrm.dip.orders.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTypeTest {

	private static final String MOVE_ORDER_STRING = "to";
	private static final String HOLD_ORDER_STRING = "hold";
	private static final String SUPPORT_ORDER_STRING = "sup";
	private static final String CONVOY_ORDER_STRING = "con";

	@Test
	public void getOrderTypeFromMoveString() {
		assertEquals("Should return TO type for \"to\" string", OrderType.MOVE, OrderType.getOrderType(MOVE_ORDER_STRING));
	}

	@Test
	public void getOrderTypeFromHoldString() {
		assertEquals("Should return HOLD type for \"hold\" string", OrderType.HOLD, OrderType.getOrderType(HOLD_ORDER_STRING));
	}

	@Test
	public void getOrderTypeFromSupportString() {
		assertEquals("Should return SUPPORT type for \"sup\" string", OrderType.SUPPORT, OrderType.getOrderType(SUPPORT_ORDER_STRING));
	}

	@Test
	public void getOrderTypeFromConvoyString() {
		assertEquals("Should return CONVOY type for \"con\" string", OrderType.CONVOY, OrderType.getOrderType(CONVOY_ORDER_STRING));
	}

	@Test
	public void getOrderTypeFromMoveStringUpperCase() {
		assertEquals("Match should be case insensitive", OrderType.MOVE, OrderType.getOrderType(MOVE_ORDER_STRING.toUpperCase()));
	}

	@Test
	public void getOrderTypeFromMoveStringWhiteSpace() {
		assertEquals("Match should ignore leading and trailing whitespace", OrderType.MOVE, OrderType.getOrderType("  " + MOVE_ORDER_STRING + "\t\r\n"));
	}

	@Test(expected=OrderTypeFormatException.class)
	public void getOrderTypeFromBadString() {
		OrderType.getOrderType("X");
	}

}
