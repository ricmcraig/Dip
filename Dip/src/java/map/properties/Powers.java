package map.properties;

public enum Powers {
	AUSTRIAHUNGARY("A"),
	ENGLAND("E"),
	FRANCE("F"),
	GERMANY("G"),
	ITALY("I"),
	RUSSIA("R"),
	TURKEY("T"),
	NONE("");
	
	public static String expectedMessage = "Expected value of \"A\", \"E\", \"F\", \"G\", \"I\", \"R\", \"T\" or \"\". ";
	
	private String powerID;
	
	private Powers (String powerID){
		this.powerID = powerID;
	}
	
	public String getPowerID(){
		return this.powerID;
	}
}
