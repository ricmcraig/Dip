package net.craigrm.dip.map.properties;

public class PowersFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String power;
	private final String expected;
	
	public PowersFormatException(String power, String expected) {
		super("Power: " + power + ". Expected: " + expected + ". ");
		this.power = power;
		this.expected = expected;
	}

	public PowersFormatException(String power, String expected, Throwable cause) {
		super("Power: " + power + ". Expected: " + expected + ". ", cause);
		this.power = power;
		this.expected = expected;
	}

	public String getPower() {
		return power;
	}

	public String getExpected() {
		return expected;
	}

}
