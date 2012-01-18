package net.craigrm.dip.map;

/**
 * Thrown to indicate that application has attempted to construct a 
 * {@link net.craigrm.dip.map.Aliases} instance with a bad initial
 * state.
 * 
 * @author Ric Craig
 *
 */
public class AliasesStateException extends IllegalStateException {

	private static final long serialVersionUID = 0L;
	
	private final String duplicateAlias;
	
	public AliasesStateException() {
		super();
		this.duplicateAlias = null;
	}

	public AliasesStateException(String s, String duplicate) {
		super(s);
		this.duplicateAlias = duplicate;
	}

	public AliasesStateException(Throwable cause) {
		super(cause);
		this.duplicateAlias = null;
	}

	public AliasesStateException(String message, Throwable cause) {
		super(message, cause);
		this.duplicateAlias = null;
	}

	public String getDuplicateAlias() {
		return duplicateAlias;
	}
	
}
