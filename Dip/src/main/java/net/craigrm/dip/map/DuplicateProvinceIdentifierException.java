package net.craigrm.dip.map;

public class DuplicateProvinceIdentifierException extends IllegalStateException {

	private static final long serialVersionUID = 0L;

	private final String duplicateIdentifier;
	
	public DuplicateProvinceIdentifierException(String dupID) {
		super("Identifier: " + dupID);
		duplicateIdentifier = dupID;
	}

	public DuplicateProvinceIdentifierException(String dupID, Throwable cause) {
		super("Identifier: " + dupID, cause);
		duplicateIdentifier = dupID;
	}

	public String getDuplicateIdentifier() {
		return duplicateIdentifier;
	}

}
