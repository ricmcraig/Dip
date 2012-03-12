package net.craigrm.dip.map.properties;

public class Owner {

	private static final String NO_OWNER = "None";
	private static final String EXPECTED_MESSAGE = "\"A\", \"E\", \"F\", \"G\", \"I\", \"R\", \"T\" or \"\"";
	
	private final Powers owningPower;

	public Owner(String owner) {
		if (owner.trim().isEmpty()) {
			owningPower = null;
		} else {
			try {
				owningPower = Powers.getPowerFromID(owner);
			}
			catch (PowersFormatException pfe) {
				throw new PowersFormatException(owner.trim(), EXPECTED_MESSAGE);
			}
		}
	}
	
	public String getPowerID() {
		if (owningPower == null) {
			return NO_OWNER;
		}
		return owningPower.getPowerID();
	}
	
}
