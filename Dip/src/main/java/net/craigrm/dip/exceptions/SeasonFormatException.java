package net.craigrm.dip.exceptions;

public class SeasonFormatException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028307236168861430L;

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
