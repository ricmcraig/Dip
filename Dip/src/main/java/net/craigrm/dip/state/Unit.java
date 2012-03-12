package net.craigrm.dip.state;
import net.craigrm.dip.map.DipMap;
import net.craigrm.dip.map.ProvinceIdentifier;
import net.craigrm.dip.map.Province;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.state.properties.UnitType;

public class Unit {
	private final ProvinceIdentifier currentPosition;
	private final ProvinceIdentifier previousPosition;
	private final Powers power;
	private final UnitType unitType;

	public Unit(ProvinceIdentifier position, UnitType unitType) {
		this.currentPosition = position;
		this.previousPosition = null;
		this.power = null;
		this.unitType = unitType;
	}

	public Unit(ProvinceIdentifier position, Powers power, UnitType unitType) {
		this.currentPosition = position;
		this.previousPosition = null;
		this.power = power;
		this.unitType = unitType;
	}

	public Unit(Unit unit, ProvinceIdentifier newPosition) {
		this.currentPosition = newPosition;
		this.previousPosition = unit.getCurrentPosition();
		this.power = unit.getPower();
		this.unitType = unit.getUnitType();
	}

	public ProvinceIdentifier getCurrentPosition() {
		return currentPosition;
	}

	public ProvinceIdentifier getPreviousPosition() {
		return previousPosition;
	}

	public Powers getPower() {
		return power;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public boolean isPreviousPositionAvailable() {
		return previousPosition!=null;
	}
	
	public boolean isPowerAvailable() {
		return power!=null;
	}
	
	public boolean canMove(ProvinceIdentifier destination) {
		return DipMap.getMap().isNeighbour(currentPosition, destination) && unitType.isValidTerrain(DipMap.getMap().getProvince(destination).getType());
	}

	public boolean canSupport(ProvinceIdentifier destination) {
		return canMove(destination);
	}
	
	public boolean canConvoy(Province province) {
		//TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result
				+ ((unitType == null) ? 0 : unitType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Unit other = (Unit) obj;
		if (currentPosition == null) {
			if (other.currentPosition != null) {
				return false;
			}
		} else if (!currentPosition.equals(other.currentPosition)) {
			return false;
		}
		if (power != other.power) {
			return false;
		}
		if (unitType != other.unitType) {
			return false;
		}
		return true;
	}

}
