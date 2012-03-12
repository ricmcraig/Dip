package net.craigrm.dip.orders;

public class AdjustmentFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String ordersID;
	private final int lineNo;
	private final String line;
	private final String expected;
	
	public AdjustmentFormatException(String ordersID, int lineNo, String line, String expected) {
		super("Orders ID: " + ordersID + ". Line number: " + lineNo + " .Line: " + line + " .Expected: " + expected + ". ");
		this.ordersID = ordersID;
		this.lineNo = lineNo;
		this.line = line;
		this.expected = expected;
		}

	public AdjustmentFormatException(String ordersID, int lineNo, String line, String expected, Throwable cause) {
		super("Orders ID: " + ordersID + ". Line number: " + lineNo + " .Line: " + line + " .Expected: " + expected + ". ", cause);
		this.ordersID = ordersID;
		this.lineNo = lineNo;
		this.line = line;
		this.expected = expected;
	}

	public String getFileName() {
		return ordersID;
	}

	public int getLineNo() {
		return lineNo;
	}

	public String getBadLine() {
		return line;
	}

	public String getExpected() {
		return expected;
	}

}
