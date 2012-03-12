package net.craigrm.dip.orders.properties;

public class AdjustmentTypeFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String adjustmentType;
	private final String expected;

	public AdjustmentTypeFormatException(String adjustmentType, String expected) {
		super("Adjustment Type: " + adjustmentType + ". Expected: " + expected + ". ");
		this.adjustmentType = adjustmentType;
		this.expected = expected;
	}

	public AdjustmentTypeFormatException(String adjustmentType, String expected, Throwable cause) {
		super("Adjustment Type: " + adjustmentType + ". Expected: " + expected + ". ", cause);
		this.adjustmentType = adjustmentType;
		this.expected = expected;
	}

	public String getOrderType() {
		return adjustmentType;
	}

	public String getExpected() {
		return expected;
	}

}
