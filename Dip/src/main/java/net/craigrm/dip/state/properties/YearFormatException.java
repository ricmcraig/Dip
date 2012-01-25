package net.craigrm.dip.state.properties;

public class YearFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	public YearFormatException() {
		super();
	}

	public YearFormatException(String s) {
		super(s);
	}

	public YearFormatException(Throwable cause) {
		super(cause);
	}

	public YearFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
