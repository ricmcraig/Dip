package net.craigrm.dip.state;

import java.util.Collections;
import java.util.Set;

import net.craigrm.dip.gameturn.IPositionParser;

public class TurnState {

	private final TurnIdentifier turnID;
	private final Set<Unit> units;
	
	public TurnState(IPositionParser pp) {
		this.turnID = new TurnIdentifier(pp.getYear(), pp.getSeason());
		this.units = pp.getUnits();
	}

	public TurnIdentifier getTurnIdentifier(){
		return turnID;
	}
	
	public Set<Unit> getUnits(){
		return Collections.unmodifiableSet(units);
	}

}
