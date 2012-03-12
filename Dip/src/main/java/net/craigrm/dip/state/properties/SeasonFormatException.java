package net.craigrm.dip.state.properties;

public class SeasonFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String season; 
	private final String expected; 
	
	public SeasonFormatException(String season, String expected) {
		super("Season: " + season + ". Expected: " + expected);
		this.season = season;
		this.expected = expected;
	}

	public SeasonFormatException(String expected, String season, Throwable cause) {
		super("Season: " + season + ". Expected: " + expected, cause);
		this.season = season;
		this.expected = expected;
	}

	public String getSeason() {
		return season;
	}

	public String getExpected() {
		return expected;
	}

}
