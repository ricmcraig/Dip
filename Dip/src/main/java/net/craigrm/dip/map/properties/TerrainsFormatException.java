package net.craigrm.dip.map.properties;

public class TerrainsFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 0L;
	
	private final String terrain;
	private final String expected; 
	
	public TerrainsFormatException(String terrain, String expected) {
		super("Terrain: " + terrain + ". Expected: " + expected + ". ");
		this.terrain = terrain;
		this.expected = expected;
	}

	public TerrainsFormatException(String terrain, String expected, Throwable cause) {
		super("Terrain: " + terrain + ". Expected: " + expected + ". ", cause);
		this.terrain = terrain;
		this.expected = expected;
	}

	public String getTerrain() {
		return terrain;
	}

	public String getExpected() {
		return expected;
	}

}
