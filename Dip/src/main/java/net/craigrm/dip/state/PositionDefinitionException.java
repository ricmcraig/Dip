package net.craigrm.dip.state;

public class PositionDefinitionException extends IllegalArgumentException{
	private static final long serialVersionUID = 0L;

	private final String positionID;
	private final int lineNo;
	private final String line;
	private final String expected;
	
	public PositionDefinitionException(String fileName, int lineNo, String line, String expected) {
		super("File name: " + fileName + ". Line number: " + lineNo + ". Line: " + line + ". Expected: " + expected + ". ");
		this.positionID = fileName;
		this.lineNo = lineNo;
		this.line = line;
		this.expected = expected;
	}

	public PositionDefinitionException(String fileName, int lineNo, String line, String expected, Throwable cause) {
		super("File name: " + fileName + ". Line number: " + lineNo + ". Line: " + line + ". Expected: " + expected + ". ", cause);
		this.positionID = fileName;
		this.lineNo = lineNo;
		this.line = line;
		this.expected = expected;
	}

	public String getFileName() {
		return positionID;
	}

	public int getLineNo() {
		return lineNo;
	}

	public String getBadLine() {
		return line;
	}

	public String getExpected() {
		return expected;
	}

}
