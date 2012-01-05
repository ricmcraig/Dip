package net.craigrm.dip.map.properties;

public enum Powers {
	AUSTRIAHUNGARY("A"),
	ENGLAND("E"),
	FRANCE("F"),
	GERMANY("G"),
	ITALY("I"),
	RUSSIA("R"),
	TURKEY("T"),
	NONE("");
	
	private static String expectedMessage = "Expected value of \"A\", \"E\", \"F\", \"G\", \"I\", \"R\", \"T\" or \"\". ";
	
	private String powerID;
	
	public static Powers getPower (String power){
		String trimmedPower = power.trim();
		
		for (Powers p: Powers.values()){
			if (p.getPowerID().equalsIgnoreCase(trimmedPower)){
				return p;
			}
		}
		throw new IllegalArgumentException(Powers.expectedMessage + "Got: " + trimmedPower);
	}

	public String getPowerID(){
		return this.powerID;
	}
	private Powers (String powerID){
		this.powerID = powerID;
	}
	
}
