package net.craigrm.dip.scanners;

import java.util.NoSuchElementException;

public class NoSuchMapElementException extends NoSuchElementException {

	private static final long serialVersionUID = 0L;

	private final String missingElementName;
	
	public NoSuchMapElementException(String elName) {
		super("Missing element: " + elName);
		missingElementName = elName;
	}

	public String getMissingElementName() {
		return missingElementName;
	}

}
