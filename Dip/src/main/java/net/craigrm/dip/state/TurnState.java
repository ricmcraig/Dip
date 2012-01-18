package net.craigrm.dip.state;

import java.util.Collections;
import java.util.Set;

import net.craigrm.dip.gameturn.IPositionParser;

/**
 * 
 * @author Ric Craig
 *
 */
public class TurnState {

	private final TurnIdentifier turnID;
	private final Set<Unit> units;
	private final Set<Control> control;
	
	public TurnState(IPositionParser pp) {
		this.turnID = new TurnIdentifier(pp.getYear(), pp.getSeason());
		this.units = pp.getUnits();
		this.control = pp.getControl();
	}

	public TurnIdentifier getTurnIdentifier(){
		return turnID;
	}
	
	public Set<Unit> getUnits(){
		return Collections.unmodifiableSet(units);
	}

	public Set<Control> getControl(){
		return Collections.unmodifiableSet(control);
	}

}
