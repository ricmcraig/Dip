package net.craigrm.dip.state;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.properties.Powers;
import net.craigrm.dip.state.properties.UnitType;

public class Unit {
	private final Identifier currentPosition;
	private final Identifier previousPosition;
	private final Powers power;
	private final UnitType unitType;

	public Unit(Identifier position, Powers power, UnitType unitType){
		this.currentPosition = position;
		this.previousPosition = null;
		this.power = power;
		this.unitType = unitType;
	}

	public Unit(Unit unit, Identifier newPosition){
		this.currentPosition = newPosition;
		this.previousPosition = unit.getCurrentPosition();
		this.power = unit.getPower();
		this.unitType = unit.getUnitType();
	}

	public Identifier getCurrentPosition() {
		return currentPosition;
	}

	public Identifier getPreviousPosition() {
		return previousPosition;
	}

	public Powers getPower() {
		return power;
	}

	public UnitType getUnitType() {
		return unitType;
	}
}
