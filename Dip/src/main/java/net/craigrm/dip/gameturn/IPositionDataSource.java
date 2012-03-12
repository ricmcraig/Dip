package net.craigrm.dip.gameturn;

import java.util.Set;

import net.craigrm.dip.state.Control;
import net.craigrm.dip.state.TurnIdentifier;
import net.craigrm.dip.state.Unit;

public interface IPositionDataSource {

	Set<Unit> getUnits();
	Set<Control> getControl();
	TurnIdentifier getTurnID();
	void parsePositionDefinition();
	
}
