package net.craigrm.dip.state.properties;

public class YearFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String year;
	
	public YearFormatException(String year) {
		super("Year string: " + year);
		this.year = year;
	}

	public YearFormatException(String year, Throwable cause) {
		super("Year string: " + year, cause);
		this.year = year;
	}

	public String getYear() {
		return year;
	}

}
