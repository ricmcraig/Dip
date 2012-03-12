package net.craigrm.dip.map;

public class MapDefinitionException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;
	
	private final String mapDefinitionIdentifier;
	private final int recordNumber;
	private final String record;
	private final String expected;
	
	
	public MapDefinitionException(String mapId, int recordNumber, String record, String expected) {
		super("Map ID: " + mapId + ". Record Number: " + recordNumber + ". Record: " + record + ". Expected: " + expected + ". ");
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
		this.record = record;
		this.expected = expected;
	}

	public MapDefinitionException(String mapId, int recordNumber, String record, String expected, Throwable cause) {
		super("Map ID: " + mapId + ". Record Number: " + recordNumber + ". Record: " + record + ". Expected: " + expected + ". ", cause);
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
		this.record = record;
		this.expected = expected;
	}

	public String getMapDefinitionIdentifier() {
		return mapDefinitionIdentifier;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public String getRecord() {
		return record;
	}

	public String getExpected() {
		return expected;
	}

}
