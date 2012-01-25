package net.craigrm.dip.map;

public class MapDefinitionException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;
	
	private final String mapDefinitionIdentifier;
	private final int recordNumber;
	
	public MapDefinitionException(String mapId, int recordNumber) {
		super();
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
	}

	public MapDefinitionException(String s, String mapId, int recordNumber) {
		super(s);
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
	}

	public MapDefinitionException(Throwable cause, String mapId, int recordNumber) {
		super(cause);
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
	}

	public MapDefinitionException(String message, Throwable cause, String mapId, int recordNumber) {
		super(message, cause);
		this.mapDefinitionIdentifier = mapId;
		this.recordNumber = recordNumber;
	}

	public String getMapDefinitionIdentifier() {
		return mapDefinitionIdentifier;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

}
