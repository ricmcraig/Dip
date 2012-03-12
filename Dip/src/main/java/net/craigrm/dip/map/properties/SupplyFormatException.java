package net.craigrm.dip.map.properties;

public class SupplyFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String supply;
	private final String expected;
	
	public SupplyFormatException(String supply, String expected) {
		super("Supply: " + supply + ". Expected: " + expected + ". ");
		this.supply = supply;
		this.expected = expected;
	}

	public SupplyFormatException(String supply, String expected, Throwable cause) {
		super("Supply: " + supply + ". Expected: " + expected + ". ", cause);
		this.supply = supply;
		this.expected = expected;
	}

	public String getSupply() {
		return supply;
	}

	public String getExpected() {
		return expected;
	}

}
