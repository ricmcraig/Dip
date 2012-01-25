package net.craigrm.dip.orders.properties;

public class OrderTypeFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	public OrderTypeFormatException() {
		super();
	}

	public OrderTypeFormatException(String s) {
		super(s);
	}

	public OrderTypeFormatException(Throwable cause) {
		super(cause);
	}

	public OrderTypeFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
