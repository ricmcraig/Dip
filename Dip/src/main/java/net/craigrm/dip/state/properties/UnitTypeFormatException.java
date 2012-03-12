package net.craigrm.dip.state.properties;

public class UnitTypeFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String unitType;
	private final String expected;
	
	public UnitTypeFormatException(String unitType, String expected) {
		super("Unit Type: " + unitType + ". Expected: " + expected + ". ");
		this.unitType = unitType;
		this.expected = expected;
	}

	public UnitTypeFormatException(String unitType, String expected, Throwable cause) {
		super("Unit Type: " + unitType + ". Expected: " + expected + ". ", cause);
		this.unitType = unitType;
		this.expected = expected;
	}

	public String getUnitType() {
		return unitType;
	}

	public String getExpected() {
		return expected;
	}

}
