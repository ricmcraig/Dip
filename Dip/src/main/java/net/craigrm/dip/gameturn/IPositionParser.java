package net.craigrm.dip.gameturn;

import java.util.Set;

import net.craigrm.dip.state.Control;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.properties.Season;
import net.craigrm.dip.state.properties.Year;

public interface IPositionParser {

	Set<Unit> getUnits();
	Set<Control> getControl();
	Year getYear();
	Season getSeason();
	void parsePositionDefinition();
	
}
