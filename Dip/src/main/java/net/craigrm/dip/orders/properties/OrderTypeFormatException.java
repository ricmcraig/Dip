package net.craigrm.dip.orders.properties;

public class OrderTypeFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String orderType;
	private final String expected;

	public OrderTypeFormatException(String orderType, String expected) {
		super("Order Type: " + orderType + ". Expected: " + expected + ". ");
		this.orderType = orderType;
		this.expected = expected;
	}

	public OrderTypeFormatException(String orderType, String expected, Throwable cause) {
		super("Order Type: " + orderType + ". Expected: " + expected + ". ", cause);
		this.orderType = orderType;
		this.expected = expected;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getExpected() {
		return expected;
	}

}
