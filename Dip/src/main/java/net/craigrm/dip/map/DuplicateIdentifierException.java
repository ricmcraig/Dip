package net.craigrm.dip.map;

public class DuplicateIdentifierException extends IllegalStateException {

	private static final long serialVersionUID = 0L;

	private final String duplicateIdentifier;
	
	public DuplicateIdentifierException(String id) {
		super();
		duplicateIdentifier = id;
	}

	public DuplicateIdentifierException(String s, String id) {
		super(s);
		duplicateIdentifier = id;
	}

	public DuplicateIdentifierException(Throwable cause, String id) {
		super(cause);
		duplicateIdentifier = id;
	}

	public DuplicateIdentifierException(String message, Throwable cause, String id) {
		super(message, cause);
		duplicateIdentifier = id;
	}

	public String getDuplicateIdentifier() {
		return duplicateIdentifier;
	}

}
