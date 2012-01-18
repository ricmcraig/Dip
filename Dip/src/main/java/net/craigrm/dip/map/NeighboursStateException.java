package net.craigrm.dip.map;

/**
 * Thrown to indicate that application has attempted to construct a 
 * {@link net.craigrm.dip.map.Neighbours} instance with a bad initial
 * state.
 * 
 * @author Ric Craig
 *
 */
public class NeighboursStateException extends IllegalStateException {

	private static final long serialVersionUID = 0L;
	
	private final String duplicateNeighbour;
	
	public NeighboursStateException() {
		super();
		this.duplicateNeighbour = null;
	}

	public NeighboursStateException(String s, String duplicate) {
		super(s);
		this.duplicateNeighbour = duplicate;
	}

	public NeighboursStateException(Throwable cause) {
		super(cause);
		this.duplicateNeighbour = null;
	}

	public NeighboursStateException(String message, Throwable cause) {
		super(message, cause);
		this.duplicateNeighbour = null;
	}

	public String getDuplicateAlias() {
		return duplicateNeighbour;
	}

	
}
