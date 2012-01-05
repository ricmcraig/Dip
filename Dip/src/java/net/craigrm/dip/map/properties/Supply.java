package net.craigrm.dip.map.properties;

public enum Supply {
	
	NONE("", 0),
	ONE("SC", 1);
	
	private static String expectedMessage = "Expected value of \"SC\" or \"\". ";
	
	private String supplyID;
	private int supplyValue;
	
	public static Supply getSupply(String supply){
		String trimmedSupply = supply.trim();
		
		for(Supply s: Supply.values()){
			if (s.getSupplyID().equalsIgnoreCase(trimmedSupply)){
				 return s;
			}
		}
		throw new IllegalArgumentException(Supply.expectedMessage + "Got: " + trimmedSupply);
	}
	
	public int getSupplyValue(){
		return this.supplyValue;
	}

	public String getSupplyID(){
		return this.supplyID;
	}

	private Supply (String supplyID, int supplyValue){
		this.supplyID = supplyID;
		this.supplyValue = supplyValue;
	}
	
}
