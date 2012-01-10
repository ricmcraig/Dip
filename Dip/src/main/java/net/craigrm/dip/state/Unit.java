package net.craigrm.dip.state;
import net.craigrm.dip.map.Identifier;
import net.craigrm.dip.map.properties.Powers;

public class Unit {
	private final Identifier position;
	private final Powers power;
	private final UnitType unitType;

	public Unit(Identifier position, Powers power, UnitType unitType){
		this.position = position;
		this.power = power;
		this.unitType = unitType;
	}
}
