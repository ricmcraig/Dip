package net.craigrm.dip.state;

import java.util.LinkedHashSet;
import java.util.Set;

import net.craigrm.dip.map.IMapper;


public class History {

	private Set<TurnState> turns = new LinkedHashSet<TurnState>();

	public History (IMapper mapper){
		turns.add(new TurnState(TurnIdentifier.INITIAL_TURN_ID, mapper.getStartPosition()));
	}
	
}
