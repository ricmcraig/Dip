package net.craigrm.dip.map.properties;

public enum Powers {
	AUSTRIAHUNGARY("A", "Austria-Hungary"),
	ENGLAND("E", "England"),
	FRANCE("F", "France"),
	GERMANY("G", "Germany"),
	ITALY("I", "Italy"),
	RUSSIA("R", "Russia"),
	TURKEY("T", "Turkey");
	
	private static final String EXPECTED_ID_MESSAGE = "\"A\", \"E\", \"F\", \"G\", \"I\", \"R\" or \"T\"";
	private static final String EXPECTED_NAME_MESSAGE = "\"Austria-Hungary\", \"England\", \"France\", \"Germany\", \"Italy\", \"Russia\" or \"Turkey\"";
	
	private String powerID;
	private String powerName;
	
	public static Powers getPowerFromID (String powerID) {
		String trimmedPowerID = powerID.trim();
		
		for (Powers p: Powers.values()) {
			if (p.getPowerID().equalsIgnoreCase(trimmedPowerID)) {
				return p;
			}
		}
		throw new PowersFormatException(trimmedPowerID, Powers.EXPECTED_ID_MESSAGE);
	}

	public static Powers getPowerFromName (String powerName) {
		String trimmedPowerName = powerName.trim();
		
		for (Powers p: Powers.values()) {
			if (p.getPowerName().equalsIgnoreCase(trimmedPowerName)) {
				return p;
			}
		}
		throw new PowersFormatException(trimmedPowerName, Powers.EXPECTED_NAME_MESSAGE);
	}

	public static boolean isValidPower (String power) {
		String trimmedPower = power.trim();
		
		for (Powers p: Powers.values()) {
			if (p.getPowerID().equalsIgnoreCase(trimmedPower)) {
				return true;
			}
		}
		return false;
	}
	
	public String getPowerID() {
		return this.powerID;
	}

	public String getPowerName() {
		return this.powerName;
	}

	private Powers (String powerID, String powerName) {
		this.powerID = powerID;
		this.powerName = powerName;
	}
	
}
