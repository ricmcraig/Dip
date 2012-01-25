package net.craigrm.dip.state.properties;

public class SeasonFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	public SeasonFormatException() {
		super();
	}

	public SeasonFormatException(String s) {
		super(s);
	}

	public SeasonFormatException(Throwable cause) {
		super(cause);
	}

	public SeasonFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
