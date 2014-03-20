package net.craigrm.dip.state.properties;

import java.util.Arrays;
import java.util.List;

import net.craigrm.dip.map.properties.Terrains;

public enum UnitType {
	ARMY("A", Terrains.COAST, Terrains.INLAND),
	FLEET("F", Terrains.COAST, Terrains.SEA),
	NONE("");

	private static final String EXPECTED_FORMAT = "\"A\" or \"F\"";
	
	private final String unitType;
	private final List<Terrains> validTerrains;
	
	/**
	 * 
	 * @param type textual representation of the type of unit (e.g. "A", "F")
	 * @return verified unit type
	 * @throws UnitTypeFormatException if the textual representation cannot be matched to a verified unit type.   
	 */ 
	public static UnitType getType(String type) {
		String trimmedType = type.trim();
		
		for(UnitType t: UnitType.values()) {
			if (t.getUnitType().equalsIgnoreCase(trimmedType)) {
				return t;
			}
		}
		throw new UnitTypeFormatException(trimmedType, EXPECTED_FORMAT);
	}

	public static boolean isValidType(String type) {
		String trimmedType = type.trim();
		
		for(UnitType t: UnitType.values()) {
			if (t.getUnitType().equalsIgnoreCase(trimmedType)) {
				return true;
			}
		}
		return false;
	}

	public String getUnitType() {
		return this.unitType;
	}

	public boolean isValidTerrain(Terrains t) {
		return validTerrains.contains(t);
	}

	private UnitType(String unitType, Terrains... validTerrains ) {
		this.unitType = unitType;
		this.validTerrains = Arrays.asList(validTerrains);
	}
}
