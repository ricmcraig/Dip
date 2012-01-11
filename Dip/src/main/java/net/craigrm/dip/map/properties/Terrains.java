package net.craigrm.dip.map.properties;

public enum Terrains {
	SEA("S"),
	INLAND("L"),
	COAST("L");

	private static String expectedMessage = "Expected value of \"S\" or \"L\". ";
	
	private String broadType;
	
	public static Terrains getTerrain(String terrain){
		String trimmedType = terrain.trim();
		
		for(Terrains t: Terrains.values()){
			if (t.getBroadType().equalsIgnoreCase(trimmedType)){
				return t;
			}
		}
		throw new IllegalArgumentException(Terrains.expectedMessage + "Got: " + trimmedType);
	}

	public String getBroadType(){
		return this.broadType;
	}

	private Terrains(String broadType){
		this.broadType = broadType;
	}
}