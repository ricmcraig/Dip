package net.craigrm.dip.gameturn;

import java.util.Set;

import net.craigrm.dip.state.Season;
import net.craigrm.dip.state.Unit;
import net.craigrm.dip.state.Year;

public interface IPositionParser {

	Set<Unit> getUnits();
	Year getYear();
	Season getSeason();
	void parsePositionDefinition();
	
}
