package net.craigrm.dip.state;

public class TurnIdentifierFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;

	private final String turnID;
	
	public TurnIdentifierFormatException(String turnID) {
		super("TurnID: " + turnID);
		this.turnID = turnID;
	}

	public TurnIdentifierFormatException(String turnID, Throwable cause) {
		super("TurnID: " + turnID, cause);
		this.turnID = turnID;
	}

	public String getTurnID() {
		return turnID;
	}

}
