package net.craigrm.dip.map.properties;

public enum Terrains {
	SEA("S"),
	INLAND("L"),
	COAST("L");

	private static String expectedMessage = "\"S\" or \"L\"";
	
	private String broadType;
	
	public static Terrains getTerrain(String terrain) {
		String trimmedType = terrain.trim();
		
		for(Terrains t: Terrains.values()) {
			if (t.getBroadType().equalsIgnoreCase(trimmedType)) {
				return t;
			}
		}
		throw new TerrainsFormatException(trimmedType, Terrains.expectedMessage);
	}

	public String getBroadType() {
		return this.broadType;
	}

	private Terrains(String broadType) {
		this.broadType = broadType;
	}
}
