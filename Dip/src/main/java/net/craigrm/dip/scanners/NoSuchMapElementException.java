package net.craigrm.dip.scanners;

import java.util.NoSuchElementException;

public class NoSuchMapElementException extends NoSuchElementException {

	private static final long serialVersionUID = 0L;

	private final String missingElementName;
	
	public NoSuchMapElementException(String elName) {
		super();
		missingElementName = elName;
	}

	public NoSuchMapElementException(String s, String elName) {
		super(s);
		missingElementName = elName;
	}

	public String getMissingElementName() {
		return missingElementName;
	}

}
