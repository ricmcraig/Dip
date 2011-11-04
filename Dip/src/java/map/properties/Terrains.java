package map.properties;

public enum Terrains {
	SEA("S"),
	INLAND("L"),
	COAST("L");

	public static String expectedMessage = "Expected value of \"S\" or \"L\". ";
	

	private String broadType;
	
	private Terrains(String broadType){
		this.broadType = broadType;
	}
	
	public String getBroadType(){
		return this.broadType;
	}
}
