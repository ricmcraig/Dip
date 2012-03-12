package net.craigrm.dip.orders;

public class DuplicateOrderException extends IllegalStateException {

	private static final long serialVersionUID = 0L;

	private final String duplicateOrder;
	
	public DuplicateOrderException(String order) {
		super("Order: " + order);
		this.duplicateOrder = order;
	}

	public DuplicateOrderException(String order, Throwable cause) {
		super("Order: " + order);
		this.duplicateOrder = order;
	}

	public String getDuplicateOrder() {
		return duplicateOrder;
	}

}
