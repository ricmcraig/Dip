package net.craigrm.dip.state;

import java.util.Collections;
import java.util.Set;

import net.craigrm.dip.gameturn.IPositionDataSource;
import net.craigrm.dip.map.properties.Powers;

/**
 * 
 * @author Ric Craig
 *
 */
public class TurnState {

	private static TurnState turnState;
	private final TurnIdentifier turnID;
	private final Set<Unit> units;
	private final Set<Control> control;
	
	/**
	 * Constructs a new TurnState instance from a PositionParser if there 
	 * is no current TurnState instance. The PositionParser is responsible 
	 * for extracting the unit positions from a  position definition of 
	 * whatever form.  
	 * 
	 * @param pp an {@link net.craigrm.dip.state.IPositionDataSource IPositionParser}
	 *  implementation that extracts unit position data from a position definition.
	 */
	public static void setTurnState(IPositionDataSource pp) {
		if (turnState == null) {
			turnState = new TurnState(pp);
		}
	}

	/**
	 * Provides the sole instance of TurnState created by 
	 * {@link net.craigrm.dip.state.TurnState#setTurnState(IPositionDataSource) setTurnState(IPositionParser)}
	 * @return the TurnState instance
	 * @throws IllegalStateException if TurnState has not been instantiated
	 */
	public static TurnState getState() {
		if (turnState == null) {
			throw new IllegalStateException();
		}
		return turnState;
	}
	
	private TurnState(IPositionDataSource pp) {
		this.turnID = pp.getTurnID();
		this.units = pp.getUnits();
		this.control = pp.getControl();
	}

	public TurnIdentifier getTurnIdentifier() {
		return turnID;
	}
	
	public Set<Unit> getUnits() {
		return Collections.unmodifiableSet(units);
	}

	public Set<Control> getControl() {
		return Collections.unmodifiableSet(control);
	}

	public boolean isAnyUnitPresent(Unit u) {
		for (Powers p:Powers.values()) {
			Unit any = new Unit( u.getCurrentPosition(), p, u.getUnitType()); 
			if (units.contains(any)) {
				return true;
			}
		}
		return false;
	}

	public boolean isOwnedUnitPresent(Unit u) {
		return units.contains(u);
	}
}
