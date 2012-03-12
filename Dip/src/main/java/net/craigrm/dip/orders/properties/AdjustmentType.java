package net.craigrm.dip.orders.properties;

public enum AdjustmentType {

	BUILD("Build", "Builds"),
	DISBAND("Disband", "Disbands");
	
	private static String expectedMessage = "\"Build(s)\" or \"Disband(s)\"";
	
	private final String singleType;
	private final String pluralType;
	
	AdjustmentType(String singleType, String pluralType) {
		this.singleType = singleType; 
		this.pluralType = pluralType; 
	}
	
	public static AdjustmentType getAdjustmentType (String adjustmentType) {
		String trimmedAdjustmentType = adjustmentType.trim();
		
		for (AdjustmentType o: AdjustmentType.values()) {
			if (o.matches(trimmedAdjustmentType)) {
				return o;
			}
		}
		throw new AdjustmentTypeFormatException(trimmedAdjustmentType, AdjustmentType.expectedMessage);
	}

	public String getSingleType() {
		return singleType;
	}

	public String getPluralType() {
		return pluralType;
	}

	public boolean matches(String type) {
		return singleType.equalsIgnoreCase(type) || pluralType.equalsIgnoreCase(type);
	}
}

