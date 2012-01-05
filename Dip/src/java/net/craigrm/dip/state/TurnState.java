package net.craigrm.dip.state;

import java.util.Collections;
import java.util.Set;

public class TurnState {

	private final TurnIdentifier turnID;
	private final Set<Unit> units;
	
	public TurnState(TurnIdentifier turnID, Set<Unit> units){
		this.turnID = turnID;
		this.units = units;
	}
	
	public TurnIdentifier getTurnIdentifier(){
		return turnID;
	}
	
	public Set<Unit> getUnits(){
		return Collections.unmodifiableSet(units);
	}

}
