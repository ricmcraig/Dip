package map.properties;

public enum Supply {
	
	NONE("", 0),
	ONE("SC", 1);
	
	public static String expectedMessage = "Expected value of \"SC\" or \"\". ";
	
	private String supplyID;
	private int supplyValue;
	
	private Supply (String supplyID, int supplyValue){
		this.supplyID = supplyID;
		this.supplyValue = supplyValue;
	}
	
	public int getSupplyValue(){
		return this.supplyValue;
	}

	public String getSupplyID(){
		return this.supplyID;
	}

}
