package net.craigrm.dip.state.properties;

public enum UnitType {
	ARMY("A"),
	FLEET("F"),
	NONE("");

	private static String expectedMessage = "Expected value of \"A\" or \"F\". ";
	
	private String unitType;
	
	public static UnitType getType(String type){
		String trimmedType = type.trim();
		
		for(UnitType t: UnitType.values()){
			if (t.getType().equalsIgnoreCase(trimmedType)){
				return t;
			}
		}
		throw new UnitTypeFormatException(UnitType.expectedMessage + "Got: " + trimmedType);
	}

	public String getType(){
		return this.unitType;
	}

	private UnitType(String unitType){
		this.unitType = unitType;
	}

}
